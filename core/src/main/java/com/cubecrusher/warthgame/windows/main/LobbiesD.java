package com.cubecrusher.warthgame.windows.main;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cubecrusher.warthgame.Main;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class LobbiesD extends VisDialog {

    private Main game;

    public LobbiesD(String title, String mapInfo) {
        super(" "+title);
        setKeepWithinStage(true);
        addCloseButton();
        centerWindow();
        addWidgets();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        VisTable root = new VisTable();
        VisTable searchOpts = new VisTable();
        VisScrollPane scrollPane = new VisScrollPane(root);

        VisLabel selectMaps = new VisLabel("Select Maps");

        VisTextButton playBtn = new VisTextButton("Launch Map");
        VisTextButton lobbiesBtn = new VisTextButton("Find Lobbies");
        VisTextButton cancelBtn = new VisTextButton("Cancel");

        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreenManager().pushScreen("gamemap", null);
            }
        });

        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).spaceTop(8).growX().row();
    }
}
