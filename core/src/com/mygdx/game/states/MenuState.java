package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PlatformerGame;

/**
 * Created by Никита on 17.11.2016.
 */
public class MenuState extends AbstractState {
    private Texture backgroundImg;
    private Texture btnPlay;
    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, PlatformerGame.WIDTH, PlatformerGame.HEIGHT);
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
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        font.draw(batch, "Press button to start", PlatformerGame.WIDTH/2 - 65, PlatformerGame.HEIGHT/2 + 80);
        batch.draw(btnPlay, PlatformerGame.WIDTH/2 - btnPlay.getWidth()/2, PlatformerGame.HEIGHT/2 - btnPlay.getHeight()/2);
        batch.end();

    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        btnPlay.dispose();
        font.dispose();
    }
}
