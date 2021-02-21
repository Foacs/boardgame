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

package fr.foacs.ribz.frontend.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.foacs.ribz.event.controller.EventController;
import fr.foacs.ribz.event.controller.events.Event;
import fr.foacs.ribz.event.controller.events.LifeCycleEvent;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link RibzFrontend} class.
 *
 * @since 0.1
 */
@ExtendWith(MockitoExtension.class)
class RibzFrontendTest {

  @Captor
  private ArgumentCaptor<Event> eventArgumentCaptor;

  @BeforeAll
  static void setUp() {
    Gdx.graphics = mock(Graphics.class);
    Gdx.input = mock(Input.class);
  }

  /**
   * Test for {@link RibzFrontend#getInstance()} method.
   * Singleton non null.
   */
  @DisplayName("Singleton - non null")
  @Test
  void testGetInstanceNonNull() {
    assertNotNull(RibzFrontend.getInstance());
  }

  /**
   * Test for {@link RibzFrontend#setScreen(Screen)} method.
   * Hide previous screen
   */
  @SneakyThrows
  @DisplayName("Set screen - hide previous")
  @Test
  void testSetScreenHidePreviousScreen() {
    final Screen previousScreen = mock(Screen.class);
    FieldSetter.setField(RibzFrontend.getInstance(), RibzFrontend.class.getDeclaredField("screen"), previousScreen);

    RibzFrontend.getInstance().setScreen(null);

    verify(previousScreen).hide();
  }

  /**
   * Test for {@link RibzFrontend#setScreen(Screen)} method.
   * Show new screen
   */
  @DisplayName("Set screen - show new screen")
  @Test
  void testSetScreenShowNewScreen() {
    final Screen newScreen = mock(Screen.class);

    RibzFrontend.getInstance().setScreen(newScreen);

    verify(newScreen).show();
  }

  /**
   * Test for {@link RibzFrontend#setScreen(Screen)} method.
   * Resize new screen
   */
  @DisplayName("Set screen - show new screen")
  @Test
  void testSetScreenResizeNewScreen() {
    final Screen newScreen = mock(Screen.class);

    RibzFrontend.getInstance().setScreen(newScreen);

    verify(newScreen).resize(anyInt(), anyInt());
  }

  /**
   * Test for {@link RibzFrontend#resize(int, int)} method.
   * Delegate to screen.
   */
  @DisplayName("Resize - Delegate to screen")
  @Test
  void testResizeDelegateFrontEnd() {
    final Screen screen = mock(Screen.class);
    final int mockWidth = 10;
    final int mockHeight = 30;

    RibzFrontend.getInstance().setScreen(screen);

    RibzFrontend.getInstance().resize(mockWidth, mockHeight);

    verify(screen).resize(mockWidth, mockHeight);
  }

  /**
   * Test for {@link RibzFrontend#render()} method.
   * Delegate to screen.
   */
  @DisplayName("Render - Delegate to screen")
  @Test
  void testRenderDelegateFrontEnd() {
    final Screen screen = mock(Screen.class);

    RibzFrontend.getInstance().setScreen(screen);

    RibzFrontend.getInstance().render();

    verify(screen).render(anyFloat());
  }

  /**
   * Test for {@link RibzFrontend#pause()} method.
   * Delegate to screen.
   */
  @DisplayName("Pause - Delegate to screen")
  @Test
  void testPauseDelegateFrontEnd() {
    final Screen screen = mock(Screen.class);

    RibzFrontend.getInstance().setScreen(screen);

    RibzFrontend.getInstance().pause();

    verify(screen).pause();
  }


  /**
   * Test for {@link RibzFrontend#pause()} method.
   * Send event.
   */
  @SneakyThrows
  @DisplayName("Pause - send event")
  @Test
  void testPauseSendEvent() {
    final EventController eventController = mock(EventController.class);
    RibzFrontend.getInstance().setMessageListener(eventController);

    RibzFrontend.getInstance().pause();

    verify(eventController).dispatchMessage(eventArgumentCaptor.capture());

    final Event event = eventArgumentCaptor.getValue();

    assertTrue(event instanceof LifeCycleEvent);
    final LifeCycleEvent lifeCycleEvent = (LifeCycleEvent) event;
    assertEquals(LifeCycleEvent.Type.PAUSE, lifeCycleEvent.getType());
  }

  /**
   * Test for {@link RibzFrontend#resume()} method.
   * Delegate to screen.
   */
  @DisplayName("Resume - Delegate to screen")
  @Test
  void testResumeDelegateFrontEnd() {
    final Screen screen = mock(Screen.class);

    RibzFrontend.getInstance().setScreen(screen);

    RibzFrontend.getInstance().resume();

    verify(screen).resume();
  }

  /**
   * Test for {@link RibzFrontend#resume()} method.
   * Send event.
   */
  @SneakyThrows
  @DisplayName("Resume - send event")
  @Test
  void testResumeSendEvent() {
    final EventController eventController = mock(EventController.class);
    RibzFrontend.getInstance().setMessageListener(eventController);

    RibzFrontend.getInstance().resume();

    verify(eventController).dispatchMessage(eventArgumentCaptor.capture());

    final Event event = eventArgumentCaptor.getValue();

    assertTrue(event instanceof LifeCycleEvent);
    final LifeCycleEvent lifeCycleEvent = (LifeCycleEvent) event;
    assertEquals(LifeCycleEvent.Type.RESUME, lifeCycleEvent.getType());
  }

  /**
   * Test for {@link RibzFrontend#dispose()} method.
   * Delegate to screen.
   */
  @DisplayName("Dispose - Delegate to screen")
  @Test
  void testDisposeDelegateFrontEnd() {
    final Screen screen = mock(Screen.class);

    RibzFrontend.getInstance().setScreen(screen);

    RibzFrontend.getInstance().dispose();

    verify(screen).dispose();
  }

  /**
   * Test for {@link RibzFrontend#dispose()} method.
   * Dispose batch.
   */
  @SneakyThrows
  @DisplayName("Dispose - dispose batch")
  @Test
  void testDisposeDisposeBatch() {
    final SpriteBatch batch = mock(SpriteBatch.class);
    FieldSetter.setField(RibzFrontend.getInstance(), RibzFrontend.class.getDeclaredField("batch"), batch);

    RibzFrontend.getInstance().dispose();

    verify(batch).dispose();
  }

  /**
   * Test for {@link RibzFrontend#dispose()} method.
   * Dispose shape renderer.
   */
  @SneakyThrows
  @DisplayName("Dispose - dispose shape renderer")
  @Test
  void testDisposeDisposeShapeRenderer() {
    final ShapeRenderer shapeRenderer = mock(ShapeRenderer.class);
    FieldSetter.setField(RibzFrontend.getInstance(), RibzFrontend.class.getDeclaredField("shapeRenderer"), shapeRenderer);

    RibzFrontend.getInstance().dispose();

    verify(shapeRenderer).dispose();
  }

  /**
   * Test for {@link RibzFrontend#dispose()} method.
   * Dispose asset manager.
   */
  @SneakyThrows
  @DisplayName("Dispose - dispose asset manager")
  @Test
  void testDisposeDisposeAssetManager() {
    final AssetManager assetManager = mock(AssetManager.class);
    FieldSetter.setField(RibzFrontend.getInstance(), RibzFrontend.class.getDeclaredField("assetManager"), assetManager);

    RibzFrontend.getInstance().dispose();

    verify(assetManager).dispose();
  }

  /**
   * Test for {@link RibzFrontend#dispose()} method.
   * Send event.
   */
  @SneakyThrows
  @DisplayName("Dispose - send event")
  @Test
  void testDisposeSendEvent() {
    final EventController eventController = mock(EventController.class);
    RibzFrontend.getInstance().setMessageListener(eventController);

    RibzFrontend.getInstance().dispose();

    verify(eventController).dispatchMessage(eventArgumentCaptor.capture());

    final Event event = eventArgumentCaptor.getValue();

    assertTrue(event instanceof LifeCycleEvent);
    final LifeCycleEvent lifeCycleEvent = (LifeCycleEvent) event;
    assertEquals(LifeCycleEvent.Type.DISPOSE, lifeCycleEvent.getType());
  }
}
