/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappy_bird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;

/**
 *
 * @author ADMIN
 */
public class ChimneyGroup {

    private QueueList<Chimney> chinmeys;
    private BufferedImage chimmeyimage, chimmeyimage2;
    public static int SIZE = 6;
    private int topChinmeyY = -350;
    private int buttomChinmey = 190;

    public int GetRandomY() {
        Random random = new Random();
        int a = random.nextInt(10);
        return a*40;
    }

    public Chimney getchinmey(int i) {
        return chinmeys.get(i);
    }

    public ChimneyGroup() {

        try {
            chimmeyimage = ImageIO.read(new File("Assets/chimney.png"));

            chimmeyimage2 = ImageIO.read(new File("Assets/chimney_.png"));
        } catch (Exception e) {
        }

        chinmeys = new QueueList<>();
        Chimney cn;

        for (int i = 0; i < SIZE / 2; i++) {

            int deltaY = GetRandomY();
            cn = new Chimney(830 + i * 300,buttomChinmey + deltaY, 74, 400);
            chinmeys.push(cn);

            cn = new Chimney(830 + i * 300, topChinmeyY + deltaY, 74, 400);
            chinmeys.push(cn);
        }
    }

    public void ResetChimey() {
        chinmeys = new QueueList<>();
        Chimney cn;

       for (int i = 0; i < SIZE / 2; i++) {

            int deltaY = GetRandomY();
            cn = new Chimney(830 + i * 300,buttomChinmey + deltaY, 74, 400);
            chinmeys.push(cn);

            cn = new Chimney(830 + i * 300, topChinmeyY + deltaY, 74, 400);
            chinmeys.push(cn);
        }
    }

    public void update() {
        for (int i = 0; i < SIZE; i++) {
            chinmeys.get(i).update();
        }

        if (chinmeys.get(0).getPosX() < -74) {
            int deltaY = GetRandomY();
            Chimney cn;
            cn = chinmeys.pop();
            cn.setPosX(chinmeys.get(4).getPosX() + 300);
            cn.setPosY(buttomChinmey+ deltaY);
            cn.setisBehindBird(false);
            chinmeys.push(cn);

            cn = chinmeys.pop();
            cn.setPosX(chinmeys.get(4).getPosX());
            cn.setPosY(topChinmeyY + deltaY);
            cn.setisBehindBird(false);

            chinmeys.push(cn);
        }

    }

    public void Pain(Graphics2D g2) {
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                g2.drawImage(chimmeyimage, (int) chinmeys.get(i).getPosX(), (int) chinmeys.get(i).getPosY(), null);
            } else {
                g2.drawImage(chimmeyimage2, (int) chinmeys.get(i).getPosX(), (int) chinmeys.get(i).getPosY(), null);
            }

        }
    }
}
