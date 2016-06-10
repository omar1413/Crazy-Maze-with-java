package states;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import network.Client;

public class Handeler implements MouseListener {

	MenuState menu;

	public Handeler(MenuState menu) {
		this.menu = menu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= menu.getxB() && e.getX() <= menu.getxB() + menu.getWidthB()) {
			if (e.getY() >= menu.getyB() && e.getY() <= menu.getyB() + menu.getHeightB()) {
				menu.display.getFrame().dispose();
				new SingleGame(10, "level 0");
			}
			if (e.getY() >= menu.getyB() + menu.getyOffest()
					&& e.getY() <= menu.getyB() + menu.getyOffest() + menu.getHeightB()) {
				menu.display.getFrame().dispose();
				int n = JOptionPane.showConfirmDialog(null, "do you want to run the server on this pc");
				if (n == 0){
				Level l=new Level(300, 500, "CRAZYMAZE");
				}
				else
					new Client(8888);
			}

			if (e.getY() >= menu.getyB() + 2 * menu.getyOffest()
					&& e.getY() <= menu.getyB() + 2 * menu.getyOffest() + menu.getHeightB()) {
				System.exit(0);
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
