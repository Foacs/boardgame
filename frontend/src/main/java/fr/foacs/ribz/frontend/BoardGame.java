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

package fr.foacs.ribz.frontend;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import fr.foacs.ribz.core.SplashScreenWorker;
import fr.foacs.ribz.core.utils.PropertiesLoader;
import fr.foacs.ribz.frontend.controllers.BoardGameController;
import fr.foacs.ribz.frontend.controllers.CameraInputProcessor;
import fr.foacs.ribz.frontend.screens.BoardGameScreens;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.Properties;


/**
 * Entry point class.
 * Initialize application scoped elements (e.g. Batch, ShapeRenderer)
 *
 * @since 0.1
 */
@Slf4j
public class BoardGame extends Game implements BoardGameController {

  @Getter
  private ShapeRenderer shapeRenderer;
  @Getter
  private AssetManager assetManager;
  @Getter
  private boolean created = false;
  @Getter
  private CameraInputProcessor cameraInputProcessor;
  @Getter
  @Setter
  private SpriteBatch batch;
  @Getter
  @Setter
  private OrthographicCamera camera;
  @Setter
  private BoardGameScreens gameScreens;
  @Setter
  private SplashScreenWorker splashScreenWorker;


  private static final BoardGame instance = new BoardGame();

  /**
   * Create new board game application.
   * Log starting message with version information.
   */
  private BoardGame() {
    Properties versionProperties = PropertiesLoader.loadProperties("version");

    log.info("Starting RIBZ version {} powered by libGDX version {} and java version {}",
        versionProperties.getOrDefault("version", "UNKNOWN"),
        versionProperties.getOrDefault("libgdx_version", "UNKNOWN"),
        System.getProperty("java.version"));
  }

  /**
   * Get an instance of BoardGame.
   *
   * @return The BoardGame instance.
   */
  public static BoardGame getInstance() {
    return instance;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Do:
   * <ol>
   *   <li>Close splashScreen</li>
   *   <li>Initialize batch &amp; camera (if not already set).</li>
   *   <li>Initialize shapeRenderer &amp; assetManager.</li>
   *   <li>Load assets</li>
   *   <li>Set startup screen</li>
   * </ol>
   */
  @Override
  public void create() {
    if (Objects.nonNull(splashScreenWorker)) {
      this.splashScreenWorker.closeSplashScreen();
    }
    if (Objects.isNull(this.batch)) {
      this.batch = new SpriteBatch();
    }
    if (Objects.isNull(this.camera)) {
      this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    this.cameraInputProcessor = new CameraInputProcessor(this.camera);
    Gdx.input.setInputProcessor(this.cameraInputProcessor);
    this.shapeRenderer = new ShapeRenderer();
    this.assetManager = new AssetManager();
    this.assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    this.loadAssets();

    if (Objects.nonNull(this.gameScreens)) {
      this.setScreen(this.gameScreens.createScreen(this));
    }

    this.created = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void showScreen(final BoardGameScreens screen) {
    // Nothing for the moment
  }

  /**
   * {@inheritDoc}
   * Destroy:
   * <ol>
   *   <li>Batch</li>
   *   <li>ShapeRenderer</li>
   *   <li>AssetManager</li>
   * </ol>
   */
  @Override
  public void dispose() {
    this.batch.dispose();
    this.shapeRenderer.dispose();
    this.assetManager.dispose();
  }

  /**
   * Load assets into assets manager.
   */
  private void loadAssets() {
    this.assetManager.load("boards/test.tmx", TiledMap.class);
    this.assetManager.finishLoading();
  }
}
