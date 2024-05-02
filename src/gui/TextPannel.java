package gui;


import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class TextPannel extends JPanel {

	private TextArea area;
	
	public TextPannel() {
		
		area= new TextArea();
		setLayout(new BorderLayout());
		add(area,BorderLayout.CENTER);
	}

	
	
	
	
}
