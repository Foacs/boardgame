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

package fr.foacs.ribz.core.event;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Class for queued event. Events are ordered by priority.<br>
 * Action to dispatch event is listened by a {@link MessageListener}.
 *
 * @since 0.1
 */
@Slf4j
public class MessageQueue<T extends Message> {

  private final PriorityQueue<T> events;

  /**
   * Creates an event queue.
   */
  public MessageQueue() {
    this.events = new PriorityQueue<>();
  }

  /**
   * Polls the top priority event in the queue.
   *
   * @return An optional with top priority event, empty if no event is available.
   */
  @Nonnull
  public Optional<T> poll() {
    return Optional.ofNullable(events.poll());
  }

  /**
   * Tries to input an event in the queue. <br>
   * It is insert at the position corresponding to its priority.
   *
   * @param event The event to insert.
   * @return True if the event is properly insert (as specified in {@link java.util.Queue#offer(Object)}).
   */
  public boolean add(@Nonnull final T event) {
    return events.add(event);
  }

  /**
   * Clears the event queue.
   */
  public void clear() {
    log.warn("Clearing the event queue");
    events.clear();
  }
}
