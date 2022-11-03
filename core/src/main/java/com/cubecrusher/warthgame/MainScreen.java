package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cubecrusher.warthgame.windows.main.AboutD;
import com.cubecrusher.warthgame.windows.main.MapInfoD;
import com.cubecrusher.warthgame.windows.main.MapsD;
import com.cubecrusher.warthgame.windows.main.OptionsD;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.*;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;


public class MainScreen extends ManagedScreen {

    public Stage stage = new Stage();
    private boolean debugOn = false;
    private Settings settings;
    private Main game;
    private SpriteBatch batch = new SpriteBatch();
    private final ScreenManager screenManager = new ScreenManager();
    private OptionsD optionsD;
    private AboutD aboutD;
    private MapsD mapsD;
    private MapInfoD mapInfoD;
    private final String devStage = "Indev";
    private final String stageID = "cmt9";
    public String version = "Warth "+devStage+" "+stageID;

    public MainScreen(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }

    public String getVersion(){
        return version;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    protected void create() {
        game.getScreenManager().addScreen("gamemap", new GameMap());

        Gdx.input.setInputProcessor(stage);

        settings = new Settings();

        MenuBar menuBar = new MenuBar();

        optionsD = new OptionsD();
        aboutD = new AboutD();
        mapsD = new MapsD();

        // Todo: Make Client/Server work (on localhost for now)
        // ^ Deprioritized.

        VisTextButton playBtn = new VisTextButton("Launch Belarus");
        VisTextButton browseMapsBtn = new VisTextButton("Browse Maps");
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
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("DEBUG: GameMap called, map: Belarus6.");
                Gdx.input.setInputProcessor(null);
                game.getScreenManager().pushScreen("gamemap", null);
            }
        });
        browseMapsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("DEBUG: MapsW called.");
                stage.addActor(mapsD);
                mapsD.fadeIn();
                mapsD.setVisible(true);
            }
        });

        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(optionsD);
                optionsD.fadeIn();
                optionsD.setVisible(true);
            }
        });
        aboutBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(aboutD);
                aboutD.fadeIn();
                aboutD.setVisible(true);
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

        Menu testMenu = new Menu("Test");
        MenuItem crashMenuItem = new MenuItem("Free Among Us Download APK Crack 2022 (NEW)", new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int a = 0;
                a /= 0;
            }
        });

        testMenu.addItem(crashMenuItem);
        menuBar.addMenu(testMenu);

        VisTable root = new VisTable();
        VisTable menu = new VisTable();
        VisTable menuBtn = new VisTable();
        VisTable footer = new VisTable();

        root.setFillParent(true);
        root.add(menuBar.getTable()).expandX().fillX().row();
        menuBtn.add(title).top().left().spaceBottom(10).row();
        menuBtn.add(playBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(browseMapsBtn).expandX().fillX().spaceBottom(10).row();
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
