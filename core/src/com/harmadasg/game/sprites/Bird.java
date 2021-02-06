package com.harmadasg.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bird {

    private static final int GRAVITY = -15;

    private Vector2 position;
    private Vector2 velocity;
    private Texture bird;

    public Bird(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        bird = new Texture("bird.png");
    }

    public void update(float deltaTime) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY);
        }
        position.add(0, velocity.y * deltaTime);
        if (position.y < 0) {
            position.y = 0;
        }
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
