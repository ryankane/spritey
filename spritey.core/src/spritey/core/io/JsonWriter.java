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
package spritey.core.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spritey.core.Group;
import spritey.core.Messages;
import spritey.core.Node;
import spritey.core.Sheet;
import spritey.core.Sprite;
import spritey.core.filter.VisibleSpriteFilter;
import spritey.core.io.json.GroupNode;
import spritey.core.io.json.ParentNode;
import spritey.core.io.json.SheetNode;
import spritey.core.io.json.SpriteNode;

public class JsonWriter implements Writer {
    @Override
    public void write(Sheet sheet, File file)
            throws FileNotFoundException, IOException {
        if (sheet == null) {
            throw new IllegalArgumentException(Messages.NULL);
        }

        write(build(sheet), file);
    }

    protected void addNode(ParentNode parentNode, Node node) {
        if (node instanceof Sprite) {
            Sprite sprite = (Sprite) node;
            Point location = sprite.getLocation();

            if (sprite.isVisible()) {
                Dimension size = sprite.getSize();
                String name = sprite.getName();

                SpriteNode spriteNode = new SpriteNode();

                spriteNode.setName(name);
                spriteNode.setWidth(size.width);
                spriteNode.setHeight(size.height);
                spriteNode.setX(location.x);
                spriteNode.setY(location.y);

                if (parentNode.getSprites() == null) {
                    parentNode.setSprites(new ArrayList<SpriteNode>());
                }

                parentNode.getSprites().add(spriteNode);
            }
        } else if (node instanceof Group) {
            // Skip group when it doesn't contain at least one visible sprite.
            if (new VisibleSpriteFilter().filter(node).length > 0) {
                GroupNode groupNode = new GroupNode();

                groupNode.setName(node.getName());

                if (parentNode.getGroups() == null) {
                    parentNode.setGroups(new ArrayList<GroupNode>());
                }

                parentNode.getGroups().add(groupNode);

                for (Node child : node.getChildren()) {
                    addNode(groupNode, child);
                }
            }
        }
    }

    protected ParentNode build(Sheet sheet) {
        Color bg = sheet.getBackground();

        String bgStr = bg.getRed() + ", " + bg.getGreen() + ", " + bg.getBlue()
                + ", " + bg.getAlpha();

        SheetNode sheetNode = new SheetNode();

        sheetNode.setWidth(sheet.getWidth());
        sheetNode.setHeight(sheet.getHeight());
        sheetNode.setBackground(bgStr);
        sheetNode.setDescription(sheet.getDescription());

        for (Node child : sheet.getChildren()) {
            addNode(sheetNode, child);
        }

        return sheetNode;
    }

    protected void write(ParentNode sheetNode, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(sheetNode);
        FileWriter writer = new FileWriter(file);

        writer.write(json);
        writer.close();
    }
}
