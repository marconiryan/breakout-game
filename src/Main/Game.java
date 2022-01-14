package Main;
import Entity.Ball;
import Entity.Blocks;
import Entity.Player;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable {
    double FPS = 60.00;
    Thread gameThread;
    Keyboard keyboard = new Keyboard();
    Player player = new Player(this, keyboard);
    Ball ball = new Ball(this,keyboard);
    ArrayList<Blocks> blocks = new ArrayList<>();

    public Game(){
        this.addKeyListener(keyboard);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(800,600));
        this.setFocusable(true);
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,50,15,60,Color.RED));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,70,15,60,Color.RED));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,90,15,60,Color.ORANGE));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,110,15,60,Color.ORANGE));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,130,15,60,Color.GREEN));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,150,15,60,Color.GREEN));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,170,15,60,Color.YELLOW));}
        for(int i = 0; i < 12; i++){blocks.add(new Blocks(15 +65*i,190,15,60,Color.YELLOW));}

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        Graphics2D graphics2D = (Graphics2D) graph;
        player.draw(graphics2D);
        ball.draw(graphics2D);
        blocks.forEach(blocks1 -> blocks1.draw(graphics2D));
        graphics2D.dispose();

    }
    public void update(){
        player.update(keyboard);
        ball.update(player.getPlayerX(), player.getPlayerY());
        blocks.forEach(block -> block.update(ball));
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
