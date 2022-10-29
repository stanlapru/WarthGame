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
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.transition.impl.BlendingTransition;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameMap extends ManagedScreen implements InputProcessor, GestureDetector.GestureListener {

    // Todo: We're now ready to make a map! Grab that Adobe Illustrator and get verticing!

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
    private OrthographicCamera camera;
    private Polygon polygon;
    private final float[] polReg = new float[]{0.0f, 0.0f, 100f, 100f, 100f, 0f};
    private Main game;
    private boolean touchDown = false;
    private float currentZoom;
    private Vector3 tp;

    public GameMap(){
        this.game = (Main) Gdx.app.getApplicationListener();
    }

    public void region(){
        polygon = new Polygon();
        polygon.setVertices(polReg);
    }

    public boolean isShapeClicked(Polygon polygon, boolean touchedDown){
        return (polygon.contains(tp.x, tp.y) && touchedDown);
    }

    @Override
    protected void create() {
        System.out.println("DEBUG: GameMap called.");

        camera = new OrthographicCamera();
        camera.position.set(0,0,0);
        camera.setToOrtho(true);

        settings = new Settings();
        mainScr = new MainScreen();

        game.getScreenManager().addScreen("gamemap", new GameMap());
        VisLabel verLabel = new VisLabel(mainScr.getVersion()+"     Test: Background should flash on shape click.");
        verLabel.setPosition(10,0);
        VisTextButton exitBtn = new VisTextButton("Return to Main");
        exitBtn.setPosition(20,50);
        VisTextButton resetCamBtn = new VisTextButton("Center Camera");
        resetCamBtn.setPosition(20,100);

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("DEBUG: MainMenu called.");
                Gdx.input.setInputProcessor(null);
                game.getScreenManager().pushScreen("main", null);
            }
        });

        resetCamBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                camera.position.set(0,0,0);
            }
        });
        stage.addActor(exitBtn);
        stage.addActor(resetCamBtn);
        stage.addActor(verLabel);
        stage.setDebugAll(settings.getDebugEnabled());

        inputMultiplexer = new InputMultiplexer(stage,this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        region();

        tp = new Vector3();
        tp.set(-10000,-10000,0);

        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap);
        region = new TextureRegion(texture, 0, 0, 1, 1);
        drawer = new ShapeDrawer(batch, region);
    }

    @Override
    public void hide() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f,0.05f,0.1f,1);

        if (isShapeClicked(polygon, Gdx.input.justTouched())){
            // Todo: this wrongly activates on interacting with UI when last click was inside the shape.
            System.out.println("DEBUG: Shape clicked.");
            Gdx.gl.glClearColor(0.1f,0.1f,0.15f,1);
            touchDown = false;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawer.polygon(polygon);
        batch.end();
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
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();

        camera.translate(-x,-y);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (screenX == width)
            camera.translate(50,0);
        if (screenX == 0)
            camera.translate(-50,0);
        if (screenY == height)
            camera.translate(0,50);
        if (screenY == 0)
            camera.translate(0,-50);

        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {

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
