package com.harmadasg.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private static final int FRAME_COUNT = 3;
    private static final float CYCLE_TIME = 0.5f;
    private static final float VOLUME = 0.5f;
    private static final int JUMP_OFFSET = 300;

    private final Vector2 position;
    private final Vector2 velocity;
    private final Texture animationTexture;
    private final Animation birdAnimation;
    private final Rectangle bounds;
    private final Sound flap;

    public Bird(final int x, final int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        animationTexture = new Texture("birdanimation.png");
        birdAnimation = new Animation(animationTexture, FRAME_COUNT, CYCLE_TIME);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        int actualTextureWidth = animationTexture.getWidth() / FRAME_COUNT;
        bounds = new Rectangle(x, y, actualTextureWidth, animationTexture.getHeight());
    }

    public void update(final float deltaTime) {
        birdAnimation.update(deltaTime);
        if (isFlying()) {
            velocity.add(0, GRAVITY);
        }
        position.add(MOVEMENT * deltaTime, velocity.y * deltaTime);
        if (isOnGround()) {
            position.y = 0;
        }
        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.add(0, JUMP_OFFSET);
        flap.play(VOLUME);
    }

    public void dispose() {
        animationTexture.dispose();
        flap.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getCurrentFrame();
    }

    private boolean isOnGround() {
        return position.y < 0;
    }

    private boolean isFlying() {
        return position.y > 0;
    }
}
