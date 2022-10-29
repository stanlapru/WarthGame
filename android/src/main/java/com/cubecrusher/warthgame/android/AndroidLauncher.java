package com.cubecrusher.warthgame.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import de.golfgl.gdxgamesvcs.GpgsClient;
import de.golfgl.gdxgamesvcs.IGameServiceIdMapper;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cubecrusher.warthgame.GpgsMappers;
import com.cubecrusher.warthgame.Main;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication {

	private GpgsClient gpgsClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
				| View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);

		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();

		Main game = new Main();
		this.gpgsClient = new GpgsClient(){
			@Override
			public boolean submitEvent(String eventId, int increment) {
				return super.submitEvent(GpgsMappers.mapToGpgsEvent(eventId), increment);
			}
		}.setGpgsAchievementIdMapper(new IGameServiceIdMapper<String>() {
			@Override
			public String mapToGsId(String independantId) {
				return GpgsMappers.mapToGpgsAchievement(independantId);
			}
		}).setGpgsLeaderboardIdMapper(new IGameServiceIdMapper<String>() {
			@Override
			public String mapToGsId(String independantId) {
				return GpgsMappers.mapToGpgsLeaderboard(independantId);
			}
		}).initialize(this, true);
		game.gsClient = this.gpgsClient;
		initialize(game, configuration);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		//gpgsClient.onGpgsActivityResult(requestCode, resultCode, data);
	}
}