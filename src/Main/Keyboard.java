package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public boolean keyRight = false, keyLeft = false, keySpeed= false, keyRestart= false;

    @Override
    public void keyTyped(KeyEvent e) {
        //nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A){keyLeft = true;}
        if (code == KeyEvent.VK_D){keyRight = true;}
        if (code == KeyEvent.VK_SPACE){keySpeed = true;}
        if (code == KeyEvent.VK_ENTER){keyRestart = true;}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A){keyLeft = false;}
        if (code == KeyEvent.VK_D){keyRight = false;}
        if (code == KeyEvent.VK_SPACE){keySpeed = false;}
        if (code == KeyEvent.VK_ENTER){keyRestart = false;}
    }
}
