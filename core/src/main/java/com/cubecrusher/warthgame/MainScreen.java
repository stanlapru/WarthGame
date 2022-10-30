package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cubecrusher.warthgame.windows.AboutW;
import com.cubecrusher.warthgame.windows.LobbiesW;
import com.cubecrusher.warthgame.windows.OptionsW;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.*;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;


public class MainScreen extends ManagedScreen {

    public Stage stage;
    private boolean debugOn = false;
    private Settings settings;
    private Main game;
    private SpriteBatch batch = new SpriteBatch();
    private final ScreenManager screenManager = new ScreenManager();
    private OptionsW optionsW;
    private AboutW aboutW;
    private LobbiesW lobbiesW;
    private final String devStage = "Indev";
    private final String stageID = "cmt6";
    public String version = "Warth "+devStage+" "+stageID;

    public MainScreen(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }

    public String getVersion(){
        return version;
    }

    @Override
    protected void create() {
        game.getScreenManager().addScreen("gamemap", new GameMap());

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        settings = new Settings();

        MenuBar menuBar = new MenuBar();

        optionsW = new OptionsW();
        aboutW = new AboutW();
        lobbiesW = new LobbiesW();

        // Todo: Migrate into a separate method, make button listeners and make Client/Server work (on localhost for now)
        // ^ Deprioritized.

        VisTextButton openTestMapBtn = new VisTextButton("Launch Map");
        VisTextButton lobbySearchBtn = new VisTextButton("Search Lobbies");
        VisTextButton optionsBtn = new VisTextButton("Options");
        VisTextButton aboutBtn = new VisTextButton("About");
        VisTextButton exitBtn = new VisTextButton("Exit");

        menuBar.setMenuListener(new MenuBar.MenuBarListener() {
            @Override
            public void menuOpened (Menu menu) {
                System.out.println("Opened menu: " + menu.getTitle());
            }
            @Override
            public void menuClosed (Menu menu) {
                System.out.println("Closed menu: " + menu.getTitle());
            }
        });

        openTestMapBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("DEBUG: GameMap called.");
                game.getScreenManager().pushScreen("gamemap", null);
            }
        });
        lobbySearchBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(lobbiesW);
                lobbiesW.fadeIn();
                lobbiesW.setVisible(true);
            }
        });

        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(optionsW);
                optionsW.fadeIn();
                optionsW.setVisible(true);
            }
        });

        aboutBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(aboutW);
                aboutW.fadeIn();
                aboutW.setVisible(true);
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

        VisLabel title = new VisLabel("Warth");
        title.setFontScale(4);

        menuBar.addMenu(new Menu("Test"));
        menuBar.addMenu(new Menu("Test 2"));

        VisTable root = new VisTable();
        VisTable menu = new VisTable();
        VisTable menuBtn = new VisTable();
        VisTable footer = new VisTable();

        root.setFillParent(true);
        root.add(menuBar.getTable()).expandX().fillX().row();
        menuBtn.add(title).top().left().spaceBottom(10).row();
        menuBtn.add(openTestMapBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(lobbySearchBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(optionsBtn).expandX().fillX().spaceBottom(10).row();
        //menuBtn.add(toggleDebugBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(aboutBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(exitBtn).expandX().fillX();

        menu.add(menuBtn).expand();
        //menu.add(chatWindow).expand();
        root.add(menu).expand().fill().row();
        root.add(footer).expandX().left().padLeft(10);
        footer.add(new VisLabel(version)).left();
        footer.add(new LinkLabel("Discord","https://discord.gg/a2av2JmzSX")).left().padLeft(30);

        stage.addActor(root);
        stage.setDebugAll(debugOn);

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.getInputProcessor() != stage)
            Gdx.input.setInputProcessor(stage);
        if (settings.getDebugEnabled())
            stage.setDebugAll(true);
        Gdx.gl.glClearColor(0.05f,0.05f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        //stage.setDebugAll(debugOn);
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
