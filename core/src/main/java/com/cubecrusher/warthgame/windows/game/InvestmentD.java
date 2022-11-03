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
import com.kotcrab.vis.ui.widget.VisWindow;

public class InvestmentD extends VisDialog {
    private String descr;
    public InvestmentD(String title) {
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
        descr = "You are about to invest in this region.\nDevelopment in this region will be boosted.\nStability will be slightly boosted.";
        descrTxt.setText(descr);
        descrTxt.setColor(Color.GREEN);

        VisLabel price = new VisLabel("You need "+"PH"+" Money and "+"PH"+" PP to perform this action.");

        VisTextButton confirmBtn = new VisTextButton("Accept");
        // todo add grayout confirmBtn when Money and PP are implemented

        confirmBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setVisible(false);
                Dialogs.showConfirmDialog(getStage(), " Confirm Action", "  You are about to invest in this region.  \n", new String[]{"Confirm", "Cancel"}, new Integer[]{1,0}, new ConfirmDialogListener<Integer>() {
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
