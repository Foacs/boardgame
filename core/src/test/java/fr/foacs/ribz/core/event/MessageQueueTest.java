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

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link MessageQueue} class.
 *
 * @since 0.1
 */
@DisplayName("Message Queue")
class MessageQueueTest {

  private final static int NUM_THREAD = 10;

  private final MessageQueue<MessageTestImpl> victim = new MessageQueue<>();

  /**
   * Test for {@link MessageQueue#add(Message)} method.
   */
  @DisplayName("Add")
  @Test
  void testAdd() {
    final MessageTestImpl event = mock(MessageTestImpl.class);

    victim.add(event);

    final Optional<MessageTestImpl> polledEvent = victim.poll();

    assertTrue(polledEvent.isPresent());
    assertEquals(event, polledEvent.get());
  }

  /**
   * Test for {@link MessageQueue#add(Message)} method.
   * Synchronized test.
   */
  @SneakyThrows
  @DisplayName("Add - synchronized test")
  @Test
  void testAddSynchronized() {
    final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREAD);

    for (int i = 0; i < NUM_THREAD; i++) {
      executor.submit(() -> victim.add(mock(MessageTestImpl.class)));
    }

    executor.shutdown();

    int queueSize = 0;
    if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
      while (victim.poll().isPresent()) {
        queueSize++;
      }
    }

    assertEquals(NUM_THREAD, queueSize);
  }

  /**
   * Test for {@link MessageQueue#poll()} method.
   * Empty queue.
   */
  @DisplayName("Poll: empty queue")
  @Test
  void testPollEmpty() {
    final Optional<MessageTestImpl> polledEvent = victim.poll();
    assertFalse(polledEvent.isPresent());
  }

  /**
   * Test for {@link MessageQueue#poll()} method.
   * Synchronized test.
   */
  @SneakyThrows
  @DisplayName("Poll - synchronized test")
  @Test
  void testPollSynchronized() {
    final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREAD);
    final MessageTestImpl[] expectedMessageArray = new MessageTestImpl[NUM_THREAD];
    final List<MessageTestImpl> resultMessageList = new ArrayList<>(NUM_THREAD);

    for (int i = 0; i < NUM_THREAD; i++) {
      final MessageTestImpl mockMessage = mock(MessageTestImpl.class);
      victim.add(mockMessage);
      expectedMessageArray[i] = mockMessage;
    }

    final List<Future<MessageTestImpl>> futureList = new ArrayList<>();
    for (int i = 0; i < NUM_THREAD; i++) {
      futureList.add(executor.submit(() -> victim.poll().orElse(null)));
    }

    for (Future<MessageTestImpl> future : futureList) {
      resultMessageList.add(future.get());
    }

    executor.shutdown();
    assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
    assertTrue(resultMessageList.containsAll(Arrays.asList(expectedMessageArray)));
  }


  /**
   * Test for {@link MessageQueue#add(Message)} method.
   * Order by priority.
   */
  @DisplayName("Add: order by priority")
  @Test
  void testAddOrderByPriority() {
    final MessageTestImpl highPriorityEvent = new MessageTestImpl((short) 0);
    final MessageTestImpl mediumPriorityEvent = new MessageTestImpl((short) 10);
    final MessageTestImpl lowPriorityEvent = new MessageTestImpl((short) 100);
    final MessageTestImpl veryLowPriorityEvent = new MessageTestImpl((short) 1000);

    victim.add(veryLowPriorityEvent);
    victim.add(mediumPriorityEvent);
    victim.add(highPriorityEvent);
    victim.add(lowPriorityEvent);

    final Optional<MessageTestImpl> firstPolledEvent = victim.poll();
    assertTrue(firstPolledEvent.isPresent());
    assertEquals(highPriorityEvent, firstPolledEvent.get());

    final Optional<MessageTestImpl> secondPolledEvent = victim.poll();
    assertTrue(secondPolledEvent.isPresent());
    assertEquals(mediumPriorityEvent, secondPolledEvent.get());

    final Optional<MessageTestImpl> thirdPolledEvent = victim.poll();
    assertTrue(thirdPolledEvent.isPresent());
    assertEquals(lowPriorityEvent, thirdPolledEvent.get());

    final Optional<MessageTestImpl> forthPolledEvent = victim.poll();
    assertTrue(forthPolledEvent.isPresent());
    assertEquals(veryLowPriorityEvent, forthPolledEvent.get());
  }

}
