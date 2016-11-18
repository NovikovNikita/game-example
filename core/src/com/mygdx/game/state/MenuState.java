package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Никита on 17.11.2016.
 */
public class MenuState extends AbstractState {
    private Texture backgroundImg;
    private Texture btnPlay;
    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        backgroundImg = new Texture("sky.png");
        btnPlay = new Texture("playbtn.png");
        font = new BitmapFont();
    }

    private void handleInput(){
        if (Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        font.draw(batch, "Press button to start", MyGdxGame.WIDTH/2 - 65, MyGdxGame.HEIGHT/2 + 80);
        batch.draw(btnPlay, MyGdxGame.WIDTH/2 - btnPlay.getWidth()/2,MyGdxGame.HEIGHT/2 - btnPlay.getHeight()/2);
        batch.end();

    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        btnPlay.dispose();
    }
}
