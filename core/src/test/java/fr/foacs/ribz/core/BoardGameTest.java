/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (14/09/2020 21:06)
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

package fr.foacs.boardgame.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BoardGameTest {

  private static final BoardGame VICTIM = BoardGame.getInstance();

  private static final SpriteBatch BATCH = mock(SpriteBatch.class);
  private static final SplashScreenWorker SPLASH_SCREEN_WORKER = mock(SplashScreenWorker.class);

  @BeforeAll
  static void setup() {
    VICTIM.setBatch(BATCH);
    VICTIM.setSplashScreenWorker(SPLASH_SCREEN_WORKER);
    HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
    Gdx.gl = mock(GL20.class);
    Gdx.gl20 = mock(GL20.class);
    Gdx.app = new HeadlessApplication(VICTIM, conf);
  }

  @AfterAll
  static void teardown() {
    Gdx.app.exit();
  }

  @Test
  void test_getInstance_notNull() {
    assertNotNull(VICTIM);
  }

  @Test
  void test_splashScreen_closed() {
    verify(SPLASH_SCREEN_WORKER, times(1)).closeSplashScreen();
  }

  @Test
  void test_getBatch_same() {
    assertSame(BATCH, VICTIM.getBatch());
  }

  @Test
  void test_getShapeRenderer_notNull() {
    Awaitility.await().atMost(2, TimeUnit.SECONDS).until(VICTIM::isCreated);
    assertNotNull(VICTIM.getShapeRenderer());
  }

  @Test
  void test_getAssetManager_notNull() {
    Awaitility.await().atMost(2, TimeUnit.SECONDS).until(VICTIM::isCreated);
    assertNotNull(VICTIM.getAssetManager());
  }

}
