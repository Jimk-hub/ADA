package application;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException; 
 
 
public class Clock extends JFrame implements Runnable, WindowListener{ 
 
	Thread timer=null; 
 
	String dateToDisplay; 
 
	int hr; 
	Date d; 
	JLabel dateLabel=new JLabel();	 
	int hour; 
	 int minute; 
	 int second; 
	 String amPm="AM"; 
 
	public static void main(String[] args) { 
		Clock clock=new Clock(); 
		 clock.setSize(500, 200); 
		clock.setVisible(true); 
		 try { 
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
 
		 } 
		 catch (ClassNotFoundException e) { 
 
		    } catch (InstantiationException e) { 
 
		    } catch (IllegalAccessException e) { 
 
		    } catch (UnsupportedLookAndFeelException e) { 
 
		    } 
		clock.setResizable(false); 
		 clock.start(); 
 
 
	} 
 
	private void start() { 
		 if(timer == null) 
	    { 
	      timer = new Thread(this); 
	      timer.start(); 
	    } 
	} 
 
	public void stop() 
	{ 
	  timer = null; 
	} 
 
	Clock() 
	{ 
 
		this.setLayout(new FlowLayout()); 
 
		dateLabel.setBackground(Color.WHITE); 
		dateLabel.setForeground(Color.BLUE); 
		dateLabel.setFont(new Font("ComicSans",Font.BOLD,24)); 
 
		 this.add(dateLabel); 
		 this.setTitle("Clock "); 
		this.pack(); 
	        	this.setLocationRelativeTo(null); 
 
	} 
	public String getFormatedDate(Date d) 
	{ 
	String formatedDate=" "; 
	 hour = d.getHours(); 
	 minute = d.getMinutes(); 
	 second = d.getSeconds();  
 
	formatedDate=formatedDate.concat(padElement(hour, '0')); 
	formatedDate=formatedDate.concat(":"); 
	formatedDate=formatedDate.concat(padElement(minute, '0')); 
	formatedDate=formatedDate.concat(":"); 
	formatedDate=formatedDate.concat(padElement(second, '0')); 
	return formatedDate;  
	} 
 
	private String padElement(int expr, char padChar) 
	{ 
	  String result = ""; 
	  // I'm just padding 2 digit numbers 
	  if (expr < 10) result = result.concat(String.valueOf(padChar)); 
	  result = result.concat(String.valueOf(expr)); 
	  return(result); 
	} 
 
 
	public void run() { 
		 // Sleep in the timer thread... 
		  while (timer != null) { 
		    try {timer.sleep(10);} 
		catch (InterruptedException e){} 
		d=new  Date(); 
		dateToDisplay=getFormatedDate(d); 
		 dateLabel.setText(dateToDisplay); 
		} 
		timer = null; 
 
	} 
 
 
	public void windowActivated(WindowEvent arg0) { 
		// TODO Auto-generated method stub 
 
	} 
 
	 public void windowClosed(WindowEvent arg0) { 
	 } 
	public void windowClosing(WindowEvent arg0) { 
		stop(); 
		dispose(); 
		System.exit(0); 
 
	} 
	public void windowDeactivated(WindowEvent arg0) { 
	} 
	public void windowDeiconified(WindowEvent arg0) { 
	} 
	public void windowIconified(WindowEvent arg0) { 
	} 
	public void windowOpened(WindowEvent arg0) { 
	} 
 
}