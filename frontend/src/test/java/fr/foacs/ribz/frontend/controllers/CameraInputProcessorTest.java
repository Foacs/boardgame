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

package fr.foacs.ribz.frontend.controllers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.foacs.ribz.frontend.mocks.OrthographicCameraMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for the {@link CameraInputProcessor} class.
 */
class CameraInputProcessorTest {

  private final OrthographicCamera cameraMock = Mockito.spy(new OrthographicCameraMock());
  private final CameraInputProcessor victim = new CameraInputProcessor(cameraMock);

  @BeforeAll
  static void setup() {
    Gdx.app = mock(Application.class);
  }


  /**
   * Test for the {@link CameraInputProcessor#zoom(float)} method zoom in min / max range
   */
  @DisplayName("Zoom: In min/max range")
  @Test
  void testZoomInMinMaxRange() {
    final float zoomAmount = -1f;

    victim.zoom(zoomAmount);
    assertEquals(1f + (zoomAmount / CameraInputProcessor.ZOOM_FACTOR), cameraMock.zoom);
    verify(cameraMock).update();
  }

  /**
   * Test for the {@link CameraInputProcessor#zoom(float)} method zoom greater than max
   */
  @DisplayName("Zoom: Greater than max")
  @Test
  void testZoomGreaterThanMax() {
    final float zoomAmount = (CameraInputProcessor.ZOOM_MAX + 2f ) * CameraInputProcessor.ZOOM_FACTOR;

    victim.zoom(zoomAmount);
    assertEquals(CameraInputProcessor.ZOOM_MAX, cameraMock.zoom);
    verify(cameraMock).update();
  }

  /**
   * Test for the {@link CameraInputProcessor#zoom(float)} method zoom lower than min
   */
  @DisplayName("Zoom: Lower than min")
  @Test
  void testZoomLowerThanMin() {
    final float zoomAmount = (CameraInputProcessor.ZOOM_MIN - 2f ) * CameraInputProcessor.ZOOM_FACTOR;

    victim.zoom(zoomAmount);
    assertEquals(CameraInputProcessor.ZOOM_MIN, cameraMock.zoom);
    verify(cameraMock).update();
  }

  /**
   * Test for the {@link CameraInputProcessor#process(float, float, int)} method with no button
   */
  @DisplayName("Process: No button")
  @Test
  void testProcessNoButton() {
    victim.process(0,0,0);
    verify(cameraMock).update();
  }

  /**
   * Test for the {@link CameraInputProcessor#process(float, float, int)} method
   */
  @DisplayName("Process")
  @Test
  void testProcess() {
    victim.process(2,2, Input.Buttons.LEFT);
    verify(cameraMock, times(2)).translate(any(Vector3.class));
    verify(cameraMock).update();
  }

}
