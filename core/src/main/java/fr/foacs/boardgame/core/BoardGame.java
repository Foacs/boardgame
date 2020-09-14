/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (13/09/2020 18:11)
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

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.foacs.boardgame.core.controllers.BoardGameController;
import fr.foacs.boardgame.core.screens.BoardGameScreens;
import fr.foacs.boardgame.core.utils.PropertiesLoader;
import lombok.extern.slf4j.Slf4j;
import java.util.Properties;


/**
 * Entry point class
 */
@Slf4j
public class BoardGame extends Game implements BoardGameController {

  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;

  private static final BoardGame instance = new BoardGame();

  private BoardGame() {
    // Nothing to do here
  }

  public static BoardGame getInstance() {
    return instance;
  }


  @Override
  public void create() {
    Properties versionProperties = PropertiesLoader.loadProperties("version");

    log.info("Starting BoardGame version {} powered by libGDX version {} and java version {}",
        versionProperties.getOrDefault("version", "UNKNOWN"),
        versionProperties.getOrDefault("libgdx_version", "UNKNOWN"),
        System.getProperty("java.version"));

    this.batch = new SpriteBatch();
    this.shapeRenderer = new ShapeRenderer();

    this.setScreen(BoardGameScreens.BOARD.createScreen(this));
  }

  @Override
  public void showScreen(BoardGameScreens screen) {
    // Nothing for the moment
  }

  @Override
  public void dispose() {
    batch.dispose();
    shapeRenderer.dispose();
  }

  @Override
  public ShapeRenderer getShapeRender() {
    return this.shapeRenderer;
  }

  @Override
  public SpriteBatch getBatch() {
    return this.batch;
  }
}