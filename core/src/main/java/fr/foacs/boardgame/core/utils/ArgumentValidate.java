/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (13/09/2020 17:19)
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

package fr.foacs.boardgame.core.utils;

import com.google.common.base.Strings;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Used to validate method argument.
 *
 * @since v0.1
 */
public class ArgumentValidate {

  private ArgumentValidate() {
    throw new UnsupportedOperationException("This class cannot be instantiate");
  }


  /**
   * Check if the given object is not null.
   * Use the default message.
   *
   * @param object The string to check.
   * @throws IllegalArgumentException if the given object is null
   */
  public static void notNull(@Nullable final Object object) {
    ArgumentValidate.notNull(object, "Please provide a non null parameter.");
  }

  /**
   * Check if the given object is not null.
   *
   * @param object  The object to check.
   * @param message The thrown message.
   * @throws IllegalArgumentException if the given object is null
   */
  public static void notNull(@Nullable final Object object, @Nonnull final String message) {
    if (Objects.isNull(object)) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Check if the given string is not null and not empty.
   * Empty string is a string with length equals 0.
   * Use the default message.
   *
   * @param string The string to check.
   * @throws IllegalArgumentException if the given string is empty
   */
  public static void notEmpty(@Nullable final String string) {
    notEmpty(string, "Please provide a non null and non empty string parameter.");
  }

  /**
   * Check if the given string is not null and not empty.
   * Empty string is a string with length equals 0.
   *
   * @param string  The string to check.
   * @param message The thrown message.
   * @throws IllegalArgumentException if the given string is empty
   */
  public static void notEmpty(@Nullable final String string, @Nonnull final String message) {
    if (Strings.isNullOrEmpty(string)) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Check if the given string is not null, not empty and not blank.
   * Blank string is a string with only space.
   * Use the default message.
   *
   * @param string The string to check.
   * @throws IllegalArgumentException if the given string is blank
   * @see ArgumentValidate#notEmpty(String)
   */
  public static void notBlank(@Nullable final String string) {
    ArgumentValidate.notBlank(string, "Please provide a non null, non empty and non blank parameter.");
  }

  /**
   * Check if the given string is not null, not empty and not blank.
   * Blank string is a string with only space.
   *
   * @param string  The string to check.
   * @param message The thrown message.
   * @throws IllegalArgumentException if the given string is blank
   * @see ArgumentValidate#notEmpty(String, String)
   */
  public static void notBlank(@Nullable final String string, @Nonnull final String message) {
    if (Strings.isNullOrEmpty(string) || string.trim().isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Check if the given integer is a natural number (not negative).
   * Use the default message.
   *
   * @param integer The integer to check.
   * @throws IllegalArgumentException when the given integer is negative.
   * @see ArgumentValidate#naturalInt(int, String)
   */
  public static void naturalInt(final int integer) {
    ArgumentValidate.naturalInt(integer, "Please provide a natural integer (not negative).");
  }

  /**
   * Check if the given integer is a natural number (not negative).
   *
   * @param integer The integer to check.
   * @param message The thrown message.
   * @throws IllegalArgumentException when the given integer is negative.
   * @see ArgumentValidate#naturalInt(int)
   */
  public static void naturalInt(final int integer, final String message) {
    if (integer < 0) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Check if the given integer is a natural number (not negative) and not equals to 0.
   * Use the default message.
   *
   * @param integer The integer to check.
   * @throws IllegalArgumentException when the given integer is negative or equals to 0.
   * @see ArgumentValidate#naturalNotNullInt(int, String)
   */
  public static void naturalNotNullInt(final int integer) {
    ArgumentValidate.naturalNotNullInt(integer, "Please provide a natural integer (not negative) and not null.");
  }

  /**
   * Check if the given integer is a natural number (not negative) and not equals to 0.
   *
   * @param integer The integer to check.
   * @param message The thrown message.
   * @throws IllegalArgumentException when the given integer is negative or equals to 0.
   * @see ArgumentValidate#naturalNotNullInt(int)
   */
  public static void naturalNotNullInt(final int integer, final String message) {
    if (integer <= 0) {
      throw new IllegalArgumentException(message);
    }
  }
}
