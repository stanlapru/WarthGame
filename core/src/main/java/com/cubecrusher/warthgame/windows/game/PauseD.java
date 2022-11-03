package com.cubecrusher.warthgame.windows.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cubecrusher.warthgame.Main;
import com.cubecrusher.warthgame.Settings;
import com.cubecrusher.warthgame.windows.main.OptionsD;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PauseD extends VisDialog {
    private Settings settings;
    private OptionsD optionsD;
    private Main game;

    public PauseD() {
        super(" Paused");
        this.game = (Main) Gdx.app.getApplicationListener();
        addCloseButton();
        centerWindow();
        addWidgets();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        settings = new Settings();
        optionsD = new OptionsD();

        VisTextButton resumeBtn = new VisTextButton("Resume");
        VisTextButton optionsBtn = new VisTextButton("Options");
        VisTextButton exitBtn = new VisTextButton("Return to Main");

        resumeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                close();
            }
        });

        optionsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getStage().addActor(optionsD);
                optionsD.fadeIn();
            }
        });

        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                close();
                Gdx.input.setInputProcessor(null);
                game.getScreenManager().pushScreen("main", null);
            }
        });

        VisTable root = new VisTable();
        root.pad(10);
        root.add(resumeBtn).expand().fill().spaceBottom(10).row();
        root.add(optionsBtn).expand().fill().spaceBottom(10).row();
        root.add(exitBtn).expand().fill().spaceBottom(10).row();

        add(root);
    }
}
