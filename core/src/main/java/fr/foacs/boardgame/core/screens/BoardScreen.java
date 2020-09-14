/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (14/09/2020 19:42)
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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.foacs.boardgame.core.controllers.BoardGameController;
import fr.foacs.boardgame.core.utils.PropertiesLoader;
import lombok.extern.slf4j.Slf4j;
import java.util.Properties;

/**
 * Screen used to display board
 */
@Slf4j
public class BoardScreen extends AbstractScreen {

  private final Color backgroundColor;
  private final Color boardColor;

  public BoardScreen(BoardGameController controller, SpriteBatch batch) {
    super(controller, batch);
    log.info("Create board screen");
    final Properties colors = PropertiesLoader.loadProperties("colors");
    this.backgroundColor = Color.valueOf(colors.getProperty("background", "#000000"));
    this.boardColor = Color.valueOf(colors.getProperty("board", "#000000"));
  }

  @Override
  public void show() {
    log.info("SHOW SCREEN: NO ACTION");
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
    getShapeRenderer().setColor(this.boardColor);
    getShapeRenderer().rect((Gdx.graphics.getWidth() -200)/2f, (Gdx.graphics.getHeight()-200)/2f,  200, 200);
    getShapeRenderer().end();

  }


}
