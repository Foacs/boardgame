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

package fr.foacs.ribz.core.utils;


import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the {@link PropertiesLoader} class.
 */
@DisplayName("Properties loader")
class PropertiesLoaderTest {

  /**
   * Test the {@link PropertiesLoader#loadProperties(String)} method with null parameters
   */
  @DisplayName("Load properties: null parameter")
  @Test
  void testLoadPropertiesNullPropertiesName() {
    assertThrows(NullPointerException.class, () -> PropertiesLoader.loadProperties(null));
  }

  /**
   * Test the {@link PropertiesLoader#loadProperties(String)} method with empty parameters
   */
  @DisplayName("Load properties: empty parameter")
  @Test
  void testLoadPropertiesEmptyPropertiesName() {
    assertThrows(IllegalArgumentException.class, () -> PropertiesLoader.loadProperties(""));
  }

  /**
   * Test the {@link PropertiesLoader#loadProperties(String)} method with blank parameters
   */
  @DisplayName("Load properties: blank parameter")
  @Test
  void testLoadPropertiesBlankPropertiesName() {
    assertThrows(IllegalArgumentException.class, () -> PropertiesLoader.loadProperties("   "));
  }

  /**
   * Test the {@link PropertiesLoader#loadProperties(String)} method with invalid parameters
   */
  @DisplayName("Load properties: invalide parameter")
  @Test
  void testLoadPropertiesInvalidPropertiesName() {
    final Properties properties = PropertiesLoader.loadProperties("invalid");
    assertNotNull(properties);
    assertTrue(properties.entrySet().isEmpty());
  }

  /**
   * Test the {@link PropertiesLoader#loadProperties(String)} method
   */
  @DisplayName("Load properties")
  @Test
  void test_loadProperties_validPropertiesName() {
    final Properties properties = PropertiesLoader.loadProperties("loader_test");
    assertNotNull(properties);
    assertTrue(properties.containsKey("test"));
    assertEquals("A test value", properties.get("test"));
  }

  /**
   * Test the {@link PropertiesLoader} constructor
   */
  @DisplayName("Load properties constructor")
  @Test
  @SneakyThrows
  void testPropertiesLoaderConstructorThrows() {
      final Class<?> propertiesLoaderClass;
      propertiesLoaderClass = Class.forName("fr.foacs.ribz.core.utils.PropertiesLoader");
      final Constructor<?> declaredConstructor = propertiesLoaderClass.getDeclaredConstructors()[0];
      declaredConstructor.setAccessible(true);
      assertThrows(InvocationTargetException.class, declaredConstructor::newInstance);
  }
}
