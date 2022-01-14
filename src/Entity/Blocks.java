package Entity;

import java.awt.*;

public class Blocks extends Rectangle {
    boolean  destroyed = false;
    Color colorBlock;
    int posX, posY, height, width;
    public Blocks(int x, int y, int h , int w, Color color){
        this.posX = x;
        this.posY = y;
        this.height = h;
        this.width = w;
        this.colorBlock = color;
    }
    public void draw(Graphics2D graphics2D){
        if(!destroyed){
        graphics2D.setColor(colorBlock);
        graphics2D.fillRect(posX,posY,width,height);
        }

    }
    public void update(Ball ball){
        int rangeX = 30;
        int rangeY = 10;
        boolean rangeXBlock = ball.getPosXBall() >= posX - rangeX && ball.getPosXBall() <= posX + rangeX;
        boolean rangeYBlock = ball.getPosYBall() >= posY - rangeY && ball.getPosYBall() <= posY + rangeY;
        if(rangeXBlock && rangeYBlock && !destroyed){
            destroyed = true;
            ball.speedY *= -1;
        }
    }
}
