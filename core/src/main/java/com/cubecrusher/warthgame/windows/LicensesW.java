package com.cubecrusher.warthgame.windows;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;

public class LicensesW extends VisWindow {
    public LicensesW() {
        super(" Licenses");
        setKeepWithinStage(true);
        addCloseButton();
        addWidgets();
        centerWindow();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        //ScrollableTextArea licenses = new ScrollableTextArea("Placeholder until I figure out how to solve the StackOverflow error when loading licenses.txt");
        //licenses.setReadOnly(true);

        VisTable table = new VisTable();

        VisScrollPane scrollPane = new VisScrollPane(table);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);

        //table.add(licenses.createCompatibleScrollPane()).growX().height(Gdx.graphics.getHeight()/2f).fill().row();
        table.add(scrollPane).spaceTop(8).growX().row();
        table.pad(20);

        //add(table);
    }
}
