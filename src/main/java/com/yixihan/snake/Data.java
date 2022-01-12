package com.yixihan.snake;

import javax.swing.*;
import java.net.URL;

/**
 * 数据中心
 *
 * @author : yixihan
 * @create : 2022-01-12-11:18
 */
public class Data {


    public static final URL FILE_HEAD = Data.class.getResource ("/static/header.png");


    public static final URL FILE_UP = Data.class.getResource ("/static/up.png");


    public static final URL FILE_DOWN = Data.class.getResource ("/static/down.png");


    public static final URL FILE_LEFT = Data.class.getResource ("/static/left.png");


    public static final URL FILE_RIGHT = Data.class.getResource ("/static/right.png");


    public static final URL FILE_FOOD = Data.class.getResource ("/static/food.png");


    public static final URL FILE_BODY = Data.class.getResource ("/static/body.png");

    public static ImageIcon header;

    public static ImageIcon up;

    public static ImageIcon down;

    public static ImageIcon left;

    public static ImageIcon right;

    public static ImageIcon food;

    public static ImageIcon body;

    static {


        header = new ImageIcon (FILE_HEAD);
        up = new ImageIcon (FILE_UP);
        down = new ImageIcon (FILE_DOWN);
        left = new ImageIcon (FILE_LEFT);
        right = new ImageIcon (FILE_RIGHT);
        food = new ImageIcon (FILE_FOOD);
        body = new ImageIcon (FILE_BODY);

    }

}
