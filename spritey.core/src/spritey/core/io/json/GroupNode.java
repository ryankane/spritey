/**
 * This source file is part of Spritey - the sprite sheet creator.
 *
 * Copyright 2011 Maksym Bykovskyy.
 *
 * Spritey is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Spritey is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Spritey. If not, see <http://www.gnu.org/licenses/>.
 */
package spritey.core.io.json;

import java.util.List;

/**
 *
 */
public class GroupNode implements ParentNode {
    private String name;
    private List<GroupNode> groups;
    private List<SpriteNode> sprites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupNode> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupNode> groups) {
        this.groups = groups;
    }

    public List<SpriteNode> getSprites() {
        return sprites;
    }

    public void setSprites(List<SpriteNode> sprites) {
        this.sprites = sprites;
    }
}
