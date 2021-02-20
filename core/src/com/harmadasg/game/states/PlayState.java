package com.harmadasg.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harmadasg.game.FlappyDemo;
import com.harmadasg.game.sprites.Bird;
import com.harmadasg.game.sprites.Tube;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.harmadasg.game.sprites.Tube.*;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int CAM_OFFSET = 80;

    private final Bird bird;
    private final Texture background;
    private final List<Tube> tubes;

    public PlayState(final GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2f, FlappyDemo.HEIGHT / 2f);
        tubes = Stream.iterate(1, i -> i + 1)
                .limit(TUBE_COUNT)
                .map(i -> new Tube(i * (TUBE_SPACING + TUBE_WIDTH)))
                .collect(Collectors.toList());
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(final float deltaTime) {
        handleInput();
        bird.update(deltaTime);
        cam.position.x = bird.getPosition().x + CAM_OFFSET;
        tubes.forEach(tube -> {
            if (isTubeOffScreen(tube))
                tube.reposition(getTubeNewPosition(tube.getPositionTopTube().x));
            if (tube.collides(bird.getBounds()))
                gameStateManager.set(new PlayState(gameStateManager));});
        cam.update();
    }

    @Override
    public void render(final SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(background, cam.position.x - cam.viewportWidth / 2, 0);
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        tubes.forEach(tube -> {
            batch.draw(tube.getTextureTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            batch.draw(tube.getTextureBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        });
        batch.end();
    }

    @Override
    public void dispose() {
    }

    private boolean isTubeOffScreen(final Tube tube) {
        float camLeftSidePosition = cam.position.x - cam.viewportWidth / 2;
        float tubeRightSidePosition = tube.getPositionTopTube().x + tube.getTextureTopTube().getWidth();
        return camLeftSidePosition > tubeRightSidePosition;
    }

    private float getTubeNewPosition(final float x) {
        return x + (TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT;
    }
}
