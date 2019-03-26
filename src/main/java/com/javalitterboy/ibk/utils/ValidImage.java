package com.javalitterboy.ibk.utils;

import org.springframework.stereotype.Component;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 14183
 */
@Component
public class ValidImage {

    private final int WIDTH = 240;
    private final int HEIGHT = 60;

    private String text="";

    public BufferedImage getValidImage(){
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //设置背景颜色
        setBackGround(g);
        //设置边框
        setBorder(g);
        //画干扰线
        drawRandomLine(g);
        //写随机数
        drawRandomNum(g);
        return image;
    }

    public String getText(){
        return this.text;
    }

    private void drawRandomLine(Graphics g) {
        g.setColor(Color.YELLOW);
    }

    private void setBorder(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(1, 1, 23, 23);
    }

    private void setBackGround(Graphics g) {
        g.setColor(Color.GREEN);
        int lineNUM = 5;
        for(int i = 0; i< lineNUM; i++){
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    private void drawRandomNum(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 60));
        String name = "\u7684\u4e00\u662f\u4e86\u6211\u4e0d\u4eba\u5728\u4ed6\u6709\u8fd9\u4e2a\u4e0a\u4eec\u6765\u5230\u65f6\u5927\u5730\u4e3a\u5b50\u4e2d\u4f60\u8bf4\u751f\u56fd\u5e74\u7740\u5c31\u90a3\u548c\u8981\u5979\u51fa\u4e5f\u5f97\u91cc\u540e\u81ea\u4ee5\u4f1a\u5bb6\u53ef\u4e0b\u800c\u8fc7\u5929\u53bb\u80fd\u5bf9\u5c0f\u591a\u7136\u4e8e\u5fc3\u5b66\u4e48\u4e4b\u90fd\u597d";
        int x = 1;
        int textNum = 4;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< textNum; i++){
            String ch = name.charAt(new Random().nextInt(name.length()))+"";
            sb.append(ch);
            g.drawString(ch, x, 50);
            x+=60;
        }
        this.text = sb.toString();
    }

}
