package com.cubecrusher.warthgame.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.cubecrusher.warthgame.AssetMgr;
import com.cubecrusher.warthgame.MainScreen;
import com.cubecrusher.warthgame.Settings;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class OptionsW extends VisWindow {
    private Settings settings;
    public VisLabel musicV, soundV, debugV = new VisLabel();

    public OptionsW() {
        super(" Options");
        setKeepWithinStage(true);
        addCloseButton();
        addWidgets();
        centerWindow();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets() {
        settings = new Settings();

        VisLabel music = new VisLabel("Music Volume");
        VisLabel sound = new VisLabel("Sound Volume");
        VisLabel debug = new VisLabel("Display Debug");

        musicV = new VisLabel(Math.round(settings.getMusicVolume()*100) + "%");
        soundV = new VisLabel(Math.round(settings.getSoundVolume()*100) + "%");
        debugV = new VisLabel(Boolean.toString(settings.getDebugEnabled()));

        VisSlider musicSlider = new VisSlider(0.00f, 1.00f, 0.01f, false);
        VisSlider soundSlider = new VisSlider(0.00f, 1.00f, 0.01f, false);
        VisTextButton debugBtn = new VisTextButton("Toggle");

        musicSlider.setValue(settings.getMusicVolume());
        soundSlider.setValue(settings.getSoundVolume());
        debugBtn.setChecked(settings.getDebugEnabled());

        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                settings.setMusicVolume(musicSlider.getValue());
                musicV.setText(Math.round(settings.getMusicVolume()*100) + "%");
                System.out.println("DEBUG: Music volume set to "+settings.getMusicVolume());
            }
        });

        soundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                settings.setSoundVolume(soundSlider.getValue());
                soundV.setText(Math.round(settings.getSoundVolume()*100) + "%");
                System.out.println("DEBUG: Sound volume set to "+settings.getSoundVolume());
            }
        });

        debugBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                settings.setDebug(!settings.getDebugEnabled());
                debugV.setText(Boolean.toString(settings.getDebugEnabled()));
            }
        });

        VisTable btnTable = new VisTable(true);

        btnTable.add(music);
        btnTable.add(musicSlider);
        btnTable.add(musicV).spaceBottom(5).row();
        btnTable.add(sound);
        btnTable.add(soundSlider);
        btnTable.add(soundV).spaceBottom(5).row();
        btnTable.add(debug);
        btnTable.add(debugBtn);
        btnTable.add(debugV).row();
        btnTable.pad(20);

        add(btnTable);
    }
}
