package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.PlatformerGame;
import com.mygdx.game.sprites.Ground;
import com.mygdx.game.sprites.Player;
import com.mygdx.game.sprites.Platform;
import com.mygdx.game.sprites.Star;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Никита on 18.11.2016.
 */
public class PlayState extends AbstractState {
    private final Texture backgroundImg;
    private final BitmapFont font;
    private final Ground ground;
    private final Set<Platform> platforms;
    private final Set<Star> stars;
    private final Player player;
    private final Sound catchSound;
    private int counter;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, PlatformerGame.WIDTH, PlatformerGame.HEIGHT);
        backgroundImg = new Texture("sky.png");
        counter = 0;
        font = new BitmapFont();
        ground = new Ground();
        platforms = new HashSet<Platform>();
        platforms.add(new Platform(new Vector2(0, 300)));
        platforms.add(new Platform(new Vector2(PlatformerGame.WIDTH/2 + 30, 150)));
        stars = new HashSet<Star>();
        for (int i = 0; i < 10; i++){
            stars.add(new Star(new Vector2(i * 70, PlatformerGame.HEIGHT)));
        }
        catchSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        player = new Player();
    }

    private void hadleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.left();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.right();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.jump();
        }
    }

    private void catchStar(){
        for (Star star : stars) {
            if (star.isVisible() && star.collides(player.getBounds())){
                counter++;
                catchSound.play();
                star.setVisible(false);
            }
        }
    }

    private void checkGameOver(){
        boolean gameover = true;
        for (Star star : stars) {
            if (star.getPosition().y > ground.getImage().getHeight()){
                gameover = false;
            }
        }
        if (gameover){
            gsm.set(new GameOverState(gsm, counter));
        }
    }

    @Override
    public void update(float delta) {
        hadleInput();
        player.update(delta);
        for (Star star : stars) {
            star.update(delta);
        }
        catchStar();
        boolean isCollide = false;
        for (Platform platform : platforms) {
            if (platform.collides(player.getBounds())){
                isCollide = true;
            }
        }
        if (ground.collide(player.getBounds())){
            isCollide = true;
        }
        player.setStayOnOBject(isCollide);
        checkGameOver();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        font.draw(batch,String.format("Score: %s", counter), 10, PlatformerGame.HEIGHT - 20);
        batch.draw(ground.getImage(), ground.getPosition().x, ground.getPosition().y);
        batch.draw(ground.getImage(), ground.getPosition().x + ground.getImage().getWidth(), ground.getPosition().y);
        for (Platform platform : platforms) {
            batch.draw(platform.getImage(), platform.getPosition().x, platform.getPosition().y);
        }
        for (Star star : stars) {
            if (star.isVisible()){
                batch.draw(star.getImage(), star.getPosition().x, star.getPosition().y);
            }
        }
        batch.draw(player.getImage(), player.getPosition().x, player.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        font.dispose();
        ground.dispose();
        for (Platform platform : platforms) {
            platform.dispose();
        }
        player.dispose();
        for (Star star : stars) {
            star.dispose();
        }
    }
}
