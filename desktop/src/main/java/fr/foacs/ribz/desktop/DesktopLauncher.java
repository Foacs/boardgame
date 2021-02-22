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

package fr.foacs.ribz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.foacs.ribz.backend.impl.RibzBackend;
import fr.foacs.ribz.event.controller.EventController;
import fr.foacs.ribz.frontend.impl.RibzFrontend;
import fr.foacs.ribz.frontend.impl.screens.BoardGameScreens;
import fr.foacs.ribz.response.controller.ResponseController;

/**
 * Desktop application entry point.
 * Initialises {@link fr.foacs.ribz.backend.api.Backend} and {@link fr.foacs.ribz.frontend.api.Frontend}
 * with {@link fr.foacs.ribz.core.event.MessageListener}.
 * <br>
 * Initialises {@link fr.foacs.ribz.core.event.MessageController} with {@link fr.foacs.ribz.backend.api.Backend}
 * and {@link fr.foacs.ribz.frontend.api.Frontend}.
 * <br>
 * Instantiate a new {@link LwjglApplication}.
 *
 * @since 0.1
 */
public class DesktopLauncher {

  /**
   * Entry point.
   * Creates a {@link LwjglApplication}.
   *
   * @param args Application arguments
   */
  public static void main(final String[] args) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = 1920;
    config.height = 1080;
    RibzFrontend.getInstance().setSplashScreenWorker(new DesktopSplashScreenWorker());
    RibzFrontend.getInstance().setGameScreens(BoardGameScreens.BOARD);

    // Sets frontend and backend in controller
    EventController.getInstance().setBackend(RibzBackend.getInstance());
    ResponseController.getInstance().setFrontend(RibzFrontend.getInstance());

    // Sets message listener in backend and frontend
    RibzFrontend.getInstance().setMessageListener(EventController.getInstance());
    RibzBackend.getInstance().setMessageListener(ResponseController.getInstance());

    new LwjglApplication(ResponseController.getInstance(), config);
  }
}
