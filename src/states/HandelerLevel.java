package states;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class HandelerLevel implements MouseListener{

	private Level level;
	
	
	public HandelerLevel(Level level) {
		this.level = level;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= level.getxB() && e.getX() <= level.getxB() + level.getWidthB()){
			if(e.getY() >= level.getyB() && e.getY() <= level.getyB() + level.getHeightB()){
				level.display.getFrame().dispose();
				new MultiPlayer(10);
			}
			if(e.getY() >= level.getyB() + level.getyOffest() && e.getY() <= level.getyB() + level.getyOffest() + level.getHeightB()){
				level.display.getFrame().dispose();
				new MultiPlayer(20);
			}
			
			if(e.getY() >= level.getyB() + 2* level.getyOffest() && e.getY() <= level.getyB() + 2 * level.getyOffest() + level.getHeightB()){
				level.display.getFrame().dispose();
				new MultiPlayer(30);
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
