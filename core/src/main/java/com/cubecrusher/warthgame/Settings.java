package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
    public Preferences prefs;

    private float sound; // Sound volume
    private float music; // Music volume
    private String version; // Last played version
    private boolean privacy; // Privacy Policy acknowledged
    private boolean debug; // Display debug information

    public Settings(){
        prefs = Gdx.app.getPreferences("WarthSettings");
        sound = prefs.getFloat("sound",1.0f);
        music = prefs.getFloat("music",1.0f);
        version = prefs.getString("version", "");
        privacy = prefs.getBoolean("privacy", false);
        debug = prefs.getBoolean("debug",true);
    }

    public void setSoundVolume(float volume){
        this.sound = volume;
        prefs.putFloat("sound",volume);
        prefs.flush();
    }

    public void setMusicVolume(float volume){
        this.music = volume;
        prefs.putFloat("music",volume);
        prefs.flush();
    }

    public void setVersion(String version){
        this.version = version;
        prefs.putString("version",version);
        prefs.flush();
    }

    public void setPrivacyAcknowledged(boolean privacy){
        this.privacy = privacy;
        prefs.putBoolean("privacy",privacy);
        prefs.flush();
    }

    public void setDebug(boolean enabled){
        this.debug = enabled;
        prefs.putBoolean("debug",enabled);
        prefs.flush();
    }

    public float getSoundVolume(){
        return sound;
    }

    public float getMusicVolume(){
        return music;
    }

    public boolean getDebugEnabled(){
        return debug;
    }

    public String getVersion(){
        return version;
    }

    public boolean getPrivacyAcknowledged(){
        return privacy;
    }
}
