package com.yixihan.snake;

import javax.swing.*;

/**
 * @author : yixihan
 * @create : 2022-01-12-11:04
 */
public class StartGame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("贪吃蛇");

        frame.setBounds (10, 10, 900, 720);
        // 窗口大小不可变
        frame.setResizable (false);
        frame.setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);

        frame.add (new GamePanel ());

        frame.setVisible (true);
    }
}
