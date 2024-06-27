package me.quickscythe.zirconium.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import me.quickscythe.zirconium.utils.FixtureProperties;
import me.quickscythe.zirconium.utils.ShapeProperties;

public class Player extends GameObject {
    public Player(World world, Vector2 location) {
        super(world, location, BodyDef.BodyType.DynamicBody, new FixtureProperties(10f, 0.3f, 0f, new ShapeProperties(16f, 16f)));
        setTexture("book.png");
        speed = 4f;
    }

    @Override
    public void draw(SpriteBatch batch, Camera camera) {
        super.draw(batch, camera);
        camera.position.x = getLocation().x-camera.viewportWidth/2;
        camera.position.y = getLocation().y-camera.viewportHeight/2;
    }
}
