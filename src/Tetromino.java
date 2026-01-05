import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Tetromino {
	private int x;
	private int y;
	private Color color;
	private Point[] points;
	private Point center;
	private int type;


	public Tetromino(int type, int x, int y) {
		this.type = type;
		if (type == 1) { 
			this.color = Color.BLUE;
			points = new Point[4];
			points[0] = new Point(x - 2, y);

			this.center = new Point(x - 1, y);
			points[1] = center;
			points[2] = new Point(x, y);
			points[3] = new Point(x + 1, y);

		} if (type == 2) {
			this.color = Color.YELLOW;
			points = new Point[4];
			this.center = new Point(x, y);
			points[0] = center;
			points[1] = new Point(x - 1, y);
			points[2] = new Point(x - 1, y + 1);
			points[3] = new Point(x, y + 1);
		} if (type == 3) {
			this.color = Color.MAGENTA;
			points = new Point[4];
			points[0] = new Point(x - 2, y + 1);
			points[1] = new Point(x - 1, y);
			this.center = new Point(x - 1, y + 1);
			points[2] = center;
			points[3] = new Point(x, y + 1);
		} if (type == 4) {
			this.color = Color.CYAN;
			points = new Point[4];
			points[0] = new Point(x - 2, y + 1);
			points[1] = new Point(x - 2, y);
			this.center = new Point(x - 1, y + 1);
			points[2] = center;
			points[3] = new Point(x, y + 1);
		} if (type == 5) {
			this.color = Color.ORANGE;
			points = new Point[4];
			points[0] = new Point(x - 2, y + 1);
			points[1] = new Point(x, y);
			this.center = new Point(x - 1, y + 1);
			points[2] = center;
			points[3] = new Point(x, y + 1);
		} if (type == 6) {
			this.color = Color.RED;
			points = new Point[4];
			points[0] = new Point(x - 2, y);
			points[1] = new Point(x - 1, y);
			points[2] = new Point(x - 1, y + 1);
			this.center = new Point(x, y + 1);
			points[3] = center;

		} if (type == 7) {
			this.color = Color.GREEN;
			points = new Point[4];	
			points[0] = new Point(x - 1, y);
			points[1] = new Point(x, y);
			points[2] = new Point(x - 2, y + 1);
			this.center = new Point(x - 1, y + 1);
			points[3] = center;
		}
	}

	public void rotate() {

		for (int i = 0; i < points.length; i++) {
			if (points[i].equals(center)) {
				continue;
			}



			int x = (int) (points[i].getX() - center.getX());
			int y = (int) (points[i].getY() - center.getY());

			int rotX = -y;
			int rotY = x;


			points[i].setLocation(center.getX() + rotX, center.getY() + rotY);




		}
	}

	public void hardDrop(int y) {
		for (int i = 0; i < points.length; i++) {
			points[i].setLocation(points[i].getX(), points[i].getY() + y);
		}
	}
	public void update() {
		for (int i = 0; i < points.length; i++) {
			points[i].setLocation(points[i].getX(), points[i].getY() + 1);
		}
	}

	public void moveLeft() {
		for (int i = 0; i < points.length; i++) {
			points[i].setLocation(points[i].getX() - 1, points[i].getY());
		}
	}
	public void moveDown() {
		for (int i = 0; i < points.length; i++) {
			points[i].setLocation(points[i].getX(), points[i].getY() + 1);
		}
	}
	public void moveRight() {
		for (int i = 0; i < points.length; i++) {
			points[i].setLocation(points[i].getX() + 1, points[i].getY());
		}
	}




	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;

	}
	public Point[] getPoints() {
		return points;
	}



	public Point getCenter() {
		return center;
	}



	public void setCenter(Point center) {
		this.center = center;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}






}
