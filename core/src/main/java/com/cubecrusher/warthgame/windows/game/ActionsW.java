package com.cubecrusher.warthgame.windows.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class ActionsW extends VisWindow {
    private PropagandaD propagandaD;
    private ConscriptionD conscriptD;
    private InvestmentD investD;
    private String regName;

    public ActionsW(String title, boolean regionStatus, String regionName) {  // regionStatus will be an int to iterate between owned, enemy, occupied or orphan region types
        super(" "+title+" for "+regionName);
        regName = regionName;
        addCloseButton();
        centerWindow();
        if (regionStatus)
            addWidgets();
        else
            addForeignWidgets();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        conscriptD = new ConscriptionD("Conscription for "+regName);
        investD = new InvestmentD("Invest in "+regName);
        propagandaD = new PropagandaD("Deploy Propaganda in "+regName,true);
        VisLabel owned = new VisLabel("This region is your country property.");
        owned.setColor(Color.GREEN);
        VisLabel capital = new VisLabel("Capital: "+"PH.");
        VisLabel country = new VisLabel("Country: "+"PH.");
        VisLabel population = new VisLabel("Population: "+"PH.");
        VisLabel stability = new VisLabel("Stability: "+"PH.");

        VisTextButton propaganda = new VisTextButton("Distribute Propaganda");
        VisTextButton invest = new VisTextButton("Invest in Region");
        VisTextButton conscription = new VisTextButton("Conscript Army");

        propaganda.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getStage().addActor(propagandaD);
                propagandaD.fadeIn();
                propagandaD.setVisible(true);
            }
        });

        invest.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getStage().addActor(investD);
                investD.fadeIn();
                investD.setVisible(true);
            }
        });

        conscription.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getStage().addActor(conscriptD);
                conscriptD.fadeIn();
                conscriptD.setVisible(true);
            }
        });

        VisTable root = new VisTable();
        VisTable btns = new VisTable();
        root.pad(20);
        root.add(owned).left().spaceBottom(10).row();
        root.add(capital).left().spaceBottom(10).row();
        root.add(country).left().spaceBottom(10).row();
        root.add(population).left().spaceBottom(10).row();
        root.add(stability).left().spaceBottom(10).row();
        root.addSeparator().spaceBottom(10);
        btns.add(propaganda);
        btns.add(invest).spaceLeft(10);
        btns.add(conscription).spaceLeft(10);
        root.add(btns);

        add(root);
    }

    private void addForeignWidgets(){
        VisLabel owned = new VisLabel("This region belongs to "+"PLACEHOLDER.");
        VisLabel capital = new VisLabel("Capital: "+"PLACEHOLDER.");
        VisLabel country = new VisLabel("Country: "+"PLACEHOLDER.");
        VisLabel population = new VisLabel("Population: "+"PLACEHOLDER.");
        VisLabel stability = new VisLabel("Stability: "+"PLACEHOLDER.");

        VisTable root = new VisTable();
        root.pad(20);
        root.add(owned).left().spaceBottom(10).row();
        root.add(capital).left().spaceBottom(10).row();
        root.add(country).left().spaceBottom(10).row();
        root.add(population).left().spaceBottom(10).row();
        root.add(stability).left().spaceBottom(10).row();
        add(root);
    }

    private void addOccupiedWidgets(){
        VisLabel owned = new VisLabel("This province is under your occupation.");
        owned.setColor(Color.GOLD);

        VisLabel capital = new VisLabel("Capital: "+"PLACEHOLDER.");
        VisLabel country = new VisLabel("Country: "+"PLACEHOLDER.");
        VisLabel population = new VisLabel("Population: "+"PLACEHOLDER.");
        VisLabel stability = new VisLabel("Stability: "+"PLACEHOLDER.");

        VisTable root = new VisTable();
        root.pad(20);
        root.add(owned).left().spaceBottom(10).row();
        root.add(capital).left().spaceBottom(10).row();
        root.add(country).left().spaceBottom(10).row();
        root.add(population).left().spaceBottom(10).row();
        root.add(stability).left().spaceBottom(10).row();
        add(root);
    }

    private void addLostWidgets(){
        VisLabel owned = new VisLabel("This province is under enemy occupation.");
        owned.setColor(Color.RED);
        VisLabel capital = new VisLabel("Capital: "+"PLACEHOLDER.");
        VisLabel country = new VisLabel("Country: "+"PLACEHOLDER.");
        VisLabel population = new VisLabel("Population: "+"PLACEHOLDER.");
        VisLabel stability = new VisLabel("Stability: "+"PLACEHOLDER.");

        VisTable root = new VisTable();
        root.pad(20);
        root.add(owned).left().spaceBottom(10).row();
        root.add(capital).left().spaceBottom(10).row();
        root.add(country).left().spaceBottom(10).row();
        root.add(population).left().spaceBottom(10).row();
        root.add(stability).left().spaceBottom(10).row();
        add(root);
    }

    private void addOrphanWidgets(){
        VisLabel owned = new VisLabel("This province is not controlled by anyone.");
        owned.setColor(Color.GRAY);

        VisTable root = new VisTable();
        root.pad(20);
        root.add(owned).left().spaceBottom(10).row();
        add(root);
    }
}
