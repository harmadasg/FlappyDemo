package com.harmadasg.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Animation {

    private final List<TextureRegion> frames;
    private final float maxFrameTime;
    private final int frameCount;

    private float currentFrameTime;
    private int currentFrame;

    public Animation(final Texture texture, final int frameCount, final float cycleTime) {
        int frameWidth = texture.getWidth() / frameCount;
        this.frames = getFrames(texture, frameCount, frameWidth);
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / frameCount;
        this.currentFrame = 0;
        this.currentFrameTime = 0;
    }

    public TextureRegion getCurrentFrame() {
        return frames.get(currentFrame);
    }

    public void update(final float deltaTime) {
        currentFrameTime += deltaTime;
        if (isFrameTimeOver()) {
            currentFrame++;
            currentFrameTime = 0;
        }
        if (isLastFrame()) {
            currentFrame = 0;
        }
    }

    private boolean isFrameTimeOver() {
        return currentFrameTime > maxFrameTime;
    }

    private boolean isLastFrame() {
        return currentFrame == frameCount;
    }

    private static List<TextureRegion> getFrames(Texture texture, int frameCount, int frameWidth) {
        return Stream.iterate(0, i -> i + 1)
                .limit(frameCount)
                .map(i -> new TextureRegion(texture, i * frameWidth, 0, frameWidth, texture.getHeight()))
                .collect(Collectors.toList());
    }
}
