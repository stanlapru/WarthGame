package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cubecrusher.warthgame.windows.main.LoginD;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.LinkLabel;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import java.security.Provider;

import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;
import de.eskalon.commons.screen.transition.impl.BlendingTransition;

public class LoginScreen extends ManagedScreen {

    private Stage stage;
    private boolean debugOn = false;
    private Settings settings;
    private Main game;
    private SpriteBatch batch = new SpriteBatch();
    private LoginD loginD;
    private final ScreenManager screenManager = new ScreenManager();
    private String gpSignInBtn;
    //final OAuth20Service service;

    public LoginScreen(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }

    @Override
    protected void create() {
        //service = new ServiceBuilder("").apiSecret("").build();
        game.getScreenManager().addScreen("main", new MainScreen());
        game.getScreenManager().addScreenTransition("blend",new BlendingTransition(batch,1f));

        settings = new Settings();
        if (settings.getDebugEnabled())
            debugOn = true;
        String version = settings.getVersion();

        loginD = new LoginD();

        VisTable root = new VisTable();
        VisTable menu = new VisTable();
        VisTable footer = new VisTable();
        root.setFillParent(true);

        footer.add(new VisLabel(version)).left();
        root.add(menu).expand().fill().row();
        root.add(footer).expandX().left().padLeft(10);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(root);
        stage.addActor(loginD);
        stage.setDebugAll(debugOn);
    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f,0.05f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        VisUI.dispose();
    }
}
