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
package spritey.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests the implementation of Group.
 */
public class GroupTests {

    @Test
    public void defaultConstructor() {
        assertEquals(Group.DEFAULT_NAME, new Group().getName());
    }

    @Test
    public void constructor() {
        assertEquals("GROUP1", new Group("GROUP1").getName());
    }

}
