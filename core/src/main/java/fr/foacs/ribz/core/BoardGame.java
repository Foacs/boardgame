/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (17/09/2020 19:09)
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

package fr.foacs.ribz.core;

import com.badlogic.gdx.Game;
import fr.foacs.ribz.core.utils.PropertiesLoader;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import fr.foacs.ribz.core.controllers.BoardGameController;
import fr.foacs.ribz.core.screens.BoardGameScreens;
import fr.foacs.ribz.core.utils.PropertiesLoader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.Properties;


/**
 * Entry point class.
 * Initialize application scoped elements (e.g. Batch, ShapeRenderer)
 * @since 0.1
 */
@Slf4j
public class BoardGame extends Game implements BoardGameController {

  @Getter
  @Setter
  private SpriteBatch batch;
  @Getter
  private ShapeRenderer shapeRenderer;
  @Getter
  private AssetManager assetManager;
  private SplashScreenWorker splashScreenWorker;
  @Setter
  private BoardGameScreens gameScreens;
  @Getter
  private boolean created = false;


  private static final BoardGame instance = new BoardGame();

  private BoardGame() {
    Properties versionProperties = PropertiesLoader.loadProperties("version");

    log.info("Starting RIBZ version {} powered by libGDX version {} and java version {}",
        versionProperties.getOrDefault("version", "UNKNOWN"),
        versionProperties.getOrDefault("libgdx_version", "UNKNOWN"),
        System.getProperty("java.version"));
  }

  /**
   * Gets an instance of BoardGame.
   *
   * @return The BoardGame instance.
   */
  public static BoardGame getInstance() {
    return instance;
  }

  @Override
  public void create() {
    if (Objects.nonNull(splashScreenWorker)) {
      this.splashScreenWorker.closeSplashScreen();
    }
    if (Objects.isNull(this.batch)) {
      this.batch = new SpriteBatch();
    }
    this.shapeRenderer = new ShapeRenderer();
    this.assetManager = new AssetManager();
    this.assetManager.setLoader(TiledMap.class, new TmxMapLoader());
    this.loadAssets();

    if (Objects.nonNull(this.gameScreens)) {
      this.setScreen(this.gameScreens.createScreen(this));
    }
    this.created = true;
  }

  @Override
  public void showScreen(BoardGameScreens screen) {
    // Nothing for the moment
  }

  @Override
  public void dispose() {
    this.batch.dispose();
    this.shapeRenderer.dispose();
    this.assetManager.dispose();
  }

  /**
   * Set the splashScreenWorker to used.
   *
   * @param splashScreenWorker The splashScreen to used.
   */
  public void setSplashScreenWorker(SplashScreenWorker splashScreenWorker) {
    this.splashScreenWorker = splashScreenWorker;
  }

  /**
   * Load assets into assets manager.
   */
  private void loadAssets() {
    this.assetManager.load("boards/test.tmx", TiledMap.class);
    this.assetManager.finishLoading();
  }
}