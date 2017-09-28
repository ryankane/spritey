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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import spritey.core.Group;
import spritey.core.Node;
import spritey.core.Sheet;
import spritey.core.Sprite;
import spritey.core.filter.VisibleSpriteFilter;
import spritey.core.io.json.node.ParentNode;
import spritey.core.io.json.node.GroupNode.GroupNodeBuilder;
import spritey.core.io.json.node.SheetNode.SheetNodeBuilder;
import spritey.core.io.json.node.SpriteNode.SpriteNodeBuilder;

/**
 *
 */
public class NodeBuilderUtil {
    public static void addNode(ParentBuilder builder, Node node) {
        if (node instanceof Sprite) {
            Sprite sprite = (Sprite) node;
            Point location = sprite.getLocation();

            if (sprite.isVisible()) {
                Dimension size = sprite.getSize();
                String name = sprite.getName();
                SpriteNodeBuilder spriteBuilder = new SpriteNodeBuilder(name,
                        size, location);

                builder.withSprite(spriteBuilder.build());
            }
        } else if (node instanceof Group) {
            // Skip group when it doesn't contain at least one visible sprite.
            if (new VisibleSpriteFilter().filter(node).length > 0) {
                GroupNodeBuilder groupBuilder = new GroupNodeBuilder(
                        node.getName());

                for (Node child : node.getChildren()) {
                    addNode(groupBuilder, child);
                }

                builder.withGroup(groupBuilder.build());
            }
        }
    }

    public static ParentNode build(Sheet sheet) {
        Color bg = sheet.getBackground();
        String bgStr = String.format("%d, %d, %d, %d", bg.getRed(),
                bg.getGreen(), bg.getBlue(), bg.getAlpha());
        SheetNodeBuilder builder = new SheetNodeBuilder(sheet.getWidth(),
                sheet.getHeight(), bgStr, sheet.getDescription());

        for (Node child : sheet.getChildren()) {
            addNode(builder, child);
        }

        return builder.build();
    }
}
