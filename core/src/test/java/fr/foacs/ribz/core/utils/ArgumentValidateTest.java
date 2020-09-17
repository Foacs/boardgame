/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (17/09/2020 19:09)
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

package fr.foacs.ribz.core.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArgumentValidateTest {
  private static final int NON_NATURAL_INT = -6;
  private static final int NATURAL_INT = 6;
  private static final int ZERO = 0;
  //NOT NULL

  @Test
  void notNullNullArgument() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notNull(null, ""));
  }

  @Test
  void notNullNotNullArgument() {
    assertDoesNotThrow(() -> ArgumentValidate.notNull(new Object(), ""));
  }

  @Test
  void notNullNullArgumentWithoutMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notNull(null));
  }

  @Test
  void notNullNotNullArgumentWithoutMessage() {
    assertDoesNotThrow(() -> ArgumentValidate.notNull(new Object()));
  }

  //NOT EMPTY

  @Test
  void notEmptyNullArgument() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notEmpty(null, ""));
  }

  @Test
  void notEmptyEmptyArgument() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notEmpty("", ""));
  }

  @Test
  void notEmptyBlankArgument() {
    assertDoesNotThrow(() -> ArgumentValidate.notEmpty("  ", ""));
  }

  @Test
  void notEmptyConcreteArgument() {
    assertDoesNotThrow(() -> ArgumentValidate.notEmpty("An argument", ""));
  }


  @Test
  void notEmptyNullArgumentWithoutMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notEmpty(null));
  }

  @Test
  void notEmptyEmptyArgumentWithoutMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notEmpty(""));
  }

  @Test
  void notEmptyBlankArgumentWithoutMessage() {
    assertDoesNotThrow(() -> ArgumentValidate.notEmpty("  "));
  }

  @Test
  void notEmptyConcreteArgumentWithoutMessage() {
    assertDoesNotThrow(() -> ArgumentValidate.notEmpty("An argument"));
  }

  // NOT BLANK

  @Test
  void notBlankNullArgument() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notBlank(null, ""));
  }

  @Test
  void notBlankEmptyArgument() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notBlank("", ""));
  }

  @Test
  void notBlankBlankArgument() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notBlank("  ", ""));
  }

  @Test
  void notBlankConcreteArgument() {
    assertDoesNotThrow(() -> ArgumentValidate.notBlank("An argument", ""));
  }


  @Test
  void notBlankNullArgumentWithoutMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notBlank(null));
  }

  @Test
  void notBlankEmptyArgumentWithoutMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notBlank(""));
  }

  @Test
  void notBlankBlankArgumentWithoutMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.notBlank("  "));
  }

  @Test
  void notBlankConcreteArgumentWithoutMessage() {
    assertDoesNotThrow(() -> ArgumentValidate.notBlank("An argument"));
  }


  // NATURAL INT
  @Test
  void naturalIntNotNatural() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.naturalInt(NON_NATURAL_INT, ""));
  }

  @Test
  void naturalIntZero() {
    assertDoesNotThrow(() -> ArgumentValidate.naturalInt(ZERO, ""));
  }

  @Test
  void naturalIntNaturalInt() {
    assertDoesNotThrow(() -> ArgumentValidate.naturalInt(NATURAL_INT, ""));
  }

  @Test
  void naturalIntNotNaturalWithouMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.naturalInt(NON_NATURAL_INT));
  }

  @Test
  void naturalIntZeroWithouMessage() {
    assertDoesNotThrow(() -> ArgumentValidate.naturalInt(ZERO));
  }

  @Test
  void naturalIntNaturalIntWithouMessage() {
    assertDoesNotThrow(() -> ArgumentValidate.naturalInt(NATURAL_INT));
  }

  // NATURAL INT NOT NULL
  @Test
  void naturalNotNullIntNotNatural() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.naturalNotNullInt(NON_NATURAL_INT, ""));
  }

  @Test
  void naturalNotNullIntZero() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.naturalNotNullInt(ZERO, ""));
  }

  @Test
  void naturalNotNullIntNaturalInt() {
    assertDoesNotThrow(() -> ArgumentValidate.naturalNotNullInt(NATURAL_INT, ""));
  }

  @Test
  void naturalNotNullIntNotNaturalWithouMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.naturalNotNullInt(NON_NATURAL_INT));
  }

  @Test
  void naturalNotNullIntZeroWithouMessage() {
    assertThrows(IllegalArgumentException.class, () -> ArgumentValidate.naturalNotNullInt(ZERO));
  }

  @Test
  void naturalNotNullIntNaturalIntWithouMessage() {
    assertDoesNotThrow(() -> ArgumentValidate.naturalNotNullInt(NATURAL_INT));
  }
}
