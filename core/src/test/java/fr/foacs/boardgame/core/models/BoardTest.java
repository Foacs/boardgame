/*
 * Copyright or © or Copr. Foacs
 * contributor(s): Alexis DINQUER (16/09/2020 20:20)
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

package fr.foacs.boardgame.core.models;

import fr.foacs.boardgame.core.screens.MockAssetManager;
import fr.foacs.boardgame.core.screens.MockTiledMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class BoardTest {

  private static final String BOARD_NAME = "MySuperDuperBoard";
  private Board victim;
  private MockAssetManager mockAssetManager = new MockAssetManager();

  @BeforeEach
  void setup() {
    victim = new Board(BOARD_NAME, mockAssetManager);
  }

  @Test
  void test_getBoardMap_NotNull() {
    assertNotNull(victim.getBoardMap());
  }

  @Test
  void test_getBoardMap() {
    assertSame(mockAssetManager.getMockedTiledMap(), victim.getBoardMap());
  }

  @Test
  void test_getTileWidth() {
    assertEquals(MockTiledMap.TILE_WIDTH, victim.getTileWidth());
  }

  @Test
  void test_getTileHeight() {
    assertEquals(MockTiledMap.TILE_HEIGHT, victim.getTileHeight());
  }

  @Test
  void test_getMapWidth() {
    assertEquals(MockTiledMap.MAP_WIDTH, victim.getMapWidth());
  }

  @Test
  void test_getMapHeight() {
    assertEquals(MockTiledMap.MAP_HEIGHT, victim.getMapHeight());
  }

  @Test
  void test_getPixMapWidth() {
    assertEquals(MockTiledMap.TILE_WIDTH * MockTiledMap.MAP_WIDTH, victim.getPixMapWidth());
  }

  @Test
  void test_getPixMapHeight() {
    assertEquals(MockTiledMap.TILE_HEIGHT * MockTiledMap.MAP_HEIGHT, victim.getPixMapHeight());
  }
}
