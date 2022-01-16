package Main;
import Entity.Ball;
import Entity.Blocks;
import Entity.Player;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends JPanel implements Runnable {
    double FPS = 60.00;
    Thread gameThread;
    Keyboard keyboard = new Keyboard();
    Player player = new Player();
    Ball ball = new Ball(keyboard);
    ArrayList<Blocks> blocks = new ArrayList<>();
    Information information = new Information();
    BufferedImage background, gameover;

    {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/background.png")));
            gameover = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sprites/gameover.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Game(){
        this.addKeyListener(keyboard);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(800,600));
        this.setFocusable(true);
        drawBlocks();
    }
    public void drawBlocks(){
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,70,15,60,"/Sprites/blockred.png"));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,90,15,60,"/Sprites/blockred.png"));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,110,15,60,"/Sprites/blockorange.png"));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,130,15,60,"/Sprites/blockorange.png"));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,150,15,60,"/Sprites/blockgreen.png"));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,170,15,60,"/Sprites/blockgreen.png"));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,190,15,60,"/Sprites/blockyellow.png"));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,210,15,60,"/Sprites/blockyellow.png"));}
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void restartGame(){
        if(keyboard.keyRestart){
            if(information.GameOver(ball)){
                information.restartGameOver();}
            else{
            ball.setDefaultValues();
            information.setDefaultValues();
            blocks.clear();
            drawBlocks();}
        }
    }
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        Graphics2D graphics2D = (Graphics2D) graph;
        graphics2D.drawImage(background,0,0,800,600,null);

        if(information.GameOver(ball)){
            graphics2D.drawImage(gameover,200,300,400,87,null);
        }

        player.draw(graphics2D);

        ball.draw(graphics2D);
        blocks.forEach(blocks1 -> blocks1.draw(graphics2D));
        information.drawLevel(graphics2D);
        information.drawLife(graphics2D);
        graphics2D.dispose();

    }



    public void update(){
        player.update(keyboard);
        ball.update(player.getPlayerX(), player.getPlayerY());
        blocks.forEach(block -> information.updateScore(block.update(ball)));
        information.updateLevel(blocks,this, ball);
        restartGame();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >=1){
                update();
                repaint();
                delta --;
            }
        }
    }
}
