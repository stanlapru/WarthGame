package com.cubecrusher.warthgame.windows.game;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.LinkLabel;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;

public class RegionInfoW extends VisWindow {
    private final String regName;
    public RegionInfoW(String title) {
        super(" "+title);
        regName = title;
        addCloseButton();
        setPosition(50,50);
        addWidgets();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        VisLabel country = new VisLabel("Country: "+"PLACEHOLDER");
        VisLabel population = new VisLabel("Population: "+"PLACEHOLDER");

        VisTable table = new VisTable(true);
        table.add(country).spaceBottom(10).left().row();
        table.add(population).spaceBottom(10).left().row();
        table.pad(20);

        add(table);
    }

    public void closeWindow(){
        close();
    }
}
