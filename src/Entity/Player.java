package Entity;

import Main.Keyboard;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    int x,y;
    BufferedImage imagePlayer;

    public Player(){
        setDefaultValues();
        try {
            imagePlayer = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/player.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        this.x = 350;
        this.y = 500;
        speed = 12;
    }

    public int getPlayerX(){
        return this.x;
    }
    public int getPlayerY(){
        return this.y;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(imagePlayer,x,y,80,14,null);
    }
    public void update(Keyboard keyboardPlayer){
        if (keyboardPlayer.keyRight){x += speed;}
        else if (keyboardPlayer.keyLeft){x -= speed;}


    }


}
