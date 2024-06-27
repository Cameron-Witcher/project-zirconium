package me.quickscythe.zirconium.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import me.quickscythe.zirconium.utils.FixtureProperties;

public class GameObject {
    private final World WORLD;
    private final Body BODY;
    private final FixtureProperties FIXTURE_PROPERTIES;
    float speed = 1.8f;
    private TextureRegion tex;
    private boolean onGround = false;

    public GameObject(World world, Vector2 location, FixtureProperties fixtureProperties) {
        this(world, location, BodyDef.BodyType.StaticBody, fixtureProperties);
    }

    public GameObject(World world, Vector2 location, BodyDef.BodyType bodyType, FixtureProperties fixtureProperties) {


        this.WORLD = world;
        this.FIXTURE_PROPERTIES = fixtureProperties;

        tex = new TextureRegion(new Texture("badlogic.jpg"));
        BodyDef bodyDef = new BodyDef();

        bodyDef.type = bodyType;
        bodyDef.position.set(location);
        bodyDef.fixedRotation = true;
        BODY = world.createBody(bodyDef);
        BODY.createFixture(this.FIXTURE_PROPERTIES);
        BODY.setUserData(this);
        fixtureProperties.dispose();


    }

    public void setTexture(String filepath) {
        tex = new TextureRegion(new Texture(filepath));
    }

    public World getWorld() {
        return WORLD;
    }

    public void applyImpulse(float x, float y) {
        applyImpulse(x, y, BODY.getPosition(), true);
    }

    public void applyImpulse(float x, float y, Vector2 location) {
        applyImpulse(x, y, location, true);
    }

    public void applyImpulse(float x, float y, Vector2 location, boolean wake) {
        BODY.applyLinearImpulse(new Vector2(x, y), location, wake);
    }
//    public Body getBody() {
//        return BODY;
//    }

    public Vector2 getLocation() {
        return BODY.getPosition();
    }

    public FixtureProperties getFixtureProperties() {
        return FIXTURE_PROPERTIES;
    }

    public float getWidth() {
        return getFixtureProperties().getShapeProperties().width == 0 ? FIXTURE_PROPERTIES.getShapeProperties().radius : FIXTURE_PROPERTIES.getShapeProperties().width;
    }

    public float getHeight() {
        return getFixtureProperties().getShapeProperties().height == 0 ? FIXTURE_PROPERTIES.getShapeProperties().radius : FIXTURE_PROPERTIES.getShapeProperties().height;
    }

    public void update() {

    }

    public void draw(SpriteBatch batch, Camera camera) {
        batch.draw(tex, (getLocation().x - (getWidth()))- camera.position.x , (getLocation().y - (getHeight()))-camera.position.y, getWidth() * 2, getHeight() * 2);
    }

    public void moveLeft() {
        applyImpulse(-(onGround() ? speed : speed * 0.65f) * getMass(), 0);
    }

    public void moveRight() {
        applyImpulse((onGround() ? speed : speed * 0.65f) * getMass(), 0);
    }

    public void jump() {
        if (onGround()) applyImpulse(0, 150f * getMass());
    }

    public float getMass() {
        return BODY.getMass();
    }

    public double getAngle() {
        return BODY.getAngle();
    }


    public boolean onGround() {
        for (Contact contact : WORLD.getContactList()) {
            if (contact.getFixtureA().getBody().equals(BODY) || contact.getFixtureB().getBody().equals(BODY))
                return true;
        }
        return false;
    }
}
