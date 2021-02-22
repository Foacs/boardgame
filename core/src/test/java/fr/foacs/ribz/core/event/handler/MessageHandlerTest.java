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

import fr.foacs.ribz.core.event.Message;
import fr.foacs.ribz.core.event.MessageTestImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link MessageHandler} class.
 *
 * @since 0.1
 */
@ExtendWith(MockitoExtension.class)
class MessageHandlerTest {

  @Spy
  private final MessageHandler<MessageTestImpl> victim = new MessageHandlerTestImpl();

  /**
   * Test for {@link MessageHandler#handleMessage(Message)} method.
   * Inactive message.
   */
  @DisplayName("Handle message - Inactive message")
  @Test
  void testHandleMessageInactiveMessage() {
    final MessageTestImpl messageTest = mock(MessageTestImpl.class);

    when(messageTest.isActive()).thenReturn(false);

    victim.handleMessage(messageTest);

    verify(victim, never()).handle(any(MessageTestImpl.class));
  }

  /**
   * Test for {@link MessageHandler#handleMessage(Message)} method.
   * Non assignable message.
   */
  @DisplayName("Handle message - Non assignable message")
  @Test
  void testHandleMessageNonAssignableMessage() {
    final Message messageTest = mock(Message.class);

    when(messageTest.isActive()).thenReturn(true);

    victim.handleMessage(messageTest);

    verify(victim, never()).handle(any(MessageTestImpl.class));
  }

  /**
   * Test for {@link MessageHandler#handleMessage(Message)} method.
   */
  @DisplayName("Handle message")
  @Test
  void testHandleMessage() {
    final MessageTestImpl messageTest = new MessageTestImpl((short) 10);

    victim.handleMessage(messageTest);

    verify(victim).handle(any(MessageTestImpl.class));
  }


}
