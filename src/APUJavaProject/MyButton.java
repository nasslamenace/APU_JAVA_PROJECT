package APUJavaProject;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MyButton extends JButton {
	
	public MyButton(String text) {
		
		super(text);
		
		setAlignmentX(this.CENTER_ALIGNMENT);
		  
		  this.setOpaque(true);
		  this.setBorderPainted(false);
		  this.setBackground(Color.white);
		  this.setForeground(new Color(61, 97, 166));
		  
		  
		  this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(Color.darkGray);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.darkGray);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Color.white);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.gray);
			    setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				  setBackground(Color.white);
				  setForeground(new Color(72, 122, 176));
			}
			  
			  
			  
		  });
		  
		  
		
	}
	
public MyButton(ImageIcon icon) {
		
		super(icon);
		
		setAlignmentX(this.CENTER_ALIGNMENT);
		  
		  this.setOpaque(true);
		  this.setBorderPainted(true);
		  this.setBackground(Color.white);
		  this.setForeground(new Color(61, 97, 166));
		  
		  
		  this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground(Color.darkGray);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.darkGray);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Color.white);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.gray);
			    setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				  setBackground(Color.white);
				  setForeground(new Color(72, 122, 176));
			}
			  
			  
			  
		  });
		  
		  
		
	}

}
