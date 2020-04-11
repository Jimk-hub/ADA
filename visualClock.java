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

@SuppressWarnings("unused")
public class visualClock {

    public static void main(String[] args) {
        new visualClock();
    }

    public visualClock() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Visual Clock"); //panel name
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //closes & kills program when exited out
                frame.add(new AClock());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    @SuppressWarnings("serial")
	public class AClock extends JPanel {

        public AClock() {
            this.startClock();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D gfx = (Graphics2D) g.create();

            LocalTime now = LocalTime.now();
            int seconds = now.getSecond();
            int minutes = now.getMinute();
            int hours = now.getHour();
            if(hours>=19 || hours <6) { //checks the current time and updates background on time
            	gfx.setColor(Color.BLUE); //if its past 7pm the background will be blue w black lines
                gfx.fillRect(0, 0, 400, 400); //this is to visualize night time
                gfx.setColor(Color.BLACK);
                gfx.translate(150, 150);
            }else { //otherwise the bg would be yellow to visualize day time
            gfx.setColor(Color.YELLOW);
            gfx.fillRect(0, 0, 400, 400);
            gfx.setColor(Color.BLACK);
            gfx.translate(150, 150);
            }
            //Draws the hours/mins/seconds lines
            for (int i = 0; i < 12; i++) {
            	//since we're making a clock, which rotates by a circle we use PI for its circumference
                gfx.drawLine(0, -130, 0, -150);
                gfx.rotate(Math.PI / 6);

            }

            gfx.rotate(seconds * Math.PI / 30); //rotates seconds hand
            gfx.drawLine(0, 0, 0, -145);

            gfx.rotate(2 * Math.PI - seconds * Math.PI / 30); //rotates minutes hand
            gfx.rotate(minutes * Math.PI / 30);
            gfx.setStroke(new BasicStroke(3));
            gfx.drawLine(0, 0, 0, -125);

            gfx.rotate(2 * Math.PI - minutes * Math.PI / 30); //rotates hour hands
            gfx.rotate(hours * Math.PI / 6);
            gfx.setStroke(new BasicStroke(6));
            gfx.drawLine(0, 0, 0, -100);

            gfx.dispose();
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

}