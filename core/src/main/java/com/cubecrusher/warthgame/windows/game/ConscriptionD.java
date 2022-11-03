package com.cubecrusher.warthgame.windows.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class ConscriptionD extends VisDialog {
    private String descr;
    private float conscrAmt;
    public ConscriptionD(String title) {
        super(" "+title);
        addCloseButton();
        centerWindow();
        addWidgets();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        VisLabel descrTxt = new VisLabel();
        descr = "You are about to conscript troops in this region.\nStability will be slightly reduced.";
        descrTxt.setText(descr);
        descrTxt.setColor(Color.GOLD);

        VisSlider slider = new VisSlider(1,5000,1,false);
        slider.setValue(2500);
        VisLabel sliderV = new VisLabel("2500");

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                conscrAmt = slider.getValue();
                sliderV.setText(Math.round(conscrAmt));
            }
        });

        VisLabel price = new VisLabel("You need "+"PH"+" Money and "+"PH"+" PP to perform this action.");

        VisTextButton confirmBtn = new VisTextButton("Accept");
        // todo add grayout confirmBtn when Money and PP are implemented

        confirmBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
                Dialogs.showConfirmDialog(getStage(), " Confirm Action", "  You are about to conscript troops.  \n", new String[]{"Confirm", "Cancel"}, new Integer[]{1,0}, new ConfirmDialogListener<Integer>() {
                    @Override
                    public void result(Integer result) {
                        // add actions when turns are implemented
                        if (result == 0) {
                            fadeIn();
                            setVisible(true);
                        }
                    }
                });
            }
        });

        VisTextButton cancelBtn = new VisTextButton("Cancel");
        cancelBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                close();
            }
        });

        VisTable root = new VisTable();
        VisTable sliderT = new VisTable();
        VisTable btns = new VisTable();

        root.pad(10);
        root.add(descrTxt).left().spaceBottom(10).row();
        root.add(price).left().spaceBottom(10).row();
        sliderT.add(slider).expand().fill().spaceBottom(10);
        sliderT.add(sliderV).spaceBottom(10).spaceLeft(10).row();
        root.add(sliderT).fill().expand().spaceBottom(10).row();
        root.addSeparator().spaceBottom(10);
        btns.add(confirmBtn).spaceBottom(10);
        btns.add(cancelBtn).spaceLeft(10).spaceBottom(10).row();
        root.add(btns).left();

        add(root);
    }
}
