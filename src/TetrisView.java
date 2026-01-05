import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

import javax.swing.JPanel;

public class TetrisView extends JPanel{
	private TetrisModel model;
	private TetrisController controller;

	public TetrisView(TetrisModel model) {
		this.model = model;

		controller = new TetrisController(model, this);
		setBackground(Color.black);
		addKeyListener(controller);

		setFocusable(true);
		requestFocusInWindow(); 
	}


	public void update() {
		setFocusable(true);
		requestFocusInWindow(); 
		repaint();
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);





		model.getGameBoard().drawBoard(g);

		Font font = g.getFont();
		g.setFont(new Font("Verdana", Font.BOLD, 15));
		g.setColor(Color.white);
		g.drawString("Lines cleared: " + model.getLinesCleared(), 402, 30);
		g.drawString("Score: " + model.getPlayer().getScore(), 402, 15);
		int y = 625;
		g.drawString("Up arrow -> rotates piece", 402, y); 
		g.drawString("Down arrow -> moves piece down", 402, y + 40); 
		g.drawString("Left arrow -> moves piece left", 402, y + 80); 
		g.drawString("Right arrow -> moves piece right", 402, y + 120);
		g.drawString("Space bar -> hard drops piece", 402, y + 160);


		g.setFont(font);



		if (model.isGameOver()) {
			Font font2 = g.getFont();
			g.setFont(new Font("Verdana", Font.BOLD, 50));
			g.setColor(Color.RED);
			g.drawString("Game Over!", 37, 400);
			g.setFont(font2);
		}
	}
}





