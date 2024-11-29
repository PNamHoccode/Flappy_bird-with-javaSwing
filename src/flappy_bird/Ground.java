/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappy_bird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author ADMIN
 */
public class Ground {

    private BufferedImage groundImage;

    private int x1, y1, x2, y2;

    public Ground() {
        try {
            groundImage = ImageIO.read(new File("Assets/ground.jpg"));
        } catch (Exception e) {
        }
        x1 = 0;
        y1 = 570;
        x2 = x1 + 736;
        y2 = 570;
    }

    public void update() {
        x1 -= 2;
        x2 -= 2;
        if (x2 < 0) {
            x1 = x2 + 736;
        }
        if (x1 < 0) {
            x2 = x1 + 736;
        }
    }

    public void Paint(Graphics2D g2) {
        g2.drawImage(groundImage, x1, y1, null); // Tự động điều chỉnh ảnh để phủ toàn bộ cửa sổ
        g2.drawImage(groundImage, x2, y2, null);
    }
    public int getYGround(){
        return y2;
    }
        

}
