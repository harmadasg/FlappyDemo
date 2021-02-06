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
        velocity.add(0, GRAVITY);
        position.add(0, velocity.y * deltaTime);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
}
