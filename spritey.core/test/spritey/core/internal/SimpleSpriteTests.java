/**
 * This source file is part of Spritey - the sprite sheet creator.
 * 
 * Copyright 2010 Maksym Bykovskyy and Alan Morey.
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
package spritey.core.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import spritey.core.Sprite;
import spritey.core.event.ModelEvent;
import spritey.core.event.ModelListener;
import spritey.core.node.Node;
import spritey.core.validator.Validator;

/**
 * Tests the implementation of SimpleSprite.
 */
public class SimpleSpriteTests {
    Sprite sprite;
    ModelListener listenerMock;
    Validator validatorMock;
    Node nodeMock;

    @Captor
    ArgumentCaptor<ModelEvent> eventCaptor;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        listenerMock = mock(ModelListener.class);
        validatorMock = mock(Validator.class);
        nodeMock = mock(Node.class);

        sprite = new SimpleSprite();
        sprite.addValidator(Sprite.NAME, validatorMock);
        sprite.addValidator(Sprite.NODE, validatorMock);
        sprite.addValidator(Sprite.SIZE, validatorMock);
        sprite.addValidator(Sprite.LOCATION, validatorMock);
        sprite.addValidator(Sprite.IMAGE, validatorMock);
        sprite.addModelListener(listenerMock);
    }

    @Test
    public void setSizeToDimension() {
        final int PROPERTY = Sprite.SIZE;
        final Object VALUE = new Dimension(14, 15);

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setSizeToNull() {
        final int PROPERTY = Sprite.SIZE;
        final Object VALUE = null;

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setNameToString() {
        final int PROPERTY = Sprite.NAME;
        final Object VALUE = "NewSprite";

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setNameToNull() {
        final int PROPERTY = Sprite.NAME;
        final Object VALUE = null;

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setNodeToNode() {
        final int PROPERTY = Sprite.NODE;
        final Object VALUE = nodeMock;

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setNodeToNull() {
        final int PROPERTY = Sprite.NODE;
        final Object VALUE = null;

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setLocationToPoint() {
        final int PROPERTY = Sprite.LOCATION;
        final Object VALUE = new Point(10, 10);

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setLocationToNull() {
        final int PROPERTY = Sprite.LOCATION;
        final Object VALUE = null;

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setImageToBufferedImage() {
        final int PROPERTY = Sprite.IMAGE;
        final Object VALUE = new BufferedImage(32, 32,
                BufferedImage.TYPE_INT_RGB);

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test
    public void setImageToNull() {
        final int PROPERTY = Sprite.IMAGE;
        final Object VALUE = null;

        sprite.setProperty(PROPERTY, VALUE);

        assertEquals(VALUE, sprite.getProperty(PROPERTY));
        verify(validatorMock).isValid(VALUE);
        verify(listenerMock).propertyChanged(eventCaptor.capture());
        assertEquals(sprite, eventCaptor.getValue().getSource());
        assertEquals(PROPERTY, eventCaptor.getValue().getProperty());
        assertEquals(null, eventCaptor.getValue().getOldValue());
        assertEquals(VALUE, eventCaptor.getValue().getNewValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullPropertyListener() {
        sprite.addModelListener(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullValidator() {
        sprite.addValidator(Sprite.NODE, null);
    }

    @Test
    public void removePropertyListener() {
        sprite.removeModelListener(listenerMock);

        sprite.setProperty(Sprite.NAME, "Name");

        verify(listenerMock, times(0)).propertyChanged(any(ModelEvent.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullPropertyListener() {
        sprite.removeModelListener(null);
    }

    @Test
    public void removeValidator() {
        sprite.removeValidator(Sprite.NAME, validatorMock);

        sprite.setProperty(Sprite.NAME, "Name");

        verify(validatorMock, times(0)).isValid("Name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullValidator() {
        sprite.removeValidator(Sprite.NAME, null);
    }
}
