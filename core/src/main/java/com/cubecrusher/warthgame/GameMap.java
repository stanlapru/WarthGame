package com.cubecrusher.warthgame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.eskalon.commons.screen.ManagedScreen;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameMap extends ManagedScreen {

    // Todo: Fiddle with JSON map parsing. This WILL be a big pain in the butthole, because it's Java.
    // For now, raw map vertices can be used. Must be changed to JSON to allow custom maps in the future.

    //Batch batch = new Batch();
    TextureRegion region = new TextureRegion();
    //ShapeDrawer drawer = new ShapeDrawer(batch, region);

    public void drawMap() {
        //batch.begin();
        //batch.end();
    }

    @Override
    protected void create() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
