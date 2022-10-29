package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.LinkLabel;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;
import de.eskalon.commons.screen.transition.impl.BlendingTransition;

public class LoginScreen extends ManagedScreen {

    private Stage stage;
    private boolean debugOn = false;
    private Settings settings;
    private Main game;
    private SpriteBatch batch = new SpriteBatch();
    private final ScreenManager screenManager = new ScreenManager();
    private final String devStage = "Indev";
    private final String stageID = "cmt9";
    public String version = "Warth "+devStage+" "+stageID;
    private String gpSignInBtn = "Sign in to Google Play";

    public LoginScreen(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }

    @Override
    protected void create() {

        if (game.gsClient.isSessionActive())
            game.getScreenManager().pushScreen("main", "blend");

        game.getScreenManager().addScreen("main", new MainScreen());
        game.getScreenManager().addScreenTransition("blend",new BlendingTransition(batch,1f));

        settings = new Settings();
        if (settings.getDebugEnabled())
            debugOn = true;

        VisTextButton googlePlayLoginBtn = new VisTextButton(gpSignInBtn);
        VisTextButton exitBtn = new VisTextButton("Exit");

        googlePlayLoginBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!game.gsClient.isSessionActive())
                    game.gsClient.logIn();
                game.getScreenManager().pushScreen("main", "blend");
            }
        });

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialogs.showConfirmDialog(stage, " Exit", "  Really quit?  \n", new String[]{"Yes", "No"}, new Integer[]{1,0}, new ConfirmDialogListener<Integer>() {
                    @Override
                    public void result(Integer result) {
                        if (result == 1)
                            System.exit(0);
                    }
                });
            }
        });

        VisTable root = new VisTable();
        VisTable menu = new VisTable();
        VisTable footer = new VisTable();
        root.setFillParent(true);

        footer.add(new VisLabel(version)).left();
        footer.add(new LinkLabel("Discord","https://discord.gg/a2av2JmzSX")).left().padLeft(30);
        menu.add(googlePlayLoginBtn).spaceBottom(10).row();
        menu.add(exitBtn).row();
        root.add(menu).expand().fill().row();
        root.add(footer).expandX().left().padLeft(10);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(root);
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
