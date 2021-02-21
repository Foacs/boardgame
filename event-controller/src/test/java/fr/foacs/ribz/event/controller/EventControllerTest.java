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
import fr.foacs.ribz.core.event.MessageQueue;
import fr.foacs.ribz.event.controller.events.Event;
import fr.foacs.ribz.event.controller.events.EventTestImpl;
import fr.foacs.ribz.event.controller.handlers.EventHandlerTestImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link EventController} class.
 *
 * @since 0.1
 */
@ExtendWith(MockitoExtension.class)
class EventControllerTest {

  @Mock
  private MessageQueue<Event> messageQueue;

  /**
   * Test for {@link EventController#getInstance()} method.
   * Singleton non null.
   */
  @DisplayName("Singleton - non null")
  @Test
  void testGetInstanceNonNull() {
    assertNotNull(EventController.getInstance());
  }

  /**
   * Test for {@link EventController#tick()} method.
   * Delegate to frontend.
   */
  @SneakyThrows
  @DisplayName("Tick - Poll event")
  @Test
  void testTickPollEvent() {
    FieldSetter.setField(EventController.getInstance(), MessageController.class.getDeclaredField("messageQueue"), messageQueue);

    EventController.getInstance().tick();

    verify(messageQueue, atLeastOnce()).poll();
  }

  /**
   * Test for {@link EventController#tick()} method.
   * Trigger handler.
   */
  @SneakyThrows
  @DisplayName("Render - Poll event")
  @Test
  void testRenderTriggerHandler() {
    FieldSetter.setField(EventController.getInstance(), MessageController.class.getDeclaredField("messageQueue"), messageQueue);
    EventHandlerTestImpl.setCalled(false);

    when(messageQueue.poll()).thenReturn(Optional.of(new EventTestImpl((short) 10)));

    EventController.getInstance().tick();

    assertTrue(EventHandlerTestImpl.isCalled());
  }


  /**
   * Test for {@link EventController#dispose()} method.
   * Stop thread.
   */
  @SneakyThrows
  @DisplayName("Dispose - Stop thread")
  @Test
  void testDisposeStopThread() {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    executor.submit(() -> EventController.getInstance().run());

    executor.shutdown();
    EventController.getInstance().dispose();
    assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
  }
}
