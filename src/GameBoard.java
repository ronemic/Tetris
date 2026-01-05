import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class GameBoard {
	private int[][] grid = new int[20][10];
	private ArrayList<Tetromino> blocks = new ArrayList<Tetromino>();
	private Tetromino currentBlock;
	private int delay = 3;
	private int currentDelay = 0;
	private boolean delaying = false;
	private int playerScore; 
	private boolean isGameOver = false;
	private int linesCleared = 0;





	public void drawBoard(Graphics g) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				g.setColor(Color.black);
				g.fillRect(40 * j, 40 * i, 40, 40);
				g.setColor(Color.darkGray);
				g.drawRect(40 * j, 40 * i, 40, 40);

			}
		}

		drawGhostPiece(g);



		for (int i = 0; i < blocks.size(); i++) {
			Tetromino block = blocks.get(i);
			for (int j = 0; j < block.getPoints().length; j++) {
				g.setColor(block.getColor());
				g.fillRect((int) (40 * block.getPoints()[j].getX()), (int) (40 * block.getPoints()[j].getY()), 40, 40);

				g.setColor(Color.black);
				g.drawRect((int) (40 * block.getPoints()[j].getX()), (int) (40 * block.getPoints()[j].getY()), 40, 40);
			}
		}

	}

	public void addNewBlock(int type, int x, int y) {
		if (!isGameOver) {
			Tetromino newBlock = new Tetromino(type, x, y);

			for (int i = 0; i < newBlock.getPoints().length; i++) {
				double xPos = newBlock.getPoints()[i].getX();
				double yPos = newBlock.getPoints()[i].getY();
				if (grid[(int) (yPos)][(int) (xPos)] != 0) {
					isGameOver = true;
				}
			}

			blocks.add(newBlock);

		}

	}

	public boolean update() {		
		currentBlock = blocks.get(blocks.size() - 1);

		updateBoard();


		for (int i = 0; i < currentBlock.getPoints().length; i++) {
			double x = currentBlock.getPoints()[i].getX();
			double y = currentBlock.getPoints()[i].getY();
			grid[(int) (y)][(int) (x)] = 0;

		} 

		if (canMoveDown()) {
			currentBlock.update();
			delaying = false;
			currentDelay = 0;
			return false;


		} else {
			if (!delaying) {
				delaying = true;
				currentDelay = 0;
			}
			currentDelay++;




			if (currentDelay >= delay) {
				delaying = false;
				currentDelay = 0;
				clearLines();
				return true;
			} else {
				return false;
			}
		}

	}


	public void movePieceLeft() {

		if (canMoveLeft() && !isGameOver) {
			currentBlock = blocks.get(blocks.size() - 1);
			currentBlock.moveLeft();
		}


	}

	public void movePieceRight() {

		if (canMoveRight() && !isGameOver) {
			currentBlock = blocks.get(blocks.size() - 1);
			currentBlock.moveRight();
		}

	}

	public void movePieceDown() {
		if (canMoveDown()) {
			currentBlock = blocks.get(blocks.size() - 1);
			currentBlock.moveDown();
			playerScore += 1;
		}



	}

	public void clearLines() {
		ArrayList<Integer> completedLines = new ArrayList<>();
		updateBoard();


		for (int r = 0; r < grid.length; r++) {
			boolean completed = true;
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == 0) {
					completed = false;
				}
			}
			if (completed) {
				completedLines.add(r);
			}


		}

		if (completedLines.size() == 0) {
			return;
		}
		if (completedLines.size() == 1) {
			playerScore += 40;
		} else if (completedLines.size() == 2) {
			playerScore += 100; 
		} else if (completedLines.size() == 3) {
			playerScore += 300;
		} else if (completedLines.size() == 4) { 
			playerScore += 1200;
		}

		linesCleared += completedLines.size();

		for (int i = 0; i < completedLines.size(); i++) {
			int line = completedLines.get(i);

			for (int j = blocks.size() - 1; j >= 0; j--) {
				Tetromino block = blocks.get(j);
				boolean onLine = false;

				for (int k = 0; k < block.getPoints().length; k++) {
					double y = block.getPoints()[k].getY();
					if ((int) (y) == line) {
						onLine = true;

					}
				}
				if (onLine) {
					ArrayList<Point> leftoverPoints = new ArrayList<>();

					for (int k = 0; k < block.getPoints().length; k++) {
						double y = block.getPoints()[k].getY();
						if ((int) (y) != line) {
							leftoverPoints.add(new Point((int) (block.getPoints()[k].getX()), (int) (block.getPoints()[k].getY())));
						}
					}

					blocks.remove(j);

					if (leftoverPoints.size() != 0) {
						Point[] points = new Point[leftoverPoints.size()];
						for (int k = 0; k < leftoverPoints.size(); k++) {
							points[k] = leftoverPoints.get(k);
						}
						Tetromino temp = new Tetromino(0, 0, 0);
						temp.setPoints(points);
						temp.setColor(block.getColor());
						blocks.add(temp);

					}



				}
			}

			for (int j = 0; j < blocks.size(); j++) {
				Tetromino block = blocks.get(j);
				boolean moveDown = false;

				for (int k = 0; k < block.getPoints().length; k++) {
					double y = block.getPoints()[k].getY();

					if ((int) (y) < line) {
						moveDown = true;

					}
				}

				if (moveDown) {
					System.out.println(moveDown);

					block.moveDown();



				}
			}

		}

		updateBoard();


	}


	public void updateBoard() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				grid[r][c] = 0;
			}
		}

		for (int j = 0; j < blocks.size(); j++) {
			Tetromino block = blocks.get(j);
			for (int i = 0; i < block.getPoints().length; i++) {
				double x = block.getPoints()[i].getX();
				double y = block.getPoints()[i].getY();			
				if ((int) (x) >= 0 && (int) (x) < 10 && (int) (y) >= 0 && (int) (y) < 20) {
					grid[(int) (y)][(int) (x)] = 1;
				}
			}
		}
	}






	public boolean canMoveDown() {


		currentBlock = blocks.get(blocks.size() - 1);


		boolean canMoveDown = true;
		for (int i = 0; i < currentBlock.getPoints().length; i++) {
			double x = currentBlock.getPoints()[i].getX();
			double y = currentBlock.getPoints()[i].getY();

			if ((int) (y) >= 19) {
				canMoveDown = false;

			} else if ((int) (y) < 19 && grid[(int) (y) + 1][(int) (x)] != 0) {
				canMoveDown = false;

			}
		}
		return canMoveDown;
	}

	public boolean canMoveRight() {

		currentBlock = blocks.get(blocks.size() - 1);
		boolean canMoveRight = true;
		for (int i = 0; i < currentBlock.getPoints().length; i++) {
			double x = currentBlock.getPoints()[i].getX();
			double y = currentBlock.getPoints()[i].getY();


			if ((int) (x) >= 9) {
				canMoveRight = false;
			} else if (grid[(int) (y)][(int) (x) + 1] != 0) {
				canMoveRight = false;
			}
		}
		return canMoveRight;
	}


	public boolean canMoveLeft() {
		currentBlock = blocks.get(blocks.size() - 1);
		boolean canMoveLeft = true;
		for (int i = 0; i < currentBlock.getPoints().length; i++) {
			double x = currentBlock.getPoints()[i].getX();
			double y = currentBlock.getPoints()[i].getY();

			if ((int) (x) <= 0) {
				canMoveLeft = false;
			} else if (grid[(int) (y)][(int) (x) - 1] != 0) {
				canMoveLeft = false;
			}
		}
		return canMoveLeft;


	}

	public boolean checkIfCanRotate() {
		currentBlock = blocks.get(blocks.size() - 1);
		if (currentBlock.getColor().equals(Color.yellow)) {
			return false;
		}

		for (int i = 0; i < currentBlock.getPoints().length; i++) {
			if (currentBlock.getPoints()[i].equals(currentBlock.getCenter())) {
				continue;
			}



			int x = (int) (currentBlock.getPoints()[i].getX() - currentBlock.getCenter().getX());
			int y = (int) (currentBlock.getPoints()[i].getY() - currentBlock.getCenter().getY());

			int rotX = -y;
			int rotY = x;

			int newX = (int) (currentBlock.getCenter().getX()) + rotX;
			int newY = (int) (currentBlock.getCenter().getY()) + rotY;
			if (newX < 0 || newX >= 10 || newY < 0 || newY >= 20 || grid[newY][newX] != 0) {
				return false;

			}




		}

		return true;

	}
	public void rotatePiece() {
		if (!isGameOver() && checkIfCanRotate()) {
			currentBlock = blocks.get(blocks.size() - 1);
			currentBlock.rotate();
		}
	}
	public void hardDropPiece() {
		if (!isGameOver) {
			currentBlock = blocks.get(blocks.size() - 1);


			int maxDistance = getMaxDistance();


			currentBlock.hardDrop(maxDistance);
			playerScore += 2 * maxDistance;
			delaying = true;
			currentDelay = delay;
		}
	}
	public void drawGhostPiece(Graphics g) {
		if(!isGameOver) {
			currentBlock = blocks.get(blocks.size() - 1);

			int maxDistance = getMaxDistance();


			for (int i = 0; i < currentBlock.getPoints().length; i++) {
				Color transparent = new Color(255, 255, 255, 128);
				g.setColor(transparent);
				g.fillRect((int) (40 * currentBlock.getPoints()[i].getX()), (int) (40 * (currentBlock.getPoints()[i].getY() + maxDistance)), 40, 40);
				g.setColor(Color.black);
				g.drawRect((int) (40 * currentBlock.getPoints()[i].getX()), (int) (40 * (currentBlock.getPoints()[i].getY() + maxDistance)), 40, 40);
			}

		}


	}
	public int getMaxDistance() {
		int maxDistance = 0;
		for (int distance = 0; distance <= 19; distance++) {
			boolean canDrop = true;
			for (int i = 0; i < currentBlock.getPoints().length; i++) {
				double y = currentBlock.getPoints()[i].getY();
				double x = currentBlock.getPoints()[i].getX();

				if ((int) (y) + distance >= 20 || grid[(int) (y) + distance][(int) x] != 0) {
					canDrop = false;
				}

			}
			if (canDrop) {
				maxDistance = distance;
			} else {

				break;
			}
		}
		return maxDistance;
	}


	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public int getLinesCleared() {
		return linesCleared;
	}

	public void setLinesCleared(int linesCleared) {
		this.linesCleared = linesCleared;
	}


}
