package com.harmadasg.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private final Vector2 position;
    private final Vector2 velocity;
    private final Texture bird;

    public Bird(final int x, final int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        bird = new Texture("bird.png");
    }

    public void update(final float deltaTime) {
        if (position.y > 0)
            velocity.add(0, GRAVITY);
        position.add(MOVEMENT * deltaTime, velocity.y * deltaTime);
        if (position.y < 0)
            position.y = 0;
    }

    public void jump() {
        velocity.add(0, 250);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
}
