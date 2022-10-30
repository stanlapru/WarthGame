package com.cubecrusher.warthgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.strongjoshua.console.Console;
import com.strongjoshua.console.GUIConsole;

import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;
import de.eskalon.commons.screen.transition.impl.BlendingTransition;

public class LoadingScreen extends ManagedScreen {

    private Main game;
    private SpriteBatch batch = new SpriteBatch();
    private final ScreenManager screenManager = new ScreenManager();
    private final AssetManager assetManager = AssetMgr.manager;
    private Viewport viewport;
    BitmapFont font = new BitmapFont();
    int n = 0;

    public LoadingScreen(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }
    @Override
    protected void create() {
        System.out.println("DEBUG: LoadingScreen called.");
        game.getScreenManager().addScreen("login", new LoginScreen());
        game.getScreenManager().addScreen("game", new GameMap());
        game.getScreenManager().addScreen("main", new MainScreen());
        game.getScreenManager().addScreenTransition("blend",new BlendingTransition(batch,0.5f));
        viewport = new FitViewport(game.getWidth(), game.getHeight());
        AssetMgr.preLoad();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        viewport.apply();
        if (AssetMgr.preLoaded) {
            assetManager.update(21);
            font = AssetMgr.manager.get("fonts/gui.ttf");
            font.draw(batch,AssetMgr.loadState,20,30);
            if (AssetMgr.allLoaded) {
                System.out.println("DEBUG: All loaded.");
                //if (Gdx.app.getType() == Application.ApplicationType.Android) {
                    //if (!game.gsClient.isSessionActive())
                        //game.getScreenManager().pushScreen("main", "blend");
                    //else
                        //game.getScreenManager().pushScreen("main", "blend");
                //} else {
                    game.getScreenManager().pushScreen("main", "blend");
                //}
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void hide() {

    }

}
