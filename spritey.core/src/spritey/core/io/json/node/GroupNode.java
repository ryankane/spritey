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
package spritey.core.io.json.node;

import java.util.ArrayList;
import java.util.List;

import spritey.core.io.json.ParentBuilder;

/**
 *
 */
public class GroupNode implements ParentNode {
    private String name;
    private List<GroupNode> groups;
    private List<SpriteNode> sprites;

    private GroupNode(String name, List<GroupNode> groups,
            List<SpriteNode> sprites) {
        this.name = name;
        this.groups = groups;
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public List<GroupNode> getGroups() {
        return groups;
    }

    public List<SpriteNode> getSprites() {
        return sprites;
    }

    public static class GroupNodeBuilder implements ParentBuilder {
        private String name = null;
        private List<GroupNode> groups = new ArrayList<GroupNode>();
        private List<SpriteNode> sprites = new ArrayList<SpriteNode>();

        public GroupNodeBuilder(String name) {
            this.name = name;
        }

        public GroupNodeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GroupNodeBuilder withGroup(GroupNode group) {
            groups.add(group);
            return this;
        }

        public GroupNodeBuilder withSprite(SpriteNode sprite) {
            sprites.add(sprite);
            return this;
        }

        public GroupNode build() {
            return new GroupNode(name, groups, sprites);
        }
    }
}
