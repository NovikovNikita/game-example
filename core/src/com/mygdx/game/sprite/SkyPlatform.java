package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Никита on 18.11.2016.
 */
public class SkyPlatform {
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;

    public SkyPlatform(Vector2 position) {
        this.image = new Texture("sky-platform.png");
        this.position = position;
        this.bounds = new Rectangle(this.position.x, this.position.y, MyGdxGame.WIDTH, this.image.getHeight());
    }

    public Texture getImage() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean collide(Rectangle player){
        return bounds.overlaps(player);
    }

    public void dispose(){
        image.dispose();
    }
}
