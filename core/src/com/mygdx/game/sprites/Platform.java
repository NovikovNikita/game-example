package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.PlatformerGame;

/**
 * Created by Никита on 18.11.2016.
 */
public class Platform {
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;

    public Platform(Vector2 position) {
        this.image = new Texture("sky-platform.png");
        this.position = position;
        this.bounds = new Rectangle(this.position.x, this.position.y, PlatformerGame.WIDTH, this.image.getHeight());
    }

    public Texture getImage() {
        return image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean collides(Rectangle player) {
        return bounds.overlaps(player);
    }

    public void dispose(){
        image.dispose();
    }
}
