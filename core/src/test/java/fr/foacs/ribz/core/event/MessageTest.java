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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.signum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link Message} class.
 *
 * @since 0.1
 */
@DisplayName("Message")
class MessageTest {

  /**
   * Test for {@link Message#compareTo(Message)} method.
   * Ensure sgn(x.compareTo(y)) == -sng(y.compareTo(x)).
   */
  @DisplayName("Compare to: Reversal")
  @Test
  void testCompareToReversal() {

    final Message highPriorityEvent = new MessageTestImpl((short) 10);
    final Message lowPriorityEvent = new MessageTestImpl((short) 100);

    assertEquals(signum(highPriorityEvent.compareTo(lowPriorityEvent)), -signum(lowPriorityEvent.compareTo(highPriorityEvent)));
  }

  /**
   * Test for {@link Message#compareTo(Message)} method.
   * Ensure (x.compareTo(y) >0 && y.compareTo(z)) implies x.compareTo(z) > 0.
   */
  @DisplayName("Compare to: Transitivity")
  @Test
  void testCompareToTransitive() {
    final Message highPriorityEvent = new MessageTestImpl((short) 10);
    final Message mediumPriorityEvent = new MessageTestImpl((short) 100);
    final Message lowPriorityEvent = new MessageTestImpl((short) 200);

    assertTrue(highPriorityEvent.compareTo(mediumPriorityEvent) < 0 && mediumPriorityEvent.compareTo(lowPriorityEvent) < 0);
    assertTrue(highPriorityEvent.compareTo(lowPriorityEvent) < 0);

  }

  /**
   * Test for {@link Message#compareTo(Message)} method.
   * Ensure x.compareTo(y) == 0 implies sgn(x.compareTo(z)) == sgn(y.compareTo(z)).
   */
  @DisplayName("Compare to: Consistency")
  @Test
  void testCompareToConsistency() {
    final Message high1PriorityEvent = new MessageTestImpl((short) 10);
    final Message high2PriorityEvent = new MessageTestImpl((short) 10);
    final Message lowPriorityEvent = new MessageTestImpl((short) 200);

    assertEquals(0, high1PriorityEvent.compareTo(high2PriorityEvent));
    assertEquals(signum(high1PriorityEvent.compareTo(lowPriorityEvent)), signum(high2PriorityEvent.compareTo(lowPriorityEvent)));
  }
}
