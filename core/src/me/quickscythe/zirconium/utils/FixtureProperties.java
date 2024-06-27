package me.quickscythe.zirconium.utils;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import me.quickscythe.zirconium.utils.ShapeProperties;

public class FixtureProperties extends FixtureDef {

    private final ShapeProperties SHAPE_PROPERTIES;

    public FixtureProperties(float density, float friction, float restitution, ShapeProperties shapeProperties) {
        this.SHAPE_PROPERTIES = shapeProperties;
        this.density = density;
        this.friction = friction;
        this.shape = shapeProperties.build();
        this.restitution = restitution;

    }


    public void dispose() {
        shape.dispose();
    }

    public ShapeProperties getShapeProperties() {
        return SHAPE_PROPERTIES;
    }
}
