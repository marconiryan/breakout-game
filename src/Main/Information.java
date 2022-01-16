package Main;

import Entity.Ball;
import Entity.Blocks;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Information {
    int lostLife = 0;
    int level = 1;
    int score = 0;

    BufferedImage imageLife;
    public Information(){
        try {
            imageLife = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/life.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawLevel(Graphics2D graphics2D){
        graphics2D.setColor(Color.white);
        graphics2D.drawString("LEVEL:%d".formatted(level), 20,590);
        graphics2D.drawString("SCORE:%d".formatted(score), 700,590);

    }
    public void restartGameOver(){
        this.lostLife = -1;
        this.level = 1;
        this.score = 0;
    }
    public void setDefaultValues(){
        this.lostLife = 0;
        this.level = 1;
        this.score = 0;
    }
    public void updateScore(int value){
        score += value * 4;
    }
    public void updateLevel(ArrayList<Blocks> blocks, Game game, Ball ball){
        if(this.score == level * 960){
            level++;
            blocks.clear();
            game.drawBlocks();
            ball.updateSpeed(1.5);

        }
    }

    public void drawLife(Graphics2D graphics2D){
        for(int i = 3; i > lostLife; i--)
            {graphics2D.drawImage(imageLife,600+50*i,10,30,30,null);}
    }
    public boolean GameOver(Ball ball){
        if(ball.getPosYBall() > 650){
            lostLife++;
            if(lostLife >= 3){return true;}
            else{ball.setDefaultValues();}}
        return false;
    }

}

