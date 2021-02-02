package com.harmadasg.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harmadasg.game.FlappyDemo;

public class MenuState extends State{

    private final Texture background;
    private final Texture playButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        batch.draw(playButton, (FlappyDemo.WIDTH - playButton.getWidth()) / 2f, FlappyDemo.HEIGHT / 2f);
        batch.end();
    }
}
