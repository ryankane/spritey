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
public class SheetNode implements ParentNode {
    private String background;
    private String description;
    private int height;
    private int width;
    private List<GroupNode> groups;
    private List<SpriteNode> sprites;

    private SheetNode(String background, String description, int height,
            int width, List<GroupNode> groups, List<SpriteNode> sprites) {
        this.background = background;
        this.description = description;
        this.height = height;
        this.width = width;
        this.groups = groups;
        this.sprites = sprites;
    }

    public String getBackground() {
        return background;
    }

    public String getDescription() {
        return description;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<GroupNode> getGroups() {
        return groups;
    }

    public List<SpriteNode> getSprites() {
        return sprites;
    }

    public static class SheetNodeBuilder implements ParentBuilder {
        private String background = null;
        private String description = null;
        private int height = 0;
        private int width = 0;
        private List<GroupNode> groups = new ArrayList<GroupNode>();
        private List<SpriteNode> sprites = new ArrayList<SpriteNode>();

        public SheetNodeBuilder(int height, int width, String background,
                String description) {
            this.background = background;
            this.description = description;
            this.height = height;
            this.width = width;
        }

        public SheetNodeBuilder withBackground(String background) {
            this.background = background;
            return this;
        }

        public SheetNodeBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SheetNodeBuilder withHeight(int height) {
            this.height = height;
            return this;
        }

        public SheetNodeBuilder withWidth(int width) {
            this.width = width;
            return this;
        }

        public SheetNodeBuilder withGroup(GroupNode group) {
            groups.add(group);
            return this;
        }

        public SheetNodeBuilder withSprite(SpriteNode sprite) {
            sprites.add(sprite);
            return this;
        }

        public SheetNode build() {
            return new SheetNode(background, description, height, width, groups,
                    sprites);
        }
    }
}
