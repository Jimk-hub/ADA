package tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Date;
import java.text.SimpleDateFormat;

@SuppressWarnings({ "unused", "serial" })


   	public class VisualClock extends JPanel {

        public VisualClock() {
            this.startClock();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D clockHands = (Graphics2D) g.create();

            LocalTime now = LocalTime.now();
            int seconds = now.getSecond();
            int minutes = now.getMinute();
            int hours = now.getHour();
            if(hours>=19 || hours <6) { //checks the current time and updates background on time
            	clockHands.setColor(Color.BLUE); //if its past 7pm the background will be blue w black lines
                clockHands.fillRect(0, 0, 400, 300); //this is to visualize night time
                clockHands.setColor(Color.BLACK);
                clockHands.translate(150, 150);
            }else { //otherwise the bg would be yellow to visualize day time
            clockHands.setColor(Color.YELLOW);
            clockHands.fillRect(0, 0, 400, 300);
            clockHands.setColor(Color.BLACK);
            clockHands.translate(150, 150);
            }
            //Draws the hours/mins/seconds lines
            for (int i = 0; i < 12; i++) {
            	//since we're making a clock, which rotates by a circle we use PI for its circumference
                clockHands.drawLine(0, -90, 0, -150);
                clockHands.rotate(Math.PI / 6);

            }

            clockHands.rotate(seconds * Math.PI / 30); //rotates seconds hand
            clockHands.drawLine(0, 0, 0, -145);

            clockHands.rotate(2 * Math.PI - seconds * Math.PI / 30); //rotates minutes hand
            clockHands.rotate(minutes * Math.PI / 30);
            clockHands.setStroke(new BasicStroke(3));
            clockHands.drawLine(0, 0, 0, -125);

            clockHands.rotate(2 * Math.PI - minutes * Math.PI / 30); //rotates hour hands
            clockHands.rotate(hours * Math.PI / 6);
            clockHands.setStroke(new BasicStroke(6));
            clockHands.drawLine(0, 0, 0, -100);

            clockHands.dispose();
        }

        public void startClock() {
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
        }

    }

