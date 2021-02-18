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

package fr.foacs.ribz.frontend.impl.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import fr.foacs.ribz.core.utils.PropertiesLoader;
import fr.foacs.ribz.frontend.impl.controllers.BoardGameController;
import fr.foacs.ribz.frontend.impl.controllers.CameraInputProcessor;
import fr.foacs.ribz.frontend.impl.models.Board;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.Properties;

/**
 * Screen used to display a board.
 *
 * @since 0.1
 */
@Slf4j
public class BoardScreen extends AbstractScreen {

  private final Color backgroundColor;
  private final OrthographicCamera camera;
  private final CameraInputProcessor cameraController;

  @Setter
  private OrthogonalTiledMapRenderer mapRenderer;

  /**
   * Creates new board screen.
   *
   * @param controller The controller to use.
   * @param batch      The batch used to render sprites.
   */
  public BoardScreen(final BoardGameController controller, final SpriteBatch batch) {
    super(controller, batch);
    log.info("Create board screen");

    this.camera = controller.getCamera();
    this.cameraController = controller.getCameraInputProcessor();

    final Properties colors = PropertiesLoader.loadProperties("colors");
    this.backgroundColor = Color.valueOf(colors.getProperty("background", "#000000"));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void show() {
    final Board board = new Board("test", getController().getAssetManager());
    camera.position.x = board.getPixMapWidth() * Board.SCALE_UNIT * 0.5f;
    camera.position.y = board.getPixMapHeight() * Board.SCALE_UNIT *  0.5f;
    camera.update();
    if (Objects.isNull(mapRenderer)) {
      mapRenderer = new OrthogonalTiledMapRenderer(board.getBoardMap(), Board.SCALE_UNIT, getBatch());
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void render(final float delta) {
    this.setRendered(true);
    Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    this.cameraController.update();
    mapRenderer.setView(camera);
    mapRenderer.render();
  }
}
