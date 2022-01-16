package Entity;

import Main.Keyboard;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Ball extends Entity{
    Keyboard keyboardBall;
    int speedX = 6;
    int speedY = 6;
    BufferedImage imageBall;

    public Ball(Keyboard keyboard){
        this.x = 530;
        this.y = 300;
        this.keyboardBall = keyboard;
        try {
            imageBall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/ball.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        this.x = 530;
        this.y = 300;
        this.speedY = 6;
        this.speedX = 6;
    }
    public int getPosXBall(){
        return this.x;
    }

    public int getPosYBall(){
        return this.y;
    }

    public void getCollision(int playerX, int playerY){
        int xMin = 0, xMax = 750 - xMin;
        int yMin = 0;
        boolean playerXRange = this.x >= playerX - 50 && this.x <= playerX + 90;
        boolean playerYRange = this.y >= playerY - 20  && this.y <= playerY ;
        if(this.x >= xMax || this.x <= xMin){
            speedX *= -1;
        }
        if(this.y <= yMin){
            speedY *= -1;
        }
        if(playerXRange && playerYRange){speedY *= -1;}

        }


    public void update(int coordX, int coordY){
        getCollision(coordX,coordY);
        double speedUp = 0;
        if (keyboardBall.keySpeed){speedUp = 1.5;}
        this.x -= speedX + speedUp * speedX;
        this.y += speedY + speedUp * speedY;
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(imageBall,x,y,30,30,null);
    }
    public void updateSpeed(double newSpeed){
        this.speedY *= newSpeed;
        this.speedX *= newSpeed;
    }
}
