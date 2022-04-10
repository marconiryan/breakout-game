# Breakout Game
> #### Meu primeiro jogo e projeto feito em Java, utilizando apenas recursos built-in 
### Objetivo
#### O objetivo do jogo é conseguir o maior número de pontos sem deixar a bola cair. 
<img src="https://github.com/marconiryan/breakout-game/blob/master/screenshot/game.gif" width="600" height="400" />

## 
#### Para cada bloco destruido soma-se pontos. Cada cor de bloco possui uma pontuação especifica:
* Amarelo 4 pontos;
* Verde 8 pontos;
* Laranja 12 pontos;
* Vermelho 16 pontos.

~~~ Java
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
~~~
~~~ Java
public void updateScore(int value){
        score += value * 4;
    }
~~~

## 
#### A cada nivel a velocidade da bola aumenta em 1.5x.
~~~ Java
public void updateLevel(ArrayList<Blocks> blocks, Game game, Ball ball){
    if(this.score == level * 960){
        level++;
        blocks.clear();
        game.drawBlocks();
        ball.updateSpeed(1.5);
    }
~~~ 
## 
#### O jogador pode deixar a bola cair no máximo três vezes, caso contrário o jogo se encerra.
<img src="https://github.com/marconiryan/breakout-game/blob/master/screenshot/gameover.PNG" width="600" height="400" />

~~~ Java
public boolean GameOver(Ball ball){
        if(ball.getPosYBall() > 650){
            lostLife++;
            if(lostLife >= 3){return true;}
            else{ball.setDefaultValues();}}
        return false;
    }
~~~
### Jogabilidade
* **A** Move para a esquerda;
* **D** Move para a direita;
* **Espaço** Aumenta a velocidade da bola;
* **Enter** Reinicia o jogo.
##
#### Implementação do teclado
~~~ Java
public class Keyboard implements KeyListener {
   [...]
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
~~~ 
#### Movimentação do jogador
~~~ Java
public void update(Keyboard keyboardPlayer){
        if (keyboardPlayer.keyRight){x += speed;}
        else if (keyboardPlayer.keyLeft){x -= speed;}
~~~ 
#### Aceleração da bola
~~~ Java
public void update(int coordX, int coordY){
        getCollision(coordX,coordY);
        double speedUp = 0;
        if (keyboardBall.keySpeed){speedUp = 1.5;}
        this.x -= speedX + speedUp * speedX;
        this.y += speedY + speedUp * speedY;
~~~
#### Reiniciar o jogo
~~~ Java
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
~~~

