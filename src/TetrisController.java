import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;


public class TetrisController implements KeyListener, ActionListener, MouseListener {
	private TetrisModel model;
	private TetrisView view;

	private Timer timer = new Timer(150, this);


	public TetrisController(TetrisModel model, TetrisView view) {

		this.model = model;
		this.view = view;


		timer.start();

		view.requestFocusInWindow();


	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:			

			model.setUpArrowDown(true);
			break;
		case KeyEvent.VK_DOWN:


			model.setDownArrowDown(true);

			break;
		case KeyEvent.VK_RIGHT:


			model.setRightArrowDown(true);

			break;
		case KeyEvent.VK_LEFT:

			model.setLeftArrowDown(true);

			break;

		case KeyEvent.VK_SPACE:

			model.setSpaceBarDown(true);
			break;
		}




	}


	@Override
	public void keyReleased(KeyEvent e) {

		//		switch (e.getKeyCode()) {
		//		case KeyEvent.VK_UP:
		//			model.setUpArrowDown(false);
		//			break;
		//		case KeyEvent.VK_DOWN:
		//			model.setDownArrowDown(false);
		//			break;
		//		case KeyEvent.VK_RIGHT:
		//			model.setRightArrowDown(false);
		//			break;
		//		case KeyEvent.VK_LEFT:
		//			model.setLeftArrowDown(false);
		//			break;
		//		case KeyEvent.VK_SPACE:
		//
		//			model.setSpaceBarDown(false);
		//			break;
		//		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {


		model.update();
		view.update();
	}
	@Override
	public void mouseClicked(MouseEvent e) {


	}
	@Override
	public void mousePressed(MouseEvent e) {





	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
