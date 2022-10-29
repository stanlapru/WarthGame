package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;
import de.eskalon.commons.core.ManagedGame;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;
import de.eskalon.commons.screen.transition.ScreenTransition;
import de.golfgl.gdxgamesvcs.IGameServiceClient;
import de.golfgl.gdxgamesvcs.IGameServiceListener;
import de.golfgl.gdxgamesvcs.NoGameServiceClient;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ManagedGame<ManagedScreen, ScreenTransition> implements IGameServiceListener{

	public static final String LEADERBOARD1 = "";
	public static final String ACHIEVEMENT1 = "CgkIgszi1rUYEAIQAQ";
	public static final String EVENT1 = "";

	// DO NOT FIDDLE WITH THIS CLASS. Even the slightest change could cause crashes.

	public IGameServiceClient gsClient;

	@Override
	public final void create() {
		super.create();
		System.out.println("DEBUG: Game opened.");
		Main.this.getScreenManager().addScreen("load", new LoadingScreen());
		Main.this.getScreenManager().pushScreen("load",null);

		if (gsClient == null)
			gsClient = new NoGameServiceClient();

		// for getting callbacks from the client
		gsClient.setListener(this);

		// establish a connection to the game service without error messages or login screens
		gsClient.resumeSession();
	}
	@Override
	public void pause() {
		super.pause();
		gsClient.pauseSession();
	}

	@Override
	public void resume() {
		super.resume();
		gsClient.resumeSession();
	}

	@Override
	public void gsOnSessionActive() {

	}

	@Override
	public void gsOnSessionInactive() {

	}

	@Override
	public void gsShowErrorToUser(GsErrorType et, String msg, Throwable t) {

	}
}