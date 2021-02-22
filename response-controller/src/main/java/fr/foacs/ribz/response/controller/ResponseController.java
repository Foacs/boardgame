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

package fr.foacs.ribz.response.controller;

import com.badlogic.gdx.ApplicationListener;
import fr.foacs.ribz.core.event.MessageController;
import fr.foacs.ribz.frontend.api.Frontend;
import fr.foacs.ribz.response.controller.responses.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;

/**
 * Controller for {@link Response}.<br>
 * Can received response and store it in a queue.<br>
 * It should be initialised with:
 * {@code ResponseController.getInstance().setFrontend(frontendInstance)}.
 *
 * @since 0.1
 */
@Slf4j
public class ResponseController extends MessageController<Response> implements ApplicationListener {

  @Getter
  private static final ResponseController instance = new ResponseController();

  @Setter
  @Getter
  private Frontend frontend;

  private ResponseController() {
    super();
  }

  /**
   * Calls {@link Frontend#init()} if it is set.<br>
   * {@inheritDoc}
   */
  @Override
  public void create() {
    if (Objects.nonNull(frontend)) {
      frontend.init();
    } else {
      log.warn("No frontend is set for this response controller. Unforeseen behaviour may happen.");
    }
  }

  /**
   * Calls {@link Frontend#resize(int, int)} with given parameters.<br>
   * {@inheritDoc}
   */
  @Override
  public void resize(int width, int height) {
    if (Objects.nonNull(frontend)) {
      frontend.resize(width, height);
    }
  }

  /**
   * Polls event in queue &amp; call {@link Frontend#render()}. <br>
   * {@inheritDoc}
   */
  @Override
  public void render() {
    // Handle response from backend
    getMessageQueue().poll().ifPresent(response ->
        ResponseHandlerFactory.getInstance().getHandlerSet(response.getClass())
            .forEach(handler -> handler.handleMessage(response)));
    // Trigger render of frontend
    if (Objects.nonNull(frontend)) {
      frontend.render();
    }
  }

  /**
   * Calls {@link Frontend#pause()}.<br>
   * {@inheritDoc}
   */
  @Override
  public void pause() {
    if (Objects.nonNull(frontend)) {
      frontend.pause();
    }
  }

  /**
   * Calls {@link Frontend#resume()}.<br>
   * {@inheritDoc}
   */
  @Override
  public void resume() {
    if (Objects.nonNull(frontend)) {
      frontend.resume();
    }
  }

  /**
   * Calls {@link Frontend#dispose()}.<br>
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    if (Objects.nonNull(frontend)) {
      frontend.dispose();
    }
  }
}
