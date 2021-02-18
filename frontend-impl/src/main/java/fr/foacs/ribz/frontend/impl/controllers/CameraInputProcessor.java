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

package fr.foacs.ribz.frontend.impl.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.MathUtils;


/**
 * Input processor for the board camera.
 * Handles Zoom / Translation.
 *
 * @since 0.1
 */
public class CameraInputProcessor extends CameraInputController {


  /** The factor which used to divide the zoom amount. */
  public static final float ZOOM_FACTOR = 25f;
  /** The minimum value for the zoom. */
  public static final float ZOOM_MIN = 0.3f;
  /** The maximum value for the zoom. */
  public static final float ZOOM_MAX = 1f;
  private static final int DEFAULT_TRANSLATE_BUTTON = Input.Buttons.LEFT;
  private static final float DEFAULT_TRANSLATE_UNITS = 400f;

  private float totalDeltaX = 0;
  private float totalDeltaY = 0;
  private final float screenWidth;
  private final float screenHeight;
  private final OrthographicCamera orthographicCamera;

  /**
   * Constructor.
   * Sets default values for zoom &amp; translation factors
   *
   * @param camera The camera to process
   */
  public CameraInputProcessor(final OrthographicCamera camera) {
    super(camera);
    this.screenWidth = Gdx.graphics.getWidth();
    this.screenHeight = Gdx.graphics.getHeight();
    this.orthographicCamera = camera;
    translateButton = DEFAULT_TRANSLATE_BUTTON;
    // Disable the rotation button
    rotateButton = -1;
    translateUnits = DEFAULT_TRANSLATE_UNITS;
    scrollFactor = -1f / DEFAULT_TRANSLATE_UNITS;
  }

  /**
   * {@inheritDoc}.
   * Computes &amp; applies zoom level to orthographic camera.
   */
  @Override
  public boolean zoom(final float amount) {
    this.orthographicCamera.zoom += (amount/ZOOM_FACTOR);
    orthographicCamera.zoom = MathUtils.clamp(orthographicCamera.zoom, 0.3f, 1f);
    if (autoUpdate) camera.update();
    return true;
  }


  /**
   * {@inheritDoc}.
   * Limits the camera movement to screen's size / 2.
   */
  @Override
  protected boolean process(float deltaX, float deltaY, int button) {
    this.totalDeltaX += (deltaX * translateUnits);
    this.totalDeltaY += (deltaY * translateUnits);
    float overrideDeltaX = deltaX;
    float overrideDeltaY = deltaY;
    if (Math.abs(this.totalDeltaX) > (this.screenWidth /2) ) {
      overrideDeltaX = 0;
      this.totalDeltaX -= (deltaX * translateUnits);
    }
    if (Math.abs(this.totalDeltaY) > (this.screenHeight /2) ) {
      overrideDeltaY = 0;
      this.totalDeltaY -= (deltaY * translateUnits);
    }
    return super.process(overrideDeltaX, overrideDeltaY, button);
  }
}
