package me.quickscythe.zirconium.utils;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class ShapeProperties {
    public final float type;
    public final float width;
    public final float height;
    public final float radius;

    public ShapeProperties(float width, float height) {
        this.type = 0;
        this.width = width;
        this.height = height;
        this.radius = 0;
    }

    public ShapeProperties(float radius) {
        this.type = 1;
        this.width = 0;
        this.height = 0;
        this.radius = radius;
    }

    public Shape build() {
        Shape shape = new CircleShape();
        if (type == 0) {
            shape = new PolygonShape();
            ((PolygonShape) shape).setAsBox(width, height);

        }
        if (type == 1) {
            shape = new CircleShape();
            shape.setRadius(radius);
        }
        return shape;
    }
}
