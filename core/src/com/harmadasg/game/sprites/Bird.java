package com.harmadasg.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private final Vector2 position;
    private final Vector2 velocity;
    private final Texture bird;
    private final Rectangle bounds;

    public Bird(final int x, final int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    public void update(final float deltaTime) {
        if (position.y > 0)
            velocity.add(0, GRAVITY);
        position.add(MOVEMENT * deltaTime, velocity.y * deltaTime);
        if (position.y < 0)
            position.y = 0;
        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.add(0, 300);
    }

    public void dispose() {
        bird.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
}
