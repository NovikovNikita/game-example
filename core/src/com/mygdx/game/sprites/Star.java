package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Никита on 22.11.2016.
 */
public class Star {
    public static final float GRAVITY = -0.8f;

    private Vector2 velocity;
    private Vector2 position;
    private Texture image;
    private Rectangle bounds;
    private boolean visible;

    public Star(Vector2 position){
        this.image = new Texture("star.png");
        this.position = position;
        this.velocity = new Vector2(0, 0);
        this.bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
        this.visible = true;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Texture getImage(){
        return image;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public boolean isVisible(){
        return  visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public boolean collides(Rectangle player){
        return bounds.overlaps(player);
    }

    public void update(float delta){
        if (position.y > 0){
            velocity.add(0, GRAVITY);
        }
        position.add(0, velocity.y * delta);
        bounds.setPosition(position);
    }

    public void dispose(){
        image.dispose();
    }
}
