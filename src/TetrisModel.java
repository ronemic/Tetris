import java.awt.event.KeyEvent;

public class TetrisModel {
	private GameBoard gameBoard = new GameBoard();
	private Player player = new Player();
	private boolean rightArrowDown = false;
	private boolean leftArrowDown = false;
	private boolean upArrowDown = false;
	private boolean downArrowDown = false;
	private boolean spaceBarDown = false;
	private boolean gameOver = false;



	private int linesCleared = 0;





	public TetrisModel() {

		gameBoard.addNewBlock((int)(Math.random() * 7) + 1, 5, 0);







	}

	public void update() {

		if (gameBoard.update()) {
			gameBoard.addNewBlock((int)(Math.random() * 7) + 1, 5, 0);

		}






		player.setScore(gameBoard.getPlayerScore());
		linesCleared = gameBoard.getLinesCleared();


		if (gameBoard.isGameOver()) {
			gameOver = true;
		}
		if (upArrowDown) {
			this.getGameBoard().rotatePiece();
			upArrowDown = false;
		}
		if (downArrowDown) {
			this.getGameBoard().movePieceDown();
			downArrowDown = false;
		}
		if (rightArrowDown) {
			this.getGameBoard().movePieceRight();
			rightArrowDown = false;
		}
		if (leftArrowDown) {
			this.getGameBoard().movePieceLeft();
			leftArrowDown = false;
		}
		if (spaceBarDown) {
			this.getGameBoard().hardDropPiece();
			spaceBarDown = false;

		}

	}


	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public boolean isRightArrowDown() {
		return rightArrowDown;
	}

	public void setRightArrowDown(boolean rightArrowDown) {
		this.rightArrowDown = rightArrowDown;
	}

	public boolean isLeftArrowDown() {
		return leftArrowDown;
	}

	public void setLeftArrowDown(boolean leftArrowDown) {
		this.leftArrowDown = leftArrowDown;
	}

	public boolean isUpArrowDown() {
		return upArrowDown;
	}

	public void setUpArrowDown(boolean upArrowDown) {
		this.upArrowDown = upArrowDown;
	}

	public boolean isDownArrowDown() {
		return downArrowDown;
	}

	public void setDownArrowDown(boolean downArrowDown) {
		this.downArrowDown = downArrowDown;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getLinesCleared() {
		return linesCleared;
	}

	public void setLinesCleared(int linesCleared) {
		this.linesCleared = linesCleared;
	}

	public boolean isSpaceBarDown() {
		return spaceBarDown;
	}

	public void setSpaceBarDown(boolean spaceBarDown) {
		this.spaceBarDown = spaceBarDown;
	}









}
