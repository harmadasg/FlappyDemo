package com.harmadasg.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harmadasg.game.FlappyDemo;
import com.harmadasg.game.sprites.Bird;

public class PlayState extends State {

    private Bird bird;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2f, FlappyDemo.HEIGHT / 2f);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        bird.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
