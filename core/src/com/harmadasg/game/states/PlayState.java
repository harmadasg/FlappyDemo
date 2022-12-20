package com.harmadasg.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
    private static final int GROUND_Y_OFFSET = -50;

    private final Bird bird;
    private final Texture background;
    private final Texture ground;
    private final Vector2 groundPosition1, groundPosition2;
    private final List<Tube> tubes;

    public PlayState(final GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(getCamPositionFarLeft(), GROUND_Y_OFFSET);
        groundPosition2 = new Vector2(getCamPositionFarLeft() + ground.getWidth(), GROUND_Y_OFFSET);
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
        updateGround(groundPosition1);
        updateGround(groundPosition2);
        bird.update(deltaTime);
        cam.position.x = bird.getPosition().x + CAM_OFFSET;
        tubes.forEach(tube -> {
            if (isTubeOffScreen(tube))
                tube.reposition(getTubeNewPosition(tube.getPositionTopTube().x));
            if (tube.collides(bird.getBounds()))
                restart();});
        if (hitGround())
            restart();
        cam.update();
    }

    @Override
    public void render(final SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(background, getCamPositionFarLeft(), 0);
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        tubes.forEach(tube -> {
            batch.draw(tube.getTextureTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            batch.draw(tube.getTextureBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        });
        batch.draw(ground, groundPosition1.x, groundPosition1.y);
        batch.draw(ground, groundPosition2.x, groundPosition2.y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        tubes.forEach(Tube::dispose);
        System.out.println("Play State disposed");
    }

    private boolean hitGround() {
        return bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET;
    }

    private void restart() {
        gameStateManager.set(new PlayState(gameStateManager));
    }

    private void updateGround(Vector2 groundPosition) {
        if (getCamPositionFarLeft() > groundPosition.x + ground.getWidth()) {
            groundPosition.add(ground.getWidth() * 2, 0);
        }
    }

    private boolean isTubeOffScreen(final Tube tube) {
        float camLeftSidePosition = getCamPositionFarLeft();
        float tubeRightSidePosition = tube.getPositionTopTube().x + tube.getTextureTopTube().getWidth();
        return camLeftSidePosition > tubeRightSidePosition;
    }

    private float getTubeNewPosition(final float x) {
        return x + (TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT;
    }

    private float getCamPositionFarLeft() {
        return cam.position.x - cam.viewportWidth / 2;
    }
}
