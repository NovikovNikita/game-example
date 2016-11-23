package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Никита on 17.11.2016.
 */
public abstract class AbstractState {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public AbstractState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();
}
