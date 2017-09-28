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

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 */
public class SpriteNode {
    private String name;
    private int width;
    private int height;
    private int x;
    private int y;

    private SpriteNode(String name, int width, int height, int x, int y) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static class SpriteNodeBuilder {
        private String name = null;
        private int width = 0;
        private int height = 0;
        private int x = 0;
        private int y = 0;

        public SpriteNodeBuilder(final String name, final Dimension size,
                final Point location) {
            this(name, size.width, size.height, location.x, location.y);
        }

        public SpriteNodeBuilder(final String name, final int width,
                final int height, final int x, final int y) {
            this.name = name;
            this.width = 0;
            this.height = 0;
            this.x = 0;
            this.y = 0;
        }

        public SpriteNodeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SpriteNodeBuilder withWidth(int width) {
            this.width = width;
            return this;
        }

        public SpriteNodeBuilder withHeight(int height) {
            this.height = height;
            return this;
        }

        public SpriteNodeBuilder withX(int x) {
            this.x = x;
            return this;
        }

        public SpriteNodeBuilder withY(int y) {
            this.y = y;
            return this;
        }

        public SpriteNode build() {
            return new SpriteNode(name, width, height, x, y);
        }
    }
}
