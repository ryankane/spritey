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
package spritey.rcp.wizards;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import spritey.core.Model;
import spritey.core.Sheet;
import spritey.rcp.core.SheetConstants;
import spritey.rcp.utils.ImageFactory;

/**
 * A main wizard page for creating a new sprite sheet.
 */
public class NewSpriteSheetMainPage extends WizardPage {

    static final String NAME = "Main";
    static final String TITLE = "Sprite Sheet";
    static final String DESCRIPTION = "Create a new sprite sheet.";

    static final String WIDTH_TEXT = "Width:";
    static final String HEIGHT_TEXT = "Height:";
    static final String BACKGROUND_TEXT = "Background:";
    static final String COMMENT_TEXT = "Comment:";
    static final String TRANSPARENT_TEXT = "Transparent";
    static final String CHOOSE_COLOR_TEXT = "Choose Color...";

    static final int DECIMAL_DIGITS = 0;
    static final int INCREMENT = 1;
    static final int PAGE_INCREMENT = 10;

    static final int COLOR_IMAGE_WIDTH = 31;
    static final int COLOR_IMAGE_HEIGHT = 20;

    static final int COMMENT_TEXT_LIMIT = SheetConstants.DESCRIPTION_TEXT_LIMIT;
    static final RGB DEFAULT_BACKGROUND = SheetConstants.DEFAULT_BACKGROUND;

    static final int DEFAULT_WIDTH = Sheet.DEFAULT_SIZE.width;
    static final int MAX_WIDTH = Sheet.MAX_WIDTH;
    static final int MIN_WIDTH = Sheet.MIN_WIDTH;

    static final int DEFAULT_HEIGHT = Sheet.DEFAULT_SIZE.height;
    static final int MAX_HEIGHT = Sheet.MAX_HEIGHT;
    static final int MIN_HEIGHT = Sheet.MIN_HEIGHT;

    static final String DEFAULT_COMMENT = Sheet.DEFAULT_DESCRIPTION;

    private ImageFactory imageFactory;

    private Spinner widthSpinner;
    private Spinner heightSpinner;
    private Text commentText;
    private boolean isOpaque;
    private RGB background;

    public NewSpriteSheetMainPage() {
        super(NAME);
        initialize();
    }

    private void initialize() {
        setTitle(TITLE);
        setDescription(DESCRIPTION);

        imageFactory = new ImageFactory();
        background = DEFAULT_BACKGROUND;
        isOpaque = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));

        createWidthControls(container);
        createHeightControls(container);
        createBackgroundControls(container);
        createCommentControls(container);

        setControl(container);
    }

    private void createWidthControls(Composite parent) {
        Label widthLabel = new Label(parent, SWT.NONE);
        widthLabel.setText(WIDTH_TEXT);

        widthSpinner = new Spinner(parent, SWT.BORDER);
        widthSpinner.setValues(DEFAULT_WIDTH, MIN_WIDTH, MAX_WIDTH,
                DECIMAL_DIGITS, INCREMENT, PAGE_INCREMENT);
    }

    private void createHeightControls(Composite parent) {
        Label heightLabel = new Label(parent, SWT.NONE);
        heightLabel.setText(HEIGHT_TEXT);

        heightSpinner = new Spinner(parent, SWT.BORDER);
        heightSpinner.setValues(DEFAULT_HEIGHT, MIN_HEIGHT, MAX_HEIGHT,
                DECIMAL_DIGITS, INCREMENT, PAGE_INCREMENT);
    }

    private void createBackgroundControls(Composite parent) {
        Label backgroundLabel = new Label(parent, SWT.NONE);
        backgroundLabel.setText(BACKGROUND_TEXT);

        ToolBar bar = new ToolBar(parent, SWT.NONE);

        final Menu menu = new Menu(bar);
        final ColorDialog dialog = new ColorDialog(parent.getShell());

        final ToolItem item = new ToolItem(bar, SWT.DROP_DOWN);
        item.setImage(imageFactory.createColorImage(background,
                COLOR_IMAGE_WIDTH, COLOR_IMAGE_HEIGHT));

        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ToolItem source = (ToolItem) e.getSource();
                ToolBar parent = source.getParent();

                if (SWT.ARROW == e.detail) {
                    Rectangle rect = source.getBounds();
                    Point point = parent.toDisplay(new Point(rect.x, rect.y));

                    menu.setLocation(point.x, point.y + rect.height);
                    menu.setVisible(true);
                } else {
                    RGB color = dialog.open();

                    if (null != color) {
                        source.setImage(imageFactory.createColorImage(color,
                                COLOR_IMAGE_WIDTH, COLOR_IMAGE_HEIGHT));
                        background = color;
                        isOpaque = true;
                    }
                }
            }
        });

        MenuItem chooseColor = new MenuItem(menu, SWT.NONE);
        chooseColor.setText(CHOOSE_COLOR_TEXT);
        chooseColor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                RGB color = dialog.open();

                if (null != color) {
                    item.setImage(imageFactory.createColorImage(color,
                            COLOR_IMAGE_WIDTH, COLOR_IMAGE_HEIGHT));
                    background = color;
                    isOpaque = true;
                }
            }
        });

        new MenuItem(menu, SWT.SEPARATOR);

        MenuItem transparent = new MenuItem(menu, SWT.NONE);
        transparent.setText(TRANSPARENT_TEXT);
        transparent.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                item.setImage(imageFactory.createCheckerImage(
                        COLOR_IMAGE_WIDTH, COLOR_IMAGE_HEIGHT));
                background = null;
                isOpaque = false;
            }
        });
    }

    private void createCommentControls(Composite parent) {
        Label commentLabel = new Label(parent, SWT.NONE);
        commentLabel.setText(COMMENT_TEXT);
        commentLabel.setLayoutData(new GridData(
                GridData.VERTICAL_ALIGN_BEGINNING));

        commentText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
                | SWT.WRAP);
        commentText.setText(DEFAULT_COMMENT);
        commentText.setTextLimit(COMMENT_TEXT_LIMIT);
        commentText.setLayoutData(new GridData(GridData.FILL_BOTH));
    }

    /**
     * Populates the specified sheet model with values from this wizard page.
     * 
     * @param sheet
     *        the model to populate.
     */
    public void populateSheet(Model sheet) {
        int width = Integer.valueOf(widthSpinner.getText());
        int height = Integer.valueOf(heightSpinner.getText());

        sheet.setProperty(Sheet.SIZE, new Dimension(width, height));
        sheet.setProperty(Sheet.DESCRIPTION, commentText.getText());
        sheet.setProperty(Sheet.OPAQUE, isOpaque);
        sheet.setProperty(Sheet.BACKGROUND, background);
    }

}
