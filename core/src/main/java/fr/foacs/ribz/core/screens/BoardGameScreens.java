/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (14/09/2020 20:16)
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

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.foacs.boardgame.core.controllers.BoardGameController;
import java.util.function.BiFunction;

/**
 * Application applicable screens.
 * Can create a screen.
 *
 * @since 0.1
 */
public enum BoardGameScreens {

  BOARD(BoardScreen::new);

  private final BiFunction<BoardGameController, SpriteBatch, Screen> supplier;

  BoardGameScreens(BiFunction<BoardGameController, SpriteBatch, Screen> supplier) {
    this.supplier = supplier;
  }

  /**
   * Create a instance of the screen by using a controller.
   *
   * @param controller The controller to use.
   * @return The new screen instance
   */
  public Screen createScreen(BoardGameController controller) {
    return supplier.apply(controller, controller.getBatch());
  }

}
