package me.quickscythe.zirconium;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import me.quickscythe.zirconium.objects.GameObject;
import me.quickscythe.zirconium.objects.Player;
import me.quickscythe.zirconium.utils.FixtureProperties;
import me.quickscythe.zirconium.utils.GameUtils;
import me.quickscythe.zirconium.utils.ShapeProperties;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    World world;
    Box2DDebugRenderer debugRenderer;
    Player player;
    Array<GameObject> objects = new Array<>();
    OrthographicCamera camera;
    ShapeRenderer fill_shape;
    ShapeRenderer outline_shape;
    public Stage stage;

    @Override
    public void create() {
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -50), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        fill_shape = new ShapeRenderer();
        outline_shape = new ShapeRenderer();
        stage = new Stage(new ScreenViewport());
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        camera.setToOrtho(false, 1280, 720);

        GameObject floor = new GameObject(world, new Vector2(0, 10), new FixtureProperties(0, 0.8f, 0, new ShapeProperties(1000, 50f)));

        objects.add(floor);


        GameObject something = new GameObject(world, new Vector2(300, 500), BodyDef.BodyType.DynamicBody, new FixtureProperties(0.5f, 0.8f, 0.6f, new ShapeProperties(50, 50)));
        something.setTexture("book.png");
        objects.add(something);


        player = new Player(world, new Vector2(10, 500));


        objects.add(player);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        camera.position.add(0, 1, 0);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (GameObject ob : objects) {
            ob.draw(batch, camera);

        }
//        batch.draw(img, (player.getPosition().x - player.),
//                player.getPosition().y - bodyHeight,
//                BOX_TO_WORLD * bodyWidth * 2,
//                BOX_TO_WORLD * bodyHeight * 2);
        batch.end();
        if (GameUtils.isDebug()) {
            debugRenderer.render(world, camera.combined);
//            System.out.println("Player on ground: " + player.onGround());
            System.out.println("Camera coords: (" + camera.position.x + ", " + camera.position.y + ")");
//            camera.position.set(0,0,0);
//            camera.position.add(1, 0, 0);
        }
        update();

    }

    public void update() {
//		float frameTime = Math.min(Gdx.graphics.getDeltaTime(), 0.25f);
//		accumulator += frameTime;
//		while (accumulator >= Constants.TIME_STEP) {
//			world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
//			accumulator -= Constants.TIME_STEP;
//		}
//        // Create our body definition

//        camera.position.set(player.getLocation().x, 0, player.getLocation().y);

//        camera.view.set(camera.position.x-camera.viewportWidth/2,camera.position.y-camera.viewportHeight/2,camera.viewportWidth,camera.viewportHeight);

        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            GameUtils.setDebug(!GameUtils.isDebug());
        }

//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//            player.jump();
//        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.jump();
        }


        world.step(Gdx.graphics.getDeltaTime() * 2, 12, 4);

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
