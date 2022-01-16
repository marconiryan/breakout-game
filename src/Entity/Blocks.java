package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Blocks{
    boolean  destroyed = false;
    int posX, posY, height, width;
    BufferedImage imageBlock;
    String imagetype;

    public Blocks(int x, int y, int h , int w, String imageName){
        this.posX = x;
        this.posY = y;
        this.height = h;
        this.width = w;
        this.imagetype = imageName;
        try {
            imageBlock = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imageName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D graphics2D){
        if(!destroyed){
            graphics2D.drawImage(imageBlock,posX,posY,width,height,null);
        }

    }
    public int update(Ball ball){
        int rangeX = 30;
        int rangeY = 10;
        boolean rangeXBlock = ball.getPosXBall() >= posX - rangeX && ball.getPosXBall() <= posX + rangeX;
        boolean rangeYBlock = ball.getPosYBall() >= posY - rangeY && ball.getPosYBall() <= posY + rangeY;
        if(rangeXBlock && rangeYBlock && !destroyed){
            destroyed = true;
            ball.speedY *= -1;
            switch (imagetype) {
                case "/Sprites/blockred.png":
                    return 4;
                case "/Sprites/blockorange.png":
                    return 3;
                case "/Sprites/blockgreen.png":
                    return 2;
                case "/Sprites/blockyellow.png":
                    return 1;
            }
        }
        return 0;

        }
    }

