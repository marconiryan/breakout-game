package Entity;

import Main.Game;
import Main.Keyboard;

import java.awt.*;

public class Ball extends Entity{
    Game gameBall;
    Keyboard keyboardBall;
    int speedX = 9 ;
    int speedY = 9;

    public Ball(Game gameBall, Keyboard keyboard){
        this.gameBall = gameBall;
        this.keyboardBall = keyboard;
        setDefaultValues();
    }
    public void setDefaultValues(){
        this.x = 400;
        this.y = 300;
    }
    public void getCollision(int playerX, int playerY){
        int xMin = 0, xMax = 750 - xMin;
        int yMin = 0, yMax = 600 - yMin;
        boolean playerXRange = this.x >= playerX - 50 && this.x <= playerX + 90;
        boolean playerYRange = this.y >= playerY - 20  && this.y <= playerY ;
        if(this.x >= xMax || this.x <= xMin){
            speedX *= -1;
        }
        if(this.y >= yMax || this.y <= yMin){speedY *= -1;}
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
        graphics2D.setColor(Color.white);
        //graphics2D.drawRect(0,0,800,600);
        graphics2D.drawOval(x,y,30,30);
    }
}
