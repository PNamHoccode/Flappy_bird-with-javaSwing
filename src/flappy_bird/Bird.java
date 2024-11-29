package flappy_bird;

import java.awt.Rectangle;
import java.io.File;
import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

public class Bird extends Objects {

    private float vt = 0;//toc do rơi
    private boolean isLive=true;

    private boolean isFlying = false;//xem no co bay ko
    private   Rectangle rect;
    public  SoundPlayer flapsound,bupsound,moneysound;

    public Bird(int x, int y, int w, int h) {
        super(x, y, w, h);
        rect = new Rectangle(x,y,w,h);
        
        flapsound= new SoundPlayer(new File("Assets/fap.wav"));
        moneysound= new SoundPlayer(new File("Assets/getpoint.wav"));
        bupsound= new SoundPlayer(new File("Assets/die.wav"));
        
    }

    public boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(boolean b) {
        isLive = b;
        
    }
   
    public Rectangle getRect(){
        return rect;
    }

    public void update(Long deltaTime) {//update roi con chim
        vt += Flappy_Bird.Gravity;

        this.setPosY(this.getPosY() + vt);
        this.rect.setLocation((int)this.getPosX(),(int) this.getPosY());

        if (vt < 0) {
            isFlying = true;//nho hon 0 true con chim bay lên
        } else {
            isFlying = false;
        }
    }

    public void fly() {
        vt = -3;
        flapsound.play();
    }

    public boolean getisFlying() {
        return isFlying;
    }
    public void setVT(float vt){
        this.vt=vt;
    }
}
