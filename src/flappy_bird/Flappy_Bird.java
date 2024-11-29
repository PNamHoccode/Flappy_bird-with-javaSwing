 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flappy_bird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

public class Flappy_Bird extends GameScreen {

    private BufferedImage birds;
    private Ground ground;
    private Animation bird_animation;
    private int BEGIN_SCREEN = 0;
    private int GAMEPLAY_SCREEN = 1;
    private int GAMEOVER_SCREEN = 2;

    private ChimneyGroup chimneyGroup;
    private int Point = 0;

    private int CURRENSCREEN = BEGIN_SCREEN;

    public static float Gravity = 0.15f;// con chimnos rới xuôgns

    private Bird bird;
    private BufferedImage background;

    public Flappy_Bird() {
        super(800, 600);
        try {
            birds = ImageIO.read(new File("Assets/bird_sprite.png"));
            background = ImageIO.read(new File("Assets/background.jpg"));

        } catch (Exception e) {

        }
        bird_animation = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0, 0, 60, 60);
        bird_animation.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_animation.AddFrame(f);
        f = new AFrameOnImage(120, 0, 60, 60);
        bird_animation.AddFrame(f);
        f = new AFrameOnImage(60, 0, 60, 60);
        bird_animation.AddFrame(f);

        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();

        chimneyGroup = new ChimneyGroup();
        BeginGame();
    }

    public static void main(String[] args) {
        new Flappy_Bird();
    }

    private void resetGame() {
        bird.setPos(350, 250);
        bird.setVT(0);
        bird.setIsLive(true);
        Point = 0;
        chimneyGroup.ResetChimey();
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {

        if (CURRENSCREEN == BEGIN_SCREEN) {
            resetGame();
        } else if (CURRENSCREEN == GAMEPLAY_SCREEN) {
            if (bird.getIsLive()) {
                bird_animation.Update_Me(deltaTime);
            }
            bird.update(deltaTime);
            ground.update();
            chimneyGroup.update();

            for (int i = 0; i < ChimneyGroup.SIZE; i++) {
                if (bird.getRect().intersects(chimneyGroup.getchinmey(i).getRect()))// kiểm tra 2 rect giao nhau ko
                {
                    if (bird.getIsLive()) {
                        bird.bupsound.play();
                    }
                    bird.setIsLive(false);

                }
            }
            for (int i = 0; i < ChimneyGroup.SIZE; i++) {
                if (bird.getPosX() > chimneyGroup.getchinmey(i).getPosX() && !chimneyGroup.getchinmey(i).getIsBehindBird()
                        && i % 2 == 0) {
                    Point++;
                    bird.moneysound.play();

                    chimneyGroup.getchinmey(i).setisBehindBird(true);
                }
            }

            if (bird.getPosY() + bird.getH() > ground.getYGround()) {
                CURRENSCREEN = GAMEOVER_SCREEN;//tọa độ +  độ cao hcn bao quanh nó           
            }
        } else {

        }

    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        if (background != null) {
            g2.drawImage(background, 0, 0, 800, 600, null);
        }

        // Vẽ các đối tượng khác
        chimneyGroup.Pain(g2);
        ground.Paint(g2); // Vẽ nhóm ống khói     // Vẽ mặt đất
        if (bird.getisFlying()) {
            bird_animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, -1);
        } else {
            bird_animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);
        }

       if (CURRENSCREEN == BEGIN_SCREEN) {
    drawString(g2, "Press Enter To Start Game\nPress Space To Control", 200, 300);
}
// Hiển thị thông báo nếu ở màn hình game over
if (CURRENSCREEN == GAMEOVER_SCREEN) {
    g2.setColor(Color.red);
    drawString(g2, "Game Over\nPress Space To Restart", 200, 300);
}

        g2.setColor(Color.red);
          drawStyledPoint(g2, "Point: " + Point, 20, 50);
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {

            if (CURRENSCREEN == BEGIN_SCREEN) {
                CURRENSCREEN = GAMEPLAY_SCREEN;
            } else if (CURRENSCREEN == GAMEPLAY_SCREEN) {
                if (bird.getIsLive()) {
                    bird.fly();
                }
            } else if (CURRENSCREEN == GAMEOVER_SCREEN) {
                CURRENSCREEN = BEGIN_SCREEN;
            }
        }
    }

    private void drawString(Graphics2D g2, String text, int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 30); // Chọn font và kích thước đẹp
        g2.setFont(font);

        // Tính toán chiều rộng và chiều cao để căn giữa văn bản
        int stringWidth = g2.getFontMetrics().stringWidth(text);
        int stringHeight = g2.getFontMetrics().getHeight();
        int startX = (getWidth() - stringWidth) / 2;
        int startY = y;

        // Thêm bóng đổ cho văn bản
        g2.setColor(Color.GRAY); // Màu bóng đổ
        g2.drawString(text, startX + 3, startY + 3); // Vẽ bóng đổ nhẹ

        // Vẽ văn bản chính
        g2.setColor(Color.WHITE); // Màu chữ chính
        g2.drawString(text, startX, startY);
    }
    private void drawStyledPoint(Graphics2D g2, String text, int x, int y) {
    Font font = new Font("Arial", Font.BOLD, 24); // Chọn font và kích thước đẹp cho điểm số
    g2.setFont(font);

    // Thêm bóng đổ cho văn bản điểm số
    int stringWidth = g2.getFontMetrics().stringWidth(text);
    int stringHeight = g2.getFontMetrics().getHeight();
    int startX = x;
    int startY = y;

    // Thêm bóng đổ cho điểm số
    g2.setColor(Color.GRAY); // Màu bóng đổ
    g2.drawString(text, startX + 3, startY + 3); // Vẽ bóng đổ nhẹ

    // Vẽ văn bản chính (Điểm số)
    g2.setColor(Color.YELLOW); // Màu chữ cho điểm số
    g2.drawString(text, startX, startY);
}
}
