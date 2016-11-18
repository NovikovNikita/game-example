package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Никита on 18.11.2016.
 */
public class Ground {
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;

    public Ground() {
        image = new Texture("platform.png");
        position = new Vector2(0,0);
        bounds = new Rectangle(position.x, position.y, MyGdxGame.WIDTH, image.getHeight());
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
