package com.cubecrusher.warthgame;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.kotcrab.vis.ui.VisUI;

import java.util.Locale;

public class AssetMgr {

    // Responsible for loading assets and playing audio.
    // AssetManager is wonky as heck. I do not recommend dealing with it.

    // MUSIC
    public static Music mMenu;

    // SOUNDS
    public static Sound click, popup, alert, fight, bomb, infantry, plane, heli, horse, warStart, warEnd, reward;

    // FONTS
    public static String titleF;
    public static String primaryF;
    public static String secondaryF;

    public static I18NBundle bundle;

    // TECHNICALS
    public static final AssetManager manager = new AssetManager();
    public static FileHandleResolver resolver = new InternalFileHandleResolver();
    public static float loadProgress = 0.0f;
    public static SkinLoader.SkinParameter skinPar = new SkinLoader.SkinParameter("ui/uiskin.atlas");
    public static String loadState = "Preparing to load game files.";

    public static String progress = ""+loadProgress*100;

    public static boolean preLoaded, allLoaded = false;

    public static void preLoad(){
        loadState = "Preloading...";
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        FreetypeFontLoader.FreeTypeFontLoaderParameter mainP = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        mainP.fontFileName = "fonts/gui.ttf";
        mainP.fontParameters.size = 30;
        manager.load("fonts/gui.ttf", BitmapFont.class, mainP);
        manager.finishLoading();
        preLoaded = true;

        load();
    }
    public static void load(){
        System.out.println("DEBUG: Assets preloaded.");

        //preLoad();

        if (preLoaded) {
            loadState = "Loading localization...";
            manager.setLoader(I18NBundle.class, new I18NBundleLoader(resolver));
            manager.load("locs/warth", I18NBundle.class, new I18NBundleLoader.I18NBundleParameter(Locale.getDefault()));
            //bundle = manager.get("locs/warth", I18NBundle.class);

            loadState = "Loading textures...";
            //manager.setLoader(Texture.class, new TextureLoader(resolver));

            loadState = "Loading GUI...";
            VisUI.load(VisUI.SkinScale.X2);
            //manager.load("ui/uiskin.json", Skin.class, skinPar);

            allLoaded = true;

            loadState = "Game loaded.";

            System.out.println("DEBUG: Assets loaded.");
        }
    }

    /*
    public static void playSound(Sound sound) {
        sound.play();
    }

    public static void playMusic(Music music) {
        music.play();
    }

    public static void pauseMusic(Music music) {
        music.pause();
    }

    public static void stopMusic(Music music){
        music.stop();
    }
    */
}
