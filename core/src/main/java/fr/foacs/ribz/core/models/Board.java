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

package fr.foacs.ribz.core.models;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import lombok.Getter;

/**
 * Board entity - contains the board map and its properties.
 *
 * @since 0.1
 */
@Getter
public class Board {

  /** The Scale unit used to determine map size (in pixel). */
  public static final float SCALE_UNIT = 0.8f;

  private final TiledMap boardMap;
  private final int tileWidth;
  private final int tileHeight;
  private final int mapWidth;
  private final int mapHeight;
  private final int pixMapWidth;
  private final int pixMapHeight;

  /**
   * Create a board from its name.
   * Retrieve the board map from assets.
   *
   * @param boardName    The board name.
   * @param assetManager The asset manager to use to retrieve board map.
   */
  public Board(final String boardName, final AssetManager assetManager) {
    this.boardMap = assetManager.get("boards/" + boardName + ".tmx", TiledMap.class);
    this.tileWidth = this.boardMap.getProperties().get("tilewidth", 0, Integer.class);
    this.tileHeight = this.boardMap.getProperties().get("tileheight", 0, Integer.class);
    this.mapWidth = this.boardMap.getProperties().get("width", 0, Integer.class);
    this.mapHeight = this.boardMap.getProperties().get("height", 0, Integer.class);
    this.pixMapWidth = this.mapWidth * this.tileWidth;
    this.pixMapHeight = this.mapHeight * this.tileHeight;
  }

}
