package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public boolean keyDown = false,keyUp = false, keyRight = false, keyLeft = false, keySpeed= false;

    @Override
    public void keyTyped(KeyEvent e) {
        //nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){keyUp = true;}
        if (code == KeyEvent.VK_S){keyDown = true;}
        if (code == KeyEvent.VK_A){keyLeft = true;}
        if (code == KeyEvent.VK_D){keyRight = true;}
        if (code == KeyEvent.VK_SPACE){keySpeed = true;}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){keyUp = false;}
        if (code == KeyEvent.VK_S){keyDown = false;}
        if (code == KeyEvent.VK_A){keyLeft = false;}
        if (code == KeyEvent.VK_D){keyRight = false;}
        if (code == KeyEvent.VK_SPACE){keySpeed = false;}
    }
}
