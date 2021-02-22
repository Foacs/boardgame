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

package fr.foacs.ribz.backend.impl;

import fr.foacs.ribz.backend.api.Backend;
import fr.foacs.ribz.core.event.MessageListener;
import fr.foacs.ribz.response.controller.responses.Response;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Entry point of Ribz back end.<br>
 * It should be initialised with:
 * {@code RibzBackend.getInstance().setMessageListener(messageListenerInstance)}.
 *
 * @since 0.1
 */
@Slf4j
public class RibzBackend implements Backend {

  @Getter
  @NonNull
  private static final RibzBackend instance = new RibzBackend();

  @Getter
  @Setter
  private MessageListener<Response> messageListener;

  /**
   * Hides the default constructor.
   */
  private RibzBackend() {
    log.info("Starting RIBZ backend.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void init() {
    log.info("Init backend");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void pause() {
    log.info("Pause backend");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void resume() {
    log.info("Resume backend");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    log.info("Dispose backend");
  }
}
