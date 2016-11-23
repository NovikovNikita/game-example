package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;


public class GameStateManager {
    private final Stack<AbstractState> states;

    public GameStateManager() {
        this.states = new Stack<AbstractState>();
    }

    public void push(AbstractState item) {
        states.push(item);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(AbstractState state){
        this.pop();
        states.push(state);
    }

    public void update(float delta){
        this.states.peek().update(delta);
    }

    public void render(SpriteBatch batch){
        this.states.peek().render(batch);
    }
}
