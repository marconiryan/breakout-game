package Main;
import Entity.Ball;
import Entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    double FPS = 60.00;
    Thread gameThread;
    Keyboard keyboard = new Keyboard();
    Player player = new Player(this, keyboard);
    Ball ball = new Ball(this,keyboard);

    public Game(){
        this.addKeyListener(keyboard);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(800,600));
        this.setFocusable(true);
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
        graphics2D.dispose();
    }
    public void update(){
        player.update(keyboard);
        ball.update(player.getPlayerX(), player.getPlayerY());
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
