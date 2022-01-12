package com.yixihan.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 游戏的面板
 * @author : yixihan
 * @create : 2022-01-12-11:06
 */
public class GamePanel extends JPanel {

    /**
     * 蛇的长度
     */
    private int length;

    /**
     * 蛇的 x 坐标
     */
    private int[] snakeX;

    /**
     * 蛇的 y 坐标
     */
    private int[] snakeY;

    /**
     * 小蛇的方向
     */
    private String direction;

    /**
     * 游戏当前的状态
     */
    private boolean isStart;

    /**
     * 定时器
     */
    private Timer timer;

    /**
     * 食物的 x 坐标
     */
    private int foodX;

    /**
     * 食物的 y 坐标
     */
    private int foodY;

    /**
     * 游戏失败标志
     */
    private boolean isFail;

    /**
     * 游戏积分
     */
    private int score;

    private static final Random random = new Random();


    public GamePanel() {
        init ();

        // 获得焦点事件
        setFocusable (true);

        // 获得键盘事件
        addKeyListener (new CustomerKeyListener ());

    }

    public void init () {

        // 定时器 100ms 刷新一次
        timer = new Timer(100, new CustomerActionLister ());

        // 启动定时器
        timer.start ();

        // 初始设置游戏状态为 false
        setStart (false);

        // 初始设置游戏失败为 false
        setFail (false);

        // 初始设置小蛇方向为 R
        setDirection ("R");

        // 初始设置积分为 0
        setScore (0);

        // 初始化 snakeX, snackY
        snakeX = new int[600];
        snakeY = new int[600];

        // 初始设置小蛇长度为 3
        setLength (3);

        // 脑袋的坐标
        snakeX[0] = 100; snakeY[0] = 100;

        // 第一个身体的坐标
        snakeX[1] = 75; snakeY[1] = 100;

        // 第二个身体的坐标
        snakeX[2] = 50; snakeY[2] = 100;

        // 初始化食物
        createFood ();
    }

    private void createFood () {

        // 随机生成食物
        setFoodX (25 + 25 * random.nextInt (34));
        setFoodY (75 + 25 * random.nextInt (24));
    }

    private void judgeFail () {
        for (int i = 1; i < length; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                isFail = true;
                break;
            }
        }
    }



    /**
     * 绘制面板
     * 游戏中的所有东西, 都使用这个画笔来画
     */
    @Override
    protected void paintComponent(Graphics g) {
        // 清屏
        super.paintComponent (g);

        // 绘制静态面板
        // 头部广告栏
        Data.header.paintIcon (this, g, 25, 11);

        // 默认的游戏区域
        g.fillRect (25, 75, 850, 600);

        g.setColor (Color.white);
        g.setFont (new Font ("微软雅黑", Font.BOLD, 16));
        g.drawString ("长度 : " + length, 750, 35);
        g.drawString ("积分 : " + getScore (), 750, 50);

        // 画食物
        Data.food.paintIcon (this, g, foodX, foodY);

        // 画小蛇
        if ("R".equals (direction)) {
            Data.right.paintIcon (this, g, snakeX[0], snakeY[0]);
        } else if ("L".equals (direction)) {
            Data.left.paintIcon (this, g, snakeX[0], snakeY[0]);
        } else if ("U".equals (direction)) {
            Data.up.paintIcon (this, g, snakeX[0], snakeY[0]);
        } else if ("D".equals (direction)) {
            Data.down.paintIcon (this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon (this, g, snakeX[i], snakeY[i]);
        }


        // 游戏状态
        if (! isStart) {

            // 设置画笔颜色
            g.setColor (Color.white);

            // 设置字体
            g.setFont (new Font ("微软雅黑", Font.BOLD, 40));

            // 绘制字
            g.drawString ("按下空格开始游戏", 300, 300);
        }

        if (isFail) {
            // 设置画笔颜色
            g.setColor (Color.red);

            // 设置字体
            g.setFont (new Font ("微软雅黑", Font.BOLD, 40));

            // 绘制字
            g.drawString ("游戏失败, 按下空格重新开始", 300, 300);
        }

        // 设置背景
        setBackground (Color.white);

    }



    public void setLength(int length) {
        this.length = length;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setFoodX(int foodX) {
        this.foodX = foodX;
    }

    public void setFoodY(int foodY) {
        this.foodY = foodY;
    }

    public void setFail(boolean fail) {
        isFail = fail;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 键盘监听器
     */
    private class CustomerKeyListener implements KeyListener {


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode ();
            // 如果按下空格键, 暂停/开始游戏
            if (keyCode == KeyEvent.VK_SPACE) {

                if (isFail) {
                    // 游戏重新开始
                    init ();
                } else {
                    isStart = !isStart;
                }
                repaint ();
            }

            // 小蛇移动
            if (keyCode == KeyEvent.VK_LEFT) {
                direction = "L";
            } else if (keyCode == KeyEvent.VK_UP) {
                direction = "U";
            } else if (keyCode == KeyEvent.VK_DOWN) {
                direction = "D";
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                direction = "R";
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    /**
     * 事件监听, 需要通过固定的时间刷新, 1s = 10次
     */
    private class CustomerActionLister implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (isStart && !isFail) {

                // 吃食物
                if (snakeY[0] == foodY && snakeX[0] == foodX) {
                    // 吃食物
                    length++;

                    // 生成食物
                    createFood ();

                    // 积分增长
                    setScore (getScore () + 10);
                }

                // 身体移动
                for (int i = length - 1; i > 0; i--) {
                    snakeX[i] = snakeX[i - 1];
                    snakeY[i] = snakeY[i - 1];
                }

                // 头走向
                if ("R".equals (direction)) {
                    snakeX[0] += 25;
                    // 边界判断
                    if (snakeX[0] > 850) {
                        snakeX[0] = 25;
                    }
                } else if ("L".equals (direction)) {
                    snakeX[0] -= 25;
                    // 边界判断
                    if (snakeX[0] < 25) {
                        snakeX[0] = 850;
                    }
                } else if ("U".equals (direction)) {
                    snakeY[0] -= 25;
                    // 边界判断
                    if (snakeY[0] < 75) {
                        snakeY[0] = 650;
                    }

                } else if ("D".equals (direction)) {
                    snakeY[0] += 25;
                    // 边界判断
                    if (snakeY[0] > 650) {
                        snakeY[0] = 75;
                    }
                }



                repaint ();
            }

            // 开始计数器
            timer.start ();

            // 判断失败
            judgeFail ();
        }
    }
}


