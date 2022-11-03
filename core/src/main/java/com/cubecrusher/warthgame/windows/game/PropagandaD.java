package com.cubecrusher.warthgame.windows.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PropagandaD extends VisDialog {
    private String descr;
    private boolean regStat = false;
    public PropagandaD(String title, boolean regionStatus) { // regionStatus will be an int to iterate between owned, enemy, occupied or orphan region types
        super(" "+title);
        if (regionStatus)
            regStat = true;
        addCloseButton();
        centerWindow();
        addWidgets();
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){
        VisLabel descrTxt = new VisLabel();
        if (regStat) {
            descr = "You are about to distribute propaganda in this region.\nStability in this region will be boosted.";
            descrTxt.setText(descr);
            descrTxt.setColor(Color.GREEN);
        }
        else {
            descr = "You are about to distribute propaganda on foreign country property.\nStability in this region will be decreased.\nThe owner country will be alerted and might retaliate.";
            descrTxt.setText(descr);
            descrTxt.setColor(Color.GOLD);
        }

        VisLabel price = new VisLabel("You need "+"PH"+" Money and "+"PH"+" PP to perform this action.");

        VisTextButton confirmBtn = new VisTextButton("Accept");
        // todo add grayout confirmBtn when Money and PP are implemented

        confirmBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
                Dialogs.showConfirmDialog(getStage(), " Confirm Action", "  You are about to distribute propaganda.  \n", new String[]{"Confirm", "Cancel"}, new Integer[]{1,0}, new ConfirmDialogListener<Integer>() {
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
        VisTable btns = new VisTable();

        root.pad(10);
        root.add(descrTxt).left().spaceBottom(10).row();
        root.add(price).left().spaceBottom(10).row();
        root.addSeparator().spaceBottom(10);
        btns.add(confirmBtn).spaceBottom(10);
        btns.add(cancelBtn).spaceLeft(10).spaceBottom(10).row();
        root.add(btns).left();

        add(root);
    }
}
