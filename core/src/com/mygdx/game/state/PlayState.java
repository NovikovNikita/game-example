package com.mygdx.game.state;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprite.Ground;
import com.mygdx.game.sprite.SkyPlatform;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Никита on 18.11.2016.
 */
public class PlayState extends AbstractState {
    private Texture backgroundImg;
    private BitmapFont font;
    private int counter;
    private final Ground ground;
    private Set<SkyPlatform> platforms;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        backgroundImg = new Texture("sky.png");
        counter = 0;
        font = new BitmapFont();
        ground = new Ground();
        platforms = new HashSet<SkyPlatform>();
        platforms.add(new SkyPlatform(new Vector2(0, 300)));
        platforms.add(new SkyPlatform(new Vector2(MyGdxGame.WIDTH/2 + 30, 150)));
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        font.draw(batch, "Score: " + counter, 10, MyGdxGame.HEIGHT - 20);
        batch.draw(ground.getImage(), ground.getPosition().x, ground.getPosition().y);
        batch.draw(ground.getImage(), ground.getPosition().x + ground.getImage().getWidth(), ground.getPosition().y);
        for (SkyPlatform platform :
                platforms) {
            batch.draw(platform.getImage(), platform.getPosition().x, platform.getPosition().y);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
    }
}
