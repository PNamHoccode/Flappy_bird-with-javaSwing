/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappy_bird;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author ADMIN
 */
public class Chimney extends Objects {
    
private Rectangle rect;
private boolean isBehindBird=false;

    public Chimney(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x,y,w,h);
    }
    public void update(){
        setPosX(getPosX()-2);//tốc độ di chuyển cảu ống khói phải qua trái = tốc độ di chuyển của ground
        rect.setLocation((int) this.getPosX(),(int) this.getPosY());
    }
    public Rectangle getRect(){
        return rect;
    }
    public void setisBehindBird(boolean  b){
        isBehindBird=b;
    }
    public boolean getIsBehindBird(){
        return isBehindBird;
    }
}
