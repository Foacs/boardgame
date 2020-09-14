/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (14/09/2020 21:12)
 *
 * adinquer@yahoo.com
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
 * that may mean that it is complicated to manipulaten, and that also
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

package fr.foacs.boardgame.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.foacs.boardgame.core.controllers.BoardGameController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class BoardScreenTest {

  private static BoardScreen spyBoardScreen;

  private static final BoardGameController controller = new MockBoardController();
  private static final SpriteBatch batch = mock(SpriteBatch.class);

  @BeforeAll
  static void setup() {
    HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
    Game game = new Game() {
      @Override
      public void create() {}
    };
    Gdx.app = new HeadlessApplication(game, conf);
    Gdx.gl = mock(GL20.class);

    final BoardScreen boardScreen = new BoardScreen(controller, batch);
    spyBoardScreen = spy(boardScreen);
    game.setScreen(spyBoardScreen);
  }

  @AfterAll
  static void teardown() {
    Gdx.app.exit();
  }

  @Test
  void test_clearColor_call() {
    verify(Gdx.gl, atLeastOnce()).glClearColor(anyFloat(), anyFloat(), anyFloat(), anyFloat());
  }

  @Test
  void test_clearColorBufferBit_call() {
    verify(Gdx.gl, atLeastOnce()).glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Test
  void test_render_call() {
    verify(spyBoardScreen, atLeastOnce()).render(anyFloat());
  }

  @Test
  void test_getController() {
    assertSame(controller, spyBoardScreen.getController());
  }

 @Test
  void test_getBatch() {
   assertSame(batch, spyBoardScreen.getBatch());
  }

}
