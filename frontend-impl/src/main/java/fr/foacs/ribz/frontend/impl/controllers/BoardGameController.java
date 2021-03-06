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

package fr.foacs.ribz.frontend.impl.controllers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.foacs.ribz.frontend.impl.screens.BoardGameScreens;

/**
 * Controller main purpose is to handle screen.
 * It provides to screen application scope graphical object (e.g. ShapeRenderer, SpriteBatch).
 * It lets application to switch active screen.
 *
 * @since 0.1
 */
public interface BoardGameController {

  /**
   * Switches the active screen.
   *
   * @param screen The new active screen.
   */
  void showScreen(BoardGameScreens screen);

  /**
   * Gets the shape renderer.
   *
   * @return the shape renderer.
   */
  ShapeRenderer getShapeRenderer();

  /**
   * Gets the sprite batch.
   *
   * @return The sprite batch.
   */
  SpriteBatch getBatch();

  /**
   * Gets the asset manager.
   *
   * @return The asset manager.
   */
  AssetManager getAssetManager();

  /**
   * Gets camera.
   *
   * @return The camera.
   */
  OrthographicCamera getCamera();

  /**
   * Gets the input processor for camera.
   *
   * @return The input processor.
   */
  CameraInputProcessor getCameraInputProcessor();
}
