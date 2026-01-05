import java.awt.Color;

import javax.swing.JFrame;

public class TetrisRunner {
	public static void main(String[] args) {
		JFrame window = new JFrame("Tetris");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setSize(700, 800 + 29);

		window.setLocation(500, 100);
		window.setVisible(true);
		window.setResizable(false);



		TetrisModel model = new TetrisModel();
		TetrisView view = new TetrisView(model);

		window.setContentPane(view);

		window.setVisible(true);


	}
}
