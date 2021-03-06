/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/java8-training-course
 */
package local.js8ri.ch01.ex05;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

/**
 *
 * @author mikan
 */
@SuppressWarnings("serial")
public class UpdatedDigitalClockFrame extends Frame {

    public UpdatedDigitalClockFrame(String title) {
        super(title);
        setSize(370, 110);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Canvas canvas = new Canvas() {
            private final Font font1 = new Font(Font.SANS_SERIF, Font.PLAIN, 11);
            private final Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 50);

            @Override
            public void paint(Graphics g) {
                g.setFont(font1);
                g.drawString("現在時刻", 20, 10);
                g.setFont(font2);
                g.drawString(getTime(), 20, 60);
            }

            @Override
            public void update(Graphics g) {
                super.update(g);
                g.setFont(font1);
                g.drawString("現在時刻", 20, 10);
                g.setFont(font2);
                g.drawString(getTime(), 20, 60);
            }

            /**
             * 日付表示を取得します。
             *
             * @return 日付表示
             */
            private String getTime() {
                Calendar now = Calendar.getInstance();
                int h = now.get(Calendar.HOUR_OF_DAY);
                int m = now.get(Calendar.MINUTE);
                int s = now.get(Calendar.SECOND);
                int ms = now.get(Calendar.MILLISECOND);
                return String.format("%02d:%02d:%02d:%03d", h, m, s, ms);
            }
        };
        add(canvas, BorderLayout.CENTER);
        setVisible(true);
        new Thread(() -> {
                while (true) {
                    canvas.repaint(); // update() を呼び出す
                    try {
                        Thread.sleep(10); // 10ms 間隔で更新
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }
}
