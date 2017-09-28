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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spritey.core.Messages;
import spritey.core.Sheet;
import spritey.core.io.json.CollectionAdapter;
import spritey.core.io.json.NodeBuilderUtil;
import spritey.core.io.json.node.ParentNode;

public class JsonWriter implements Writer {
    @Override
    public void write(Sheet sheet, File file)
            throws FileNotFoundException, IOException {
        if (sheet == null) {
            throw new IllegalArgumentException(Messages.NULL);
        }

        write(NodeBuilderUtil.build(sheet), file);
    }

    protected void write(ParentNode sheetNode, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeHierarchyAdapter(Collection.class,
                        new CollectionAdapter())
                .create();
        String json = gson.toJson(sheetNode);
        FileWriter writer = new FileWriter(file);

        writer.write(json);
        writer.close();
    }
}
