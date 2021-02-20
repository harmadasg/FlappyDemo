package com.harmadasg.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 150;

    private final Texture topTube, bottomTube;
    private final Vector2 positionTopTube;
    private final Vector2 positionBottomTube;
    private final Random random;

    public Tube(final float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();
        positionTopTube = new Vector2();
        positionBottomTube = new Vector2();
        reposition(x);
    }

    public void reposition( final float x) {
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }

    public Texture getTextureTopTube() {
        return topTube;
    }

    public Texture getTextureBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }
}
