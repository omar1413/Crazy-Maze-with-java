package display;

import java.awt.*;

import javax.swing.*;

public class Display {

	private JFrame frame;
	private Canvas canvas;

	private int width, height;
	private String title;
	


	

	public Display(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		init();

	}

	public void init() {
		frame = new JFrame(title);
		canvas = new Canvas();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.add(canvas);
		frame.setVisible(true);
		canvas.requestFocus();
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
