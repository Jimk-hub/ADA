package tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

class AlarmThread2 implements Runnable //comments added
{ 
	private boolean exit = false;
	String aSound = "src\\tree\\alarm.wav";
	Clip clip;
	public void setFile(String soundName)
	{
		try
		{
			File file = new File(soundName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch (Exception e)
		{
			
		}
	}
	
	public void play()
	{
		clip.setFramePosition(0);
		clip.start();
	}
	

	
    public void run() 
    { 
    	setFile(aSound);
        try
        { 
        	while (exit == false)
        	{   
	        		LocalTime here = LocalTime.now();
	        		if (here.getMinute() == Balt.amin2 && here.getHour() == Balt.ahr2)  //0-23 hours
	        		{
	        			JFrame f=new JFrame("Reminder Alarm");  
	        			JTextArea tf =new JTextArea(10,20);  
	        		    tf.setPreferredSize(new Dimension(450,150));
	        			tf.setText("Take Ventolin Enhaler"
	                    		+"\n"+"Enhale twice for 30 seconds");  
	                    tf.setBounds(50,50, 150,20);  
	                    JButton b=new JButton("DONE!");  
	                    b.setBounds(50,100,95,30); 
	                    BufferedImage img = null;
	                    String filepath = "src\\tree\\ventolin.jpg";
	            		play();
	                    try
	            		{
	            			img = ImageIO.read(new File(filepath));
	            		}
	            		catch (Exception e)
	            		{
	            			e.printStackTrace();
	            		}
	            		
	            		JLabel lbl = new JLabel();
	            		lbl.setIcon(new ImageIcon(img));
	            		f.getContentPane().add(lbl,BorderLayout.SOUTH);
	            		
	            		Timer timer = new Timer(10000, new ActionListener() {
					        @Override
					        public void actionPerformed(ActionEvent e) {
					          f.dispose();
					          try {
									FileWriter fw=new FileWriter(new File("C:\\Users\\USER\\Desktop\\A6", "log.txt"),true);
									fw.write("Event 2 NOT ATTEMPTED: \n"+LocalTime.now()); 
									  
							        System.out.println("Writing successful"); 
							        //close the file  
							        fw.close(); 
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					        }
					      });
					      timer.start();
	            		
	                    b.addActionListener(new ActionListener(){  
	                public void actionPerformed(ActionEvent e){  
	                	try {
							FileWriter fw=new FileWriter(new File("C:\\Users\\USER\\Desktop\\A6", "log.txt"),true);
							fw.write("Event 2 Completed at:"+LocalTime.now()+"\n"); 
							  
					        System.out.println("Writing successful"); 
					        //close the file  
					        fw.close(); 
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                            tf.setText("Your Response Has been Received!");  
	                        }  
	                    });  
	                    f.add(b);f.add(tf); 
	                    
	                    f.setSize(400,400);  
	                    //f.setLayout(null);  
	                    f.setVisible(true); 
	                    
	                 // for stopping the thread 
	                    
	                    
	                        exit = true; 
	                   
	
	                    
	                    break;
	        		
        		}
        	
        	}

            
        
  
        } 
        catch (Exception e) 
        { 

            System.out.println (e.getClass().getCanonicalName()); 
        } 
    } 
} 

