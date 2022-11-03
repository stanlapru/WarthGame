package com.cubecrusher.warthgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cubecrusher.warthgame.maps.Belarus6;
import com.cubecrusher.warthgame.windows.game.PauseD;
import com.cubecrusher.warthgame.windows.game.RegionInfoW;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.eskalon.commons.screen.ManagedScreen;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameMap extends ManagedScreen implements InputProcessor, GestureDetector.GestureListener {

    private PolygonSpriteBatch batch = new PolygonSpriteBatch();
    private SpriteBatch sBatch = new SpriteBatch();
    private ShapeDrawer drawer;
    private Texture texture;
    private Settings settings;
    private TextureRegion region;
    private Stage stage = new Stage();
    private Pixmap pixmap;
    private MainScreen mainScr;
    private InputMultiplexer inputMultiplexer;
    private RegionInfoW regionInfoW;
    private PauseD pauseD;
    private OrthographicCamera camera;
    protected Polygon[] polygons;
    private String[] regionNames;
    private float[] polReg;
    private float[] capitals;
    private Main game;
    private boolean touchDown, dragged = false;
    private float currentZoom;
    private Belarus6 belarus6;
    private String regionName = "";
    private Vector3 tp;
    private int latestTouchedRegionId = -1;
    public int movesInTurn = 0;

    public GameMap(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }

    public void region(){
        belarus6 = new Belarus6();
        polygons = new Polygon[belarus6.getRegionAmt()];
        regionNames = new String[belarus6.getRegionAmt()];
        for (int i = 0; i <= polygons.length-1; i++) {
            polygons[i] = new Polygon();
            polReg = belarus6.getVertices(i);
            regionNames[i] = belarus6.getRegionName(i);
            polygons[i].setVertices(polReg);
        }
    }

    public Integer getClickedShapeId(){
        int id = -1;
        for (int i = 0; i <= polygons.length-1; i++) {
            if (polygons[i].contains(tp.x, tp.y)){
                return i;
            }
        }
        return id;
    }

    public boolean isShapeClicked(){
        for (int i = 0; i <= polygons.length-1; i++) {
            if (polygons[i].contains(tp.x, tp.y)){
                return true;
            }
        }
        return false;
    }

    public String getClickedShapeName(){
        return belarus6.getRegionName(getClickedShapeId());
    }

    @Override
    protected void create() {
        System.out.println("DEBUG: GameMap called.");

        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        pauseD = new PauseD();
        settings = new Settings();
        mainScr = new MainScreen();

        VisLabel verLabel = new VisLabel(mainScr.getVersion());
        verLabel.setPosition(10,0);
        VisTextButton pauseBtn = new VisTextButton("Pause");
        pauseBtn.setPosition(20,Gdx.graphics.getHeight()-80);

        pauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("DEBUG: PauseD called.");
                stage.addActor(pauseD);
                pauseD.fadeIn();
                pauseD.setVisible(true);
            }
        });

        region();

        stage.addActor(pauseBtn);
        stage.addActor(verLabel);
        stage.setDebugAll(settings.getDebugEnabled());

        inputMultiplexer = new InputMultiplexer(stage,this);

        tp = new Vector3();
        tp.set(-10000,-10000,0);

        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA4444);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap);
        region = new TextureRegion(texture, 0, 0, 1, 1);
        drawer = new ShapeDrawer(batch, region);

        camera.position.set(0,0,0);
    }

    @Override
    public void hide() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.getInputProcessor() != inputMultiplexer)
            Gdx.input.setInputProcessor(inputMultiplexer);

        Gdx.gl.glClearColor(0.05f, 0.05f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (int i = 0; i <= polygons.length - 1; i++) {

            drawer.setColor(belarus6.getRegionColor(i));
            drawer.filledPolygon(polygons[i]);
            drawer.setColor(Color.BLACK);
            drawer.setDefaultLineWidth(2);
            drawer.polygon(polygons[i]);

            if (touchDown && !dragged && isShapeClicked()) {
                touchDown = false;
                regionInfoW = new RegionInfoW("");
                if (latestTouchedRegionId == -1) regionInfoW.remove();
                regionInfoW = new RegionInfoW(getClickedShapeName());
                stage.addActor(regionInfoW);
                regionInfoW.fadeIn();
                regionInfoW.setVisible(true);
                latestTouchedRegionId = getClickedShapeId();
            } else {
                latestTouchedRegionId = -1;
            }
        }
        for (int i = 0; i < 6; i++) {
            drawer.setColor(Color.WHITE);
            drawer.filledCircle(belarus6.getCapitalPos(i)[0], belarus6.getCapitalPos(i)[1],5);
        }

        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        batch.dispose();
        sBatch.dispose();
        pixmap.dispose();
        texture.dispose();
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(tp.set(screenX, screenY, 0));
        touchDown = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchDown = false;
        dragged = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();
        dragged = true;
        camera.translate(-x,-y);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        //camera.zoom = amountX*0.01f + currentZoom;
        //camera.update();
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX * currentZoom,deltaY * currentZoom);
        camera.update();

        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        currentZoom = camera.zoom;
        camera.update();
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        this.camera.zoom = (initialDistance / distance) * currentZoom;
        camera.update();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
