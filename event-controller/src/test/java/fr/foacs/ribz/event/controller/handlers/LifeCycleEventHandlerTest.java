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

package fr.foacs.ribz.event.controller.handlers;

import fr.foacs.ribz.backend.api.Backend;
import fr.foacs.ribz.event.controller.EventController;
import fr.foacs.ribz.event.controller.events.LifeCycleEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * Test for {@link LifeCycleEventHandler} class.
 *
 * @since 0.1
 */
@ExtendWith(MockitoExtension.class)
class LifeCycleEventHandlerTest {

  private final LifeCycleEventHandler victim = new LifeCycleEventHandler();

  @Mock
  private Backend backend;

  @BeforeEach
  void init() {
    EventController.getInstance().setBackend(backend);
  }

  /**
   * Test for {@link LifeCycleEventHandler#handle(LifeCycleEvent)} method.
   * Delegate {@link LifeCycleEvent.Type#INIT} to Backend.
   */
  @DisplayName("Handle - delegate INIT to backend")
  @Test
  void testHandleInit() {
    victim.handle(new LifeCycleEvent(LifeCycleEvent.Type.INIT));

    verify(backend).init();
  }

  /**
   * Test for {@link LifeCycleEventHandler#handle(LifeCycleEvent)} method.
   * Delegate {@link LifeCycleEvent.Type#PAUSE} to Backend.
   */
  @DisplayName("Handle - delegate PAUSE to backend")
  @Test
  void testHandlePause() {
    victim.handle(new LifeCycleEvent(LifeCycleEvent.Type.PAUSE));

    verify(backend).pause();
  }

  /**
   * Test for {@link LifeCycleEventHandler#handle(LifeCycleEvent)} method.
   * Delegate {@link LifeCycleEvent.Type#RESUME} to Backend.
   */
  @DisplayName("Handle - delegate RESUME to backend")
  @Test
  void testHandleResume() {
    victim.handle(new LifeCycleEvent(LifeCycleEvent.Type.RESUME));

    verify(backend).resume();
  }

  /**
   * Test for {@link LifeCycleEventHandler#handle(LifeCycleEvent)} method.
   * Delegate {@link LifeCycleEvent.Type#DISPOSE} to Backend.
   */
  @DisplayName("Handle - delegate DISPOSE to backend")
  @Test
  void testHandleDispose() {
    victim.handle(new LifeCycleEvent(LifeCycleEvent.Type.DISPOSE));

    verify(backend).dispose();
  }
}
