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

package fr.foacs.ribz.event.controller;

import fr.foacs.ribz.backend.api.Backend;
import fr.foacs.ribz.core.event.MessageController;
import fr.foacs.ribz.core.utils.PropertiesLoader;
import fr.foacs.ribz.event.controller.events.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.Properties;

/**
 * Controller of {@link Event}.<br>
 * Can received event and store it in a queue.<br>
 * It should be initialised with:
 * {@code ResponseController.getInstance().setFrontend(frontendInstance)}.
 *
 * @since 0.1
 */
@Slf4j
public class EventController extends MessageController<Event> implements Runnable {

  @Getter
  private static final EventController instance = new EventController();
  private final short tps;
  @Getter
  @Setter
  private Backend backend;
  private Thread mainLoop;
  private boolean running;
  private long lastTime;

  private EventController() {
    super();
    final Properties runnerProperties = PropertiesLoader.loadProperties("runner");
    this.tps = Short.parseShort(runnerProperties.getProperty("tps", "20"));
    log.info("Runs with tps value {}", tps);
    this.running = true;
    mainLoop = new Thread(this);
    mainLoop.start();
  }

  /**
   * Runs the controller.<br>
   * Loops &amp; {@link EventController#tick()} x times per second.<br>
   * Where x is tps.
   */
  @Override
  public void run() {
    while (running) {
      final long time = System.nanoTime();
      final double delta = ((time - lastTime) / 1_000_000_000.0);
      if (1.0 / tps <= delta) {
        lastTime = time;
        this.tick();
      }
    }
  }

  /**
   * Do a tick.<br>
   * Poll top event in queue and handle it.
   */
  public void tick() {
    getMessageQueue().poll().ifPresent((Event event) -> {
      log.debug("Treat event {}", event);
      EventHandlerFactory.getInstance().getHandlerSet(event.getClass())
          .forEach(eventHandler -> eventHandler.handleMessage(event));
    });
  }

  /**
   * Disposes the controller.
   */
  public void dispose() {
    this.running = false;
  }
}
