package com.cubecrusher.warthgame.windows.main;

import com.cubecrusher.warthgame.Client;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow;

public class LobbiesW extends VisWindow {

    public LobbiesW(){
        super(" Search Lobbies");
        setKeepWithinStage(true);
        addCloseButton();
        addWidgets();
        centerWindow();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){

    }
}
