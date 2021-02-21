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

package fr.foacs.ribz.response.controller;

import fr.foacs.ribz.core.event.MessageController;
import fr.foacs.ribz.core.event.MessageQueue;
import fr.foacs.ribz.frontend.api.Frontend;
import fr.foacs.ribz.response.controller.handlers.ResponseHandlerTestImpl;
import fr.foacs.ribz.response.controller.responses.Response;
import fr.foacs.ribz.response.controller.responses.ResponseTestImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link ResponseController} class.
 *
 * @since 0.1
 */
@ExtendWith(MockitoExtension.class)
class ResponseControllerTest {

  @Mock
  private MessageQueue<Response> messageQueue;

  /**
   * Test for {@link ResponseController#getInstance()} method.
   * Singleton non null.
   */
  @DisplayName("Singleton - non null")
  @Test
  void testGetInstanceNonNull() {
    assertNotNull(ResponseController.getInstance());
  }

  /**
   * Test for {@link ResponseController#create()} method.
   * Delegate to frontend.
   */
  @DisplayName("Create - Delegate to frontend")
  @Test
  void testCreateDelegateFrontEnd() {
    final Frontend frontend = mock(Frontend.class);

    ResponseController.getInstance().setFrontend(frontend);

    ResponseController.getInstance().create();

    verify(frontend).init();
  }

  /**
   * Test for {@link ResponseController#resize(int, int)} method.
   * Delegate to frontend.
   */
  @DisplayName("Resize - Delegate to frontend")
  @Test
  void testResizeDelegateFrontEnd() {
    final Frontend frontend = mock(Frontend.class);
    final int mockWidth = 10;
    final int mockHeight = 30;

    ResponseController.getInstance().setFrontend(frontend);

    ResponseController.getInstance().resize(mockWidth, mockHeight);

    verify(frontend).resize(mockWidth, mockHeight);
  }

  /**
   * Test for {@link ResponseController#render()} method.
   * Delegate to frontend.
   */
  @DisplayName("Render - Delegate to frontend")
  @Test
  void testRenderDelegateFrontEnd() {
    final Frontend frontend = mock(Frontend.class);

    ResponseController.getInstance().setFrontend(frontend);

    ResponseController.getInstance().render();

    verify(frontend).render();
  }

  /**
   * Test for {@link ResponseController#render()} method.
   * Delegate to frontend.
   */
  @SneakyThrows
  @DisplayName("Render - Poll event")
  @Test
  void testRenderPollEvent() {
    FieldSetter.setField(ResponseController.getInstance(), MessageController.class.getDeclaredField("messageQueue"), messageQueue);

    ResponseController.getInstance().render();

    verify(messageQueue).poll();
  }

  /**
   * Test for {@link ResponseController#render()} method.
   * Delegate to frontend.
   */
  @SneakyThrows
  @DisplayName("Render - Poll event")
  @Test
  void testRenderTriggerHandler() {
    FieldSetter.setField(ResponseController.getInstance(), MessageController.class.getDeclaredField("messageQueue"), messageQueue);
    ResponseHandlerTestImpl.setCalled(false);

    when(messageQueue.poll()).thenReturn(Optional.of(new ResponseTestImpl((short) 10)));

    ResponseController.getInstance().render();

    assertTrue(ResponseHandlerTestImpl.isCalled());
  }

  /**
   * Test for {@link ResponseController#pause()} method.
   * Delegate to frontend.
   */
  @DisplayName("Pause - Delegate to frontend")
  @Test
  void testPauseDelegateFrontEnd() {
    final Frontend frontend = mock(Frontend.class);

    ResponseController.getInstance().setFrontend(frontend);

    ResponseController.getInstance().pause();

    verify(frontend).pause();
  }

  /**
   * Test for {@link ResponseController#resume()} method.
   * Delegate to frontend.
   */
  @DisplayName("Resume - Delegate to frontend")
  @Test
  void testResumeDelegateFrontEnd() {
    final Frontend frontend = mock(Frontend.class);

    ResponseController.getInstance().setFrontend(frontend);

    ResponseController.getInstance().resume();

    verify(frontend).resume();
  }

  /**
   * Test for {@link ResponseController#dispose()} method.
   * Delegate to frontend.
   */
  @DisplayName("Dispose - Delegate to frontend")
  @Test
  void testDisposeDelegateFrontEnd() {
    final Frontend frontend = mock(Frontend.class);

    ResponseController.getInstance().setFrontend(frontend);

    ResponseController.getInstance().dispose();

    verify(frontend).dispose();
  }


}
