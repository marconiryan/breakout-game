package Entity;
import Main.Game;
import Main.Keyboard;
import java.awt.*;


public class Player extends Entity {
    Game gamePlayer;
    Keyboard keyboardPlayer;
    int x,y;

    public Player(Game gamePlayer, Keyboard keyboardPlayer){
        this.gamePlayer = gamePlayer;
        this.keyboardPlayer = keyboardPlayer;
        setDefaultValues();
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
        graphics2D.setColor(Color.CYAN);
        graphics2D.fillRect(x,y,90,10);
    }
    public void update(Keyboard keyboardPlayer){
        if (keyboardPlayer.keyRight){x += speed;}
        else if (keyboardPlayer.keyLeft){x -= speed;}


    }


}
