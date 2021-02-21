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

import fr.foacs.ribz.core.event.MessageTestImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.annotation.Nonnull;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link MessageHandlerFactory} class.
 *
 * @since 0.1
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Event handler factory")
class MessageHandlerFactoryTest {

  private final static int NUM_THREADS = 10;
  private final static int NUM_ITERATIONS = 1000;

  /**
   * Test for {@link MessageHandlerFactory#getHandlerSet(Class)} method.
   * No cache.
   */
  @Test
  @DisplayName("Get handlerSet - no cache")
  void testGetHandlerSetNoCache() {
    MessageHandlerTestImpl.resetCounter();

    final Set<MessageHandler<MessageTestImpl>> handlerSet = MessageHandlerFactoryTestImpl.getInstance()
        .getHandlerSet(MessageTestImpl.class);

    handlerSet.forEach(handler ->
        assertTrue(handler instanceof MessageHandlerTestImpl)
    );
    assertEquals(1, MessageHandlerTestImpl.getCounter());
  }


  /**
   * Test for {@link MessageHandlerFactory#getHandlerSet(Class)} method.
   * Private constructor.
   */
  @Test
  @DisplayName("Get handlerSet - Private constructor")
  void testGetHandlerPrivateConstructor() {
    MessageHandlerTestImpl.resetCounter();

    class InstantiationExceptionMessage extends MessageTestImpl {

      public InstantiationExceptionMessage() {
        super((short) 10);
      }
    }

    @HandleMessage(InstantiationExceptionMessage.class)
    class InstantiationExceptionMessageHandler extends MessageHandler<InstantiationExceptionMessage> {

      /**
       * Constructs a handler with the class of handled type.
       */
      private InstantiationExceptionMessageHandler() {
        super(InstantiationExceptionMessage.class);
      }

      @Override
      protected void handle(@Nonnull InstantiationExceptionMessage event) {

      }
    }

    final Set<MessageHandler<MessageTestImpl>> handlerSet = MessageHandlerFactoryTestImpl.getInstance()
        .getHandlerSet(InstantiationExceptionMessage.class);

    assertTrue(handlerSet.isEmpty());
  }
}
