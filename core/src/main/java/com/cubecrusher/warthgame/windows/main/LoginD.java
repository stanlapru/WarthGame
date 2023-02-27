package com.cubecrusher.warthgame.windows.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cubecrusher.warthgame.Main;
import com.cubecrusher.warthgame.MainScreen;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.util.form.FormInputValidator;
import com.kotcrab.vis.ui.util.form.SimpleFormValidator;
import com.kotcrab.vis.ui.widget.LinkLabel;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;

import java.util.regex.Pattern;

import de.eskalon.commons.screen.transition.impl.BlendingTransition;

public class LoginD extends VisDialog {
    private final MainScreen mainScr = new MainScreen();
    private Main game;
    private SpriteBatch batch = new SpriteBatch();

    public LoginD() {
        super(" Log in");
        setKeepWithinStage(true);
        addWidgets();
        centerWindow();
        setMovable(false);
        TableUtils.setSpacingDefaults(this);
        columnDefaults(0).left();
        pack();
    }

    private void addWidgets(){

        VisLabel emailText = new VisLabel("Email:");
        VisLabel passwordText = new VisLabel("Password:");
        VisLabel registerNotice = new VisLabel("No account yet?");
        VisLabel passwordNotice = new VisLabel("Only digits and Latin characters are allowed in the password.");
        LinkLabel policyLink = new LinkLabel("By logging in or registering, you accept the Privacy Policy and Terms.","https://example.com");

        VisValidatableTextField emailBox = new VisValidatableTextField();
        VisValidatableTextField passwordBox = new VisValidatableTextField();

        VisTextButton loginBtn = new VisTextButton("Log in");
        VisTextButton registerBtn = new VisTextButton("Register");
        VisTextButton skipAuthBtn = new VisTextButton("Skip Auth");

        VisLabel errorLabel = new VisLabel();
        errorLabel.setColor(Color.RED);

        passwordNotice.setFontScale(0.66f);
        policyLink.setFontScale(0.66f);

        skipAuthBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game = (Main) Gdx.app.getApplicationListener();
                game.getScreenManager().addScreen("main", new MainScreen());
                game.getScreenManager().addScreenTransition("blend",new BlendingTransition(batch,1f));
                game.getScreenManager().pushScreen("main", null);
            }
        });

        SimpleFormValidator validator;
        validator = new SimpleFormValidator(loginBtn,errorLabel,"smooth");
        validator.setSuccessMessage("All data acceptable.");
        validator.notEmpty(emailBox,"No Email specified.");
        validator.notEmpty(passwordBox,"No password specified.");
        FormInputValidator emailValidate = new FormInputValidator("Invalid Email specified.") {
            @Override
            protected boolean validate(String input) {
                return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
                        .matcher(input)
                        .matches();
            }
        };
        validator.custom(emailBox, emailValidate);

        VisTable table = new VisTable(true);
        VisTable loginInfo = new VisTable(true);
        VisTable middle = new VisTable(true);
        VisTable register = new VisTable(true);

        loginInfo.add(emailText).spaceBottom(10).right();
        loginInfo.add(emailBox).spaceBottom(10).left().minWidth(480).row();
        loginInfo.add(passwordText).spaceBottom(10).left();
        loginInfo.add(passwordBox).spaceBottom(10).left().minWidth(480).row();
        middle.add(errorLabel).spaceBottom(10).left().spaceRight(265);
        middle.add(loginBtn).spaceBottom(10).right().row();
        register.add(registerNotice).spaceBottom(10).left();
        register.add(registerBtn).spaceBottom(10).left();
        register.add(skipAuthBtn).spaceBottom(10).left().row();

        table.add(loginInfo).row();
        //table.add(passwordNotice).spaceBottom(10).left().row();
        table.add(middle).row();
        table.addSeparator();
        table.add(register).left().row();
        table.add(policyLink).left().row();


        table.pad(20);

        add(table);
    }

}
