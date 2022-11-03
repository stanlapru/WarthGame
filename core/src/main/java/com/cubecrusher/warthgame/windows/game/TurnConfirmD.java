package com.cubecrusher.warthgame.windows.game;

import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisDialog;

public class TurnConfirmD extends VisDialog {
    public TurnConfirmD() {
        super(" Turn Overview");
        addCloseButton();
        setPosition(50,50);
        addWidgets();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){

    }
}
