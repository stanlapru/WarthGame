package com.cubecrusher.warthgame.windows.main;

import com.cubecrusher.warthgame.MainScreen;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.LinkLabel;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;

public class AboutW extends VisWindow {
    private final MainScreen mainScr = new MainScreen();

    public AboutW() {
        super(" About");
        setKeepWithinStage(true);
        addCloseButton();
        addWidgets();
        centerWindow();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        VisLabel gameVer = new VisLabel(mainScr.getVersion());
        gameVer.setFontScale(1.5f);
        VisLabel gameDev = new VisLabel("by cubecrusher (c) 2022");
        VisLabel usingLibsBy = new VisLabel("using libraries by:");
        LinkLabel smgrLabel = new LinkLabel("crykn - libgdx-screenmanager","https://github.com/crykn/libgdx-screenmanager");
        LinkLabel visuiLabel = new LinkLabel("kotcrab - vis-ui","https://github.com/kotcrab/vis-ui");
        LinkLabel shdwLabel = new LinkLabel("earlygrey - shapedrawer","https://github.com/earlygrey/shapedrawer");
        smgrLabel.setFontScale(0.8f);
        visuiLabel.setFontScale(0.8f);
        shdwLabel.setFontScale(0.8f);

        VisTable table = new VisTable(true);
        table.add(gameVer).spaceBottom(10).left().row();
        table.add(gameDev).spaceBottom(10).left().row();
        table.add(usingLibsBy).left().row();
        table.add(smgrLabel).left().row();
        table.add(visuiLabel).left().row();
        table.add(shdwLabel).left().row();
        table.pad(20);

        add(table);
    }

}
