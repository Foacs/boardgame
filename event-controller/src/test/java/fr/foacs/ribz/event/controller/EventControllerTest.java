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

package fr.foacs.ribz.event.controller;

import fr.foacs.ribz.core.event.MessageController;
import fr.foacs.ribz.core.event.handler.MessageHandlerFactory;
import fr.foacs.ribz.event.controller.events.Event;
import fr.foacs.ribz.event.controller.handlers.EventHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link EventController} class.
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
@DisplayName("Event controller")
class EventControllerTest {

  @Spy
  private final EventController victim = EventController.getInstance();
  @Mock
  private EventQueue messageQueue;
  @Mock
  private EventHandler<EventTestImpl> messageHandler;

  /**
   * Test for {@link EventController#tick()} method.
   * Tick polls event.
   */
  @DisplayName("Tick: Poll event")
  @Test
  void testTickPollEvent() {

    setMockEventQueue();

    victim.tick();

    verify(messageQueue).poll();
  }

  /**
   * Test for {@link EventController#tick()} method.
   * Tick handle events.
   */
  @DisplayName("Tick: handle event")
  @Test
  void testTickHandleEvent() {

    final EventTestImpl event = new EventTestImpl((short) 20);

    setMockEventQueue();
    setMockEventHandlerCache();

    when(messageQueue.poll()).thenReturn(Optional.of(event));

    victim.tick();

    verify(messageHandler).handleMessage(event);
  }

  /**
   * Test for {@link EventController#dispatchMessage(Event)} method.
   */
  @DisplayName("dispatch event")
  @Test
  void testDispatchEvent() {

    final EventTestImpl event = new EventTestImpl((short) 20);

    setMockEventQueue();

    victim.dispatchMessage(event);

    verify(messageQueue).add(event);
  }

  @SneakyThrows
  private void setMockEventQueue() {
    FieldSetter.setField(victim, MessageController.class.getDeclaredField("messageQueue"), messageQueue);
  }

  @SneakyThrows
  private void setMockEventHandlerCache() {
    final HashMap<Class<? extends Event>, Set<EventHandler<? extends Event>>> mockCache = new HashMap<>();
    mockCache.put(EventTestImpl.class, Set.of(messageHandler));
    FieldSetter.setField(EventHandlerFactory.getInstance(), MessageHandlerFactory.class.getDeclaredField("cache"), mockCache);
  }
}
