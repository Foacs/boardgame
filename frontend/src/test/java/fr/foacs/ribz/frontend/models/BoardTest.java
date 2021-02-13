/*
 * Copyright or © or Copr. Foacs
 * contributor(s):
 * - Alexis DINQUER adinquer@yahoo.com
 * - Brice KESSLER brice.kessler@outlook.com
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

package fr.foacs.ribz.frontend.models;

import fr.foacs.ribz.frontend.mocks.AssetManagerMock;
import fr.foacs.ribz.frontend.mocks.TiledMapMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Test class for the {@link Board} class.
 */
@DisplayName("Board")
class BoardTest {

  private static final String BOARD_NAME = "MySuperDuperBoard";
  private Board victim;
  private final AssetManagerMock assetManagerMock = new AssetManagerMock();

  @BeforeEach
  void setup() {
    victim = new Board(BOARD_NAME, assetManagerMock);
  }

  /**
   * Test the {@link Board#getBoardMap()} method return not null.
   */
  @DisplayName("Get board map: return not null")
  @Test
  void testGetBoardMapNotNull() {
    assertNotNull(victim.getBoardMap());
  }

  /**
   * Test the {@link Board#getBoardMap()} method
   */
  @DisplayName("Get board map")
  @Test
  void testGetBoardMap() {
    assertSame(assetManagerMock.getMockedTiledMap(), victim.getBoardMap());
  }

  /**
   * Test the {@link Board#getTileHeight()} method
   */
  @DisplayName("Get tile width")
  @Test
  void test_getTileWidth() {
    assertEquals(TiledMapMock.TILE_WIDTH, victim.getTileWidth());
  }

  /**
   * Test the {@link Board#getTileHeight()} method
   */
  @DisplayName("Get tile height")
  @Test
  void test_getTileHeight() {
    assertEquals(TiledMapMock.TILE_HEIGHT, victim.getTileHeight());
  }

  /**
   * Test the {@link Board#getMapWidth()} method
   */
  @DisplayName("Get map width")
  @Test
  void testgetMapWidth() {
    assertEquals(TiledMapMock.MAP_WIDTH, victim.getMapWidth());
  }

  /**
   * Test the {@link Board#getMapHeight()} method
   */
  @DisplayName("Get map height")
  @Test
  void testgetMapHeight() {
    assertEquals(TiledMapMock.MAP_HEIGHT, victim.getMapHeight());
  }

  /**
   * Test the {@link Board#getPixMapWidth()} method
   */
  @DisplayName("Get pix map width")
  @Test
  void testGetPixMapWidth() {
    assertEquals(TiledMapMock.TILE_WIDTH * TiledMapMock.MAP_WIDTH, victim.getPixMapWidth());
  }

  /**
   * Test the {@link Board#getPixMapHeight()} method
   */
  @DisplayName("Get pix map height")
  @Test
  void testGetPixMapHeight() {
    assertEquals(TiledMapMock.TILE_HEIGHT * TiledMapMock.MAP_HEIGHT, victim.getPixMapHeight());
  }
}
