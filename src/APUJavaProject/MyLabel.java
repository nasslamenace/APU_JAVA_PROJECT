package APUJavaProject;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyLabel extends JLabel {
	
	public MyLabel(){
		
		this.setForeground(Color.white);
		this.setFont(new Font( "ChalkBoard", Font.PLAIN, 15));
		setAlignmentX(this.CENTER_ALIGNMENT);
		
	}
	
	public MyLabel(String text) {
		super(text);
		this.setFont(new Font( "ChalkBoard", Font.PLAIN, 15));
		this.setForeground(Color.white);
		setAlignmentX(this.CENTER_ALIGNMENT);
	}

	public MyLabel(String text, int alignment) {
		
		super(text, alignment);
		this.setFont(new Font( "ChalkBoard", Font.PLAIN, 15));
		this.setForeground(Color.white);
		setAlignmentX(this.CENTER_ALIGNMENT);
	}

}
