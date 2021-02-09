package com.harmadasg.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harmadasg.game.FlappyDemo;
import com.harmadasg.game.sprites.Bird;
import com.harmadasg.game.sprites.Tube;

public class PlayState extends State {

    private Bird bird;
    private Texture background;
    private Tube tube;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2f, FlappyDemo.HEIGHT / 2f);
        tube = new Tube(100);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
        }
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
        batch.draw(background, cam.position.x - cam.viewportWidth / 2, 0);
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        batch.draw(tube.getTextureTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
        batch.draw(tube.getTextureBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
