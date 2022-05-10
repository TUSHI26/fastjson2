import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class newclass {
     JFrame frame;
     JPanel panel;
     JButton button;
     JLabel  label;
     private int count=0;
	public newclass() {
		frame =new JFrame();
		panel=new JPanel();
		button=new JButton("click");
		label=new JLabel("number of click :0");
		panel.setBorder(BorderFactory.createEmptyBorder(200,200,200,200));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		panel.add(label);
		button.addActionListener(e -> System.out.println("hi"));
		frame.add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TUshi");
		frame.pack();
		frame.setVisible(true);
	} 
	public static void main(String[] args) {
		new newclass();
	}
	
	

}
