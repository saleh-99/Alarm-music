package raspry_alarm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class tt {

	JFrame frame;
	 clock1 c ;
	 TestPane t;
	private JTextField hourField;
	private JTextField minuteField;

	
	public tt() {
		c =new clock1();
		t = new TestPane();
		initialize();
		
	}

	
	void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBackground(Color.black);
		c.setBounds(-191, -73, 504, 316);
		frame.getContentPane().add(c);
		t.setBounds(10, 302, 380, 200);
		frame.getContentPane().add(t);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Hours:"));
		hourField = new JTextField("12", 3);
		panel.add(hourField);

		panel.add(new JLabel("Minutes:"));
		minuteField = new JTextField("00", 3);
		panel.add(minuteField);
		panel.setBounds(6, 255, 380, 46);
		frame.getContentPane().add(panel);
		
		frame.setVisible(true);
		
		
	}
	
	

	public static class clock1 extends JPanel{

		
		
		private static final long serialVersionUID = 1L;
		int hour;
		 int minute;
		 int second;
		public void settime(int h,int m,int s) {
			  hour = h; 
		      minute = m; 
		      second = s; 
		}
		 @Override
	        protected void paintComponent(Graphics g) 

	    { 
			super.paintComponent(g);
	        // 12 hour format 
	        if (hour > 12) { 
	            hour -= 12; 
	        } 
	  
	        // Draw clock body center at (400, 200) 
	        g.setColor(Color.white); 
	        g.fillOval(300, 100, 200, 200); 
	  
	        // Labeling 
	        g.setColor(Color.black); 
	        g.drawString("12", 390, 120); 
	        g.drawString("9", 310, 200); 
	        g.drawString("6", 400, 290); 
	        g.drawString("3", 480, 200); 
	  
	        // Declaring variables to be used 
	        double angle; 
	        int x, y; 
	  
	        // Second hand's angle in Radian 
	        angle = Math.toRadians((15 - second) * 6); 
	  
	        // Position of the second hand 
	        // with length 100 unit 
	        x = (int)(Math.cos(angle) * 100); 
	        y = (int)(Math.sin(angle) * 100); 
	  
	        // Red color second hand 
	        g.setColor(Color.red); 
	        g.drawLine(400, 200, 400 + x, 200 - y); 
	  
	        // Minute hand's angle in Radian 
	        angle = Math.toRadians((15 - minute) * 6); 
	  
	        // Position of the minute hand 
	        // with length 80 unit 
	        x = (int)(Math.cos(angle) * 80); 
	        y = (int)(Math.sin(angle) * 80); 
	  
	        // blue color Minute hand 
	        g.setColor(Color.blue); 
	        g.drawLine(400, 200, 400 + x, 200 - y); 
	  
	        // Hour hand's angle in Radian 
	        angle = Math.toRadians((15 - (hour * 5)) * 6); 
	  
	        // Position of the hour hand 
	        // with length 50 unit 
	        x = (int)(Math.cos(angle) * 50); 
	        y = (int)(Math.sin(angle) * 50); 
	  
	        // Black color hour hand 
	        g.setColor(Color.black); 
	        g.drawLine(400, 200, 400 + x, 200 - y); 
	        
	    }
	}
	
	
	public class TestPane extends JPanel {

        
		private static final long serialVersionUID = 1L;
		private JPanel mainList;

        public TestPane() {
        	
            setLayout(new BorderLayout());

            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);

            add(new JScrollPane(mainList));

            JButton add = new JButton("Add");
            add.addActionListener(new ActionListener() {
                private String time;

				@Override
                public void actionPerformed(ActionEvent e) {
					
					int hours = Integer.parseInt(hourField.getText().trim());
    				int minutes = Integer.parseInt(minuteField.getText().trim());
    				Clock.alarm.setAlarm(true, hours, minutes);
    				hourField.setText("");
    				minuteField.setText("");
    				time=hours+" : "+minutes;
					
                    JPanel panel = new JPanel(new GridLayout(1, 2,20,0));
                   
                    panel.setBorder(new TitledBorder(new EmptyBorder(10, 0, 0, 0), "NUMBER :"+Clock.alarm.geti(hours,minutes)));
                    JLabel p = new JLabel(time);
                    p.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                    p.setBorder(new EmptyBorder(0, 15, 0, 0));
                    panel.add(p);
                    JButton b = new JButton("ON");
                    b.setBorderPainted(false);
                    b.setOpaque(true);
                   
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	if(b.getText()=="ON") {
                        	Clock.alarm.turnOffAlarm(Clock.alarm.geti(hours,minutes));
                        	b.setText("OFF");
                        	b.setForeground(Color.RED);
                        	}
                        	else if(b.getText()=="OFF") {
                            	Clock.alarm.turnOnAlarm(Clock.alarm.geti(hours,minutes));
                            	b.setText("ON");
                            	b.setForeground(Color.black);
                        	}
                        	
                            
                        }

						
                   });
                    panel.add(b);
                    
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.weightx = 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    mainList.add(panel, gbc, 0);

                    validate();
                    repaint();
                }
            });

            add(add, BorderLayout.SOUTH);

        }

        
    }

}
