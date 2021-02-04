package com.harmadasg.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harmadasg.game.FlappyDemo;

public class PlayState extends State {

    private Texture bird;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.bird = new Texture("bird.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2f, FlappyDemo.HEIGHT / 2f);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(bird, 50, 50);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
