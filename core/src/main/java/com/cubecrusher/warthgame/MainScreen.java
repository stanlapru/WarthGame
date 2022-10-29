package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.*;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;
import de.eskalon.commons.screen.transition.impl.BlendingTransition;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class MainScreen extends ManagedScreen {

    private Stage stage;
    private boolean debugOn = false;
    private Settings settings;
    private Main game;
    private SpriteBatch batch = new SpriteBatch();
    private final ScreenManager screenManager = new ScreenManager();
    private final String devStage = "Indev";
    private final String stageID = "cmt9";
    public String version = "Warth "+devStage+" "+stageID;

    public MainScreen(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }

    @Override
    protected void create() {
        game.getScreenManager().addScreen("gamemap", new GameMap());
        game.getScreenManager().addScreenTransition("blend",new BlendingTransition(batch,1f));

        //VisUI.load(VisUI.SkinScale.X2);

        settings = new Settings();
        if (settings.getDebugEnabled())
                debugOn = true;

        MenuBar menuBar = new MenuBar();

        VisWindow chatWindow = new VisWindow(" Window");
        chatWindow.setMovable(false);
        chatWindow.setKeepWithinStage(true);

        VisWindow lobbiesWindow = new VisWindow(" Lobby Search");
        lobbiesWindow.setMovable(true);
        lobbiesWindow.setKeepWithinStage(true);
        lobbiesWindow.addCloseButton();
        lobbiesWindow.centerWindow();
        lobbiesWindow.setVisible(false);

        // Todo: Migrate into a separate method, make button listeners and make Client/Server work (on localhost for now)
        VisTable chatWindowLayout = new VisTable();
        chatWindowLayout.add().expand().fill().row();
        chatWindowLayout.add(new VisTextArea("")).expandX().fillX().spaceBottom(10).row();
        chatWindowLayout.add(new VisTextField("...")).expandX().fillX();
        chatWindowLayout.add(new VisTextButton("Send")).padLeft(10);

        chatWindow.add(chatWindowLayout);

        VisTextButton openTestMapBtn = new VisTextButton("Launch Gamemap");
        VisTextButton lobbySearchBtn = new VisTextButton("Search Lobbies");
        VisTextButton toggleDebugBtn = new VisTextButton("Toggle Debug");
        VisTextButton exitBtn = new VisTextButton("Exit");

        //exitBtn.setPosition(20,20);
        //toggleDebugBtn.setPosition(20,80);
        menuBar.setMenuListener(new MenuBar.MenuBarListener() {
            @Override
            public void menuOpened(Menu menu) {
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
                game.getScreenManager().pushScreen("gamemap", "blend");
            }
        });
        lobbySearchBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lobbiesWindow.setVisible(true);
                lobbiesWindow.fadeIn();
            }
        });

        toggleDebugBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settings.setDebug(!settings.getDebugEnabled());
                stage.setDebugAll(settings.getDebugEnabled());
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

        menuBar.addMenu(new Menu("Test"));
        menuBar.addMenu(new Menu("Test 2"));

        VisTable root = new VisTable();
        VisTable menu = new VisTable();
        VisTable menuBtn = new VisTable();
        VisTable footer = new VisTable();

        root.setFillParent(true);
        root.add(menuBar.getTable()).expandX().fillX().row();
        menuBtn.add(openTestMapBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(lobbySearchBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(toggleDebugBtn).expandX().fillX().spaceBottom(10).row();
        menuBtn.add(exitBtn).expandX().fillX();

        menu.add(menuBtn).expand();
        menu.add(chatWindow).expand();
        root.add(menu).expand().fill().row();
        root.add(footer).expandX().left().padLeft(10);
        footer.add(new VisLabel(version)).left();
        footer.add(new LinkLabel("Discord","https://discord.gg/a2av2JmzSX")).left().padLeft(30);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(root);
        stage.addActor(lobbiesWindow);
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
