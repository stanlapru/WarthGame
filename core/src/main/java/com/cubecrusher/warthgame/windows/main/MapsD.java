package com.cubecrusher.warthgame.windows.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cubecrusher.warthgame.GameMap;
import com.cubecrusher.warthgame.Main;
import com.cubecrusher.warthgame.MainScreen;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MapsD extends VisDialog {

    private MapInfoD mapInfoD;
    private MainScreen mainScr;
    private Main game;
    private String actMapName = "";


    public MapsD(){
        super(" Select Map");
        this.game = (Main) Gdx.app.getApplicationListener();
        setKeepWithinStage(true);
        addCloseButton();
        addWidgets();
        centerWindow();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        mainScr = new MainScreen();
        game.getScreenManager().addScreen("gamemap", new GameMap());

        VisTable root = new VisTable();
        VisScrollPane scrollPane = new VisScrollPane(root);

        VisLabel belarusL = new VisLabel("Belarus");
        VisLabel ph1 = new VisLabel("PLACEHOLDER 1");
        VisLabel ph2 = new VisLabel("PLACEHOLDER 2");
        VisLabel ph3 = new VisLabel("PLACEHOLDER 3");

        VisTextButton belarusBtn = new VisTextButton("Launch");
        VisTextButton ph1btn = new VisTextButton("Map Info");
        VisTextButton ph2btn = new VisTextButton("Map Info");
        VisTextButton ph3btn = new VisTextButton("Map Info");

        ph1btn.setDisabled(true);
        ph2btn.setDisabled(true);
        ph3btn.setDisabled(true);

        belarusBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                actMapName = "Belarus";
                mapInfoD = new MapInfoD(actMapName,getMapInfo(actMapName));
                System.out.println("DEBUG: GameMap called, map: "+actMapName+".");
                Gdx.input.setInputProcessor(null);
                game.getScreenManager().pushScreen("gamemap", null);
            }
        });
        root.pad(20);

        root.add(belarusL).expand().fill().spaceBottom(10);
        root.add(belarusBtn).expand().fill().spaceBottom(10).spaceLeft(20).row();
        root.add(ph1).expand().fill().spaceBottom(10);
        root.add(ph1btn).expand().fill().spaceBottom(10).spaceLeft(20).row();
        root.add(ph2).expand().fill().spaceBottom(10);
        root.add(ph2btn).expand().fill().spaceBottom(10).spaceLeft(20).row();
        root.add(ph3).expand().fill().spaceBottom(10);
        root.add(ph3btn).expand().fill().spaceBottom(10).spaceLeft(20).row();

        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).spaceTop(8).growX().row();
    }

    public String getMapInfo(String getMapName){
        switch (getMapName) {
            case "Belarus":
                return "Description: A small map of Belarus intended for testing.\n" +
                        "Regions: 6.\n" +
                        "Best played with: 2 players.\n" +
                        "Max players: 6.\n";
            case "Ukraine (post-2014)":
                return "Description: A map of Ukraine modeled after the post-2014 geopolitical situation.\n" +
                        "Regions: 24.\n" +
                        "Best played with: 4 players.\n" +
                        "Max players: 8.\n";
            default:
                return "This is a text that you are not supposed to see in-game.\n" +
                        "If you see this text, please report it on Discord and describe the reproduction algorithm. Thanks!";
        }
    }
}
