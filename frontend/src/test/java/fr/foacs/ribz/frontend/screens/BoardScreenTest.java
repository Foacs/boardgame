/*
 * Copyright or © or Copr. Foacs
 * contributor(s):
 * - Alexis DINQUER adinquer@yahoo.com
 * - Brice KESSLER brice.kessler@outlook.com
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

package fr.foacs.ribz.frontend.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import fr.foacs.ribz.frontend.controllers.BoardGameController;
import fr.foacs.ribz.frontend.mocks.BoardControllerMock;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Test class for the {@link BoardScreen} class.
 */
@DisplayName("Board screen")
class BoardScreenTest {

  private static BoardScreen spyBoardScreen;

  private static final BoardGameController controller = new BoardControllerMock();
  private static final SpriteBatch batch = mock(SpriteBatch.class);

  @BeforeAll
  static void setup() {
    HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
    Game game = new Game() {
      @Override
      public void create() {
      }
    };
    Gdx.app = new HeadlessApplication(game, conf);
    Gdx.gl = mock(GL20.class);

    final BoardScreen boardScreen = new BoardScreen(controller, batch);
    spyBoardScreen = spy(boardScreen);
    spyBoardScreen.setMapRenderer(mock(OrthogonalTiledMapRenderer.class));
    game.setScreen(spyBoardScreen);
  }

  @AfterAll
  static void teardown() {
    Gdx.app.exit();
  }

  /**
   * Test the {@link BoardScreen#render(float)} method call clear color
   */
  @DisplayName("Render: call clear color")
  @Test
  void testRenderCallClearColor() {
    Awaitility.await().atMost(2, TimeUnit.SECONDS).until(spyBoardScreen::isRendered);
    verify(Gdx.gl, atLeastOnce()).glClearColor(anyFloat(), anyFloat(), anyFloat(), anyFloat());
  }

  /**
   * Test the {@link BoardScreen#render(float)} method call clear color buffer bit
   */
  @DisplayName("Render: call color buffer bit")
  @Test
  void testRenderCallClearColorBufferBit() {
    Awaitility.await().atMost(2, TimeUnit.SECONDS).until(spyBoardScreen::isRendered);
    verify(Gdx.gl, atLeastOnce()).glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  /**
   * Test the {@link BoardScreen#render(float)} method called
   */
  @DisplayName("Render: called")
  @Test
  void testRenderCalled() {
    Awaitility.await().atMost(2, TimeUnit.SECONDS).until(spyBoardScreen::isRendered);
    verify(spyBoardScreen, atLeastOnce()).render(anyFloat());
  }

  /**
   * Test the {@link BoardScreen#getController()} method
   */
  @DisplayName("Get controller")
  @Test
  void test_getController() {
    assertSame(controller, spyBoardScreen.getController());
  }

  /**
   * Test the {@link BoardScreen#getBatch()} method
   */
  @DisplayName("Get batch")
  @Test
  void test_getBatch() {
    assertSame(batch, spyBoardScreen.getBatch());
  }


  /**
   * Test the {@link BoardScreen#getShapeRenderer()} method
   */
  @DisplayName("Get shape renderer")
  @Test
  void testGetShapeRenderer() {
    assertSame(controller.getShapeRenderer(), spyBoardScreen.getShapeRenderer());
  }

}
