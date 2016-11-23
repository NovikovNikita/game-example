package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.PlatformerGame;


/**
 * Created by Никита on 22.11.2016.
 */
public class Player {
    public static final int MOVEMENT_ON_X = 100;
    public static final int RESISTANCE = -10;
    public static final int GRAVITY = -10;

    private Vector2 velocity;
    private Vector2 position;
    private Texture image;
    private Rectangle bounds;
    private boolean stayOnOBject;

    public Player(){
        image = new Texture("player.png");
        position = new Vector2(0, 50);
        velocity = new Vector2(0, 0);
        bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
        stayOnOBject = false;
    }

    public Vector2 getPosition(){
        return position; }

    public Texture getImage() {
        return image; }

    public Rectangle getBounds (){
        return bounds; }

    public boolean isStayOnOBject() {
        return stayOnOBject; }

    public void setStayOnOBject(boolean stayOnOBject) {
        this.stayOnOBject = stayOnOBject;
        if (stayOnOBject){
            resetVelocity();
        }
    }
    public void jump(){
        velocity.y = 250;
    }

    public void right(){
        velocity.x = MOVEMENT_ON_X;
    }

    public void left(){
        velocity.x = MOVEMENT_ON_X;
    }

    private void resetVelocity() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public void update(float delta){
        if (position.y > 0){
            velocity.add(0, GRAVITY);
        }

        //Calculation new position
        if (velocity.x != 0){
            position.add(velocity.x * delta, 0);
        }
        if (!stayOnOBject || velocity.y > 0){
            position.add(0, velocity.y * delta);
        }

        //Checking overlaps with game world bounds
        if (position.y < 0){
            position.y = 0;
            resetVelocity();
        }
        if (position.x < 0){
            position.x = 0;
            resetVelocity();
        }
        if (position.y > PlatformerGame.HEIGHT - image.getHeight()){
            position.y = PlatformerGame.HEIGHT - image.getHeight();
            resetVelocity();
        }
        if (position.x > PlatformerGame.WIDTH - image.getWidth()){
            position.x = PlatformerGame.WIDTH - image.getWidth();
            resetVelocity();
        }
        bounds.setPosition(position);
    }

    public void dispose(){
        image.dispose();
    }
}
