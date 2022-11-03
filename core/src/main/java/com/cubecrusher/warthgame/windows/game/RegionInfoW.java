package com.cubecrusher.warthgame.windows.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class RegionInfoW extends VisWindow {
    public final String regName;
    private String actionsText;
    private ActionsW actionsW;
    private ConscriptionD conscriptW;
    private boolean isOwned;

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
        actionsText = "Actions";
        isOwned = true;
        actionsW = new ActionsW(actionsText, isOwned, regName);
        conscriptW = new ConscriptionD("Conscription for "+regName);

        VisLabel country = new VisLabel("Country: "+"PH.");
        VisLabel population = new VisLabel("Population: "+"PH.");
        VisLabel stability = new VisLabel("Stability: "+"PH.");
        VisTextButton actionsBtn = new VisTextButton(actionsText);
        VisTextButton conscriptBtn = new VisTextButton("Conscript Army");

        actionsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getStage().addActor(actionsW);
                actionsW.fadeIn();
                actionsW.setVisible(true);
            }
        });

        conscriptBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getStage().addActor(conscriptW);
                conscriptW.fadeIn();
                conscriptW.setVisible(true);
            }
        });

        VisTable table = new VisTable();
        VisTable btns = new VisTable();
        table.add(country).spaceBottom(10).left().row();
        table.add(population).spaceBottom(10).left().row();
        table.add(stability).spaceBottom(10).left().row();
        table.addSeparator().spaceBottom(10);
        if (isOwned) {
            btns.add(actionsBtn).spaceBottom(10);
            btns.add(conscriptBtn).spaceBottom(10).spaceLeft(10).row();
        } else {
            btns.add(actionsBtn).spaceBottom(10).left().row();
        }
        table.add(btns).left();
        table.pad(20);

        add(table);
    }
}
