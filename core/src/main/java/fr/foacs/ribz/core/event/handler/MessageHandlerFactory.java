/*
 * Copyright or © or Copr. Foacs
 * contributor(s):
 *  - Alexis DINQUER adinquer@yahoo.com
 *  - Brice KESSLER brice.kessler@outlook.com
 *
 * This software is a computer program whose purpose is to develop and
 * play board game.
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software. You can use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty and the software's author, the holder of the
 * economic rights, and the successive licensors have only limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading, using, modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean that it is complicated to manipulate, and that also
 * therefore means that it is reserved for developers and experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encourage to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and, more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

package fr.foacs.ribz.core.event.handler;

import com.google.common.collect.MapMaker;
import fr.foacs.ribz.core.event.Message;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * Factory for {@link MessageHandler}.<br>
 * Retrieves handler annotated with {@link HandleMessage} and identified them by the handled event type.
 *
 * @since 0.1
 */
@Slf4j
public abstract class MessageHandlerFactory<T extends Message> {

  private final Map<Class<? extends T>, Set<MessageHandler<T>>> cache;
  private final Reflections reflections;

  /**
   * Instantiates handler's cache.<br>
   * Instantiates {@link Reflections}.
   */
  protected MessageHandlerFactory() {
    this.cache = new MapMaker().weakValues().makeMap();
    this.reflections = new Reflections("fr.foacs.ribz");
  }

  /**
   * Gets an {@link MessageHandler} identified by its event type handled.
   *
   * @param eventClass The discriminante value of event type.
   * @return An optional with handler found. Empty if no handler exists or an issue has been thrown.
   */
  @Nonnull
  public Set<MessageHandler<T>> getHandlerSet(@Nonnull final Class<? extends T> eventClass) {
    return cache.computeIfAbsent(eventClass, this::searchHandlerSet);
  }

  /**
   * Searches instance of {@link MessageHandler} with annotation {@link HandleMessage} for the given {@link Message} class.
   *
   * @param searchEventClass The {@link Message} class used to map {@link MessageHandler}.
   * @return The found {@link MessageHandler}.
   */
  @Nonnull
  private Set<MessageHandler<T>> searchHandlerSet(@Nonnull Class<? extends T> searchEventClass) {
    final Set<MessageHandler<T>> resultSet = new TreeSet<>(new MessageHandlerComparator<>());
    for (Class<?> handler : reflections.getTypesAnnotatedWith(HandleMessage.class)) {
      final HandleMessage handleMessageAnnotation = handler.getAnnotation(HandleMessage.class);
      // If annotated type match
      if (Objects.nonNull(handleMessageAnnotation) && searchEventClass.equals(handleMessageAnnotation.value())) {
        createInstanceOfMessageHandler(handler).ifPresent(resultSet::add);
      }
    }
    return resultSet;
  }

  /**
   * Creates an instance of {@link MessageHandler} from its class.
   *
   * @param handler The class's handler.
   * @return The instance of {@link MessageHandler}.
   */
  @Nonnull
  private Optional<MessageHandler<T>> createInstanceOfMessageHandler(@Nonnull Class<?> handler) {
    try {
      final Class<?>[] classes = new Class[0];
      final Object[] objects = new Object[0];
      if (MessageHandler.class.isAssignableFrom(handler)) {
        return Optional.of(((Class<MessageHandler<T>>) handler).getConstructor(classes).newInstance(objects));
      }
    } catch (InstantiationException e) {
      log.error("The event handler {} is abstract.", handler.getSimpleName());
    } catch (InvocationTargetException e) {
      log.error(String.format("The event handler %s constructor has throws exception", handler.getSimpleName()), e);
    } catch (IllegalAccessException | NoSuchMethodException e) {
      log.error("The event handler {} has no empty public constructor.", handler.getSimpleName());
    }
    return Optional.empty();
  }

  /**
   * Comparator of {@link MessageHandler} based on salience define in annotation {@link HandleMessage}.
   */
  private static class MessageHandlerComparator<T extends Message> implements Comparator<MessageHandler<T>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(@Nullable final MessageHandler handler1, @Nullable final MessageHandler handler2) {
      if (Objects.isNull(handler1) || Objects.isNull(handler2)) {
        return 0;
      }
      final HandleMessage annotation1 = handler1.getClass().getAnnotation(HandleMessage.class);
      final HandleMessage annotation2 = handler2.getClass().getAnnotation(HandleMessage.class);
      if (Objects.isNull(annotation1) || Objects.isNull(annotation2)) {
        return 0;
      }
      final int priority = annotation1.priority();
      final int priority1 = annotation2.priority();
      return Integer.compare(priority, priority1);
    }
  }
}
