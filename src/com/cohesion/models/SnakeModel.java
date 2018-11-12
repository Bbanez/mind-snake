package com.cohesion.models;

import com.cohesion.entities.Color;
import com.cohesion.entities.Fruit;
import com.cohesion.entities.Point;
import com.cohesion.entities.Snake;
import com.cohesion.entities.SnakePart;

import processing.core.PApplet;

public class SnakeModel {

	private PApplet p;
	private Snake snake;
	private Grid grid;

	public SnakeModel(PApplet p, Grid grid) {
		this.p = p;
		this.grid = grid;

		int startX = (int) p.random(21, grid.getCols() - 21);
		int startY = (int) p.random(21, grid.getRows() - 21);
		int direction = (int) p.random(1, 4);
		snake = new Snake(grid.getPoint(startX, startY).getX(), grid.getPoint(startX, startY).getY(), direction,
				grid.getxSpace(), grid.getySpace());
		for (int i = 1; i < 20; i++) {
			switch (direction) {
			case Snake.UP: {
				snake.addPart(new SnakePart(grid.getPoint(startX, startY + i)));
			}
				break;
			case Snake.DOWN: {
				snake.addPart(new SnakePart(grid.getPoint(startX, startY - i)));
			}
				break;
			case Snake.LEFT: {
				snake.addPart(new SnakePart(grid.getPoint(startX + i, startY)));
			}
				break;
			case Snake.RIGHT: {
				snake.addPart(new SnakePart(grid.getPoint(startX - i, startY)));
			}
				break;
			}
		}
	}

	public SnakeModel(PApplet p, Grid grid, Point startAt, int direction) {
		this.p = p;
		this.grid = grid;

		float startX = startAt.getX();
		float startY = startAt.getY();
		snake = new Snake(startX, startY, direction, 5, 5);
		for (int i = 1; i < 40; i++) {
			switch (direction) {
			case Snake.UP: {
				snake.addPart(new SnakePart(new Point(startX, startY + i)));
			}
				break;
			case Snake.DOWN: {
				snake.addPart(new SnakePart(new Point(startX, startY - i)));
			}
				break;
			case Snake.LEFT: {
				snake.addPart(new SnakePart(new Point(startX + i, startY)));
			}
				break;
			case Snake.RIGHT: {
				snake.addPart(new SnakePart(new Point(startX - i, startY)));
			}
				break;
			}
		}
	}

	public Snake getSnake() {
		return snake;
	}
	
	public Grid getGrid()	{
		return grid;
	}

	public void setHeadColor(Color color) {
		snake.setHeadColor(color);
	}

	public void setBodyColor(Color color) {
		snake.setBodyColor(color);
	}

	public boolean isOut() {
		if (snake.getParts().get(0).getPosition().getX() >= grid.getPoint(grid.getCols(), 0).getX()
				|| snake.getParts().get(0).getPosition().getX() <= grid.getPoint(0, 0).getX()
				|| snake.getParts().get(0).getPosition().getY() >= grid.getPoint(0, grid.getRows()).getY()
				|| snake.getParts().get(0).getPosition().getY() <= grid.getPoint(0, 0).getY()) {
			return true;
		}
		return false;
	}

	public boolean isEnatingTail() {
		for (SnakePart part : snake.getParts().subList(2, snake.getParts().size())) {
			if (snake.getParts().get(0).getPosition().getX() == part.getPosition().getX()
					&& snake.getParts().get(0).getPosition().getY() == part.getPosition().getY()) {
				return true;
			}
		}
		return false;
	}

	public boolean isEatingFruit(Fruit fruit) {
		Point nextPosition = snake.nextPosition();
		if (nextPosition.getX() == fruit.getPosition().getX() && nextPosition.getY() == fruit.getPosition().getY()) {
			snake.addPartFirst();
			return true;
		}
		return false;
	}

	public float howCloseToEdge() {
		float topEdge = grid.getPoint(0, 0).getY();
		float bottomEdge = grid.getPoint(0, grid.getRows()).getY();
		float leftEdge = grid.getPoint(0, 0).getX();
		float rightEdge = grid.getPoint(grid.getCols(), 0).getX();

		float headX = snake.getParts().get(0).getPosition().getX();
		float headY = snake.getParts().get(0).getPosition().getY();

		if (headX > leftEdge + grid.getWidth() / 2) {
			headX = rightEdge - headX;
		} else {
			headX = headX - leftEdge;
		}

		if (headY > topEdge + grid.getHeight() / 2) {
			headY = bottomEdge - headY;
		} else {
			headY = headY - topEdge;
		}

		if (headX < 150 || headY < 150) {
			if (headX < headY)
				return headX * 30 / 150;
			else
				return headY * 30 / 150;
		} else {
			return 30;
		}
	}

	public void display() {
		float sub = howCloseToEdge();
		p.stroke(71 - sub, 10, 45);
		p.strokeWeight(3);
		p.line(grid.getPoint(0, 0).getX(), grid.getPoint(0, 0).getY(), grid.getPoint(grid.getCols(), 0).getX(),
				grid.getPoint(0, 0).getY());
		p.line(grid.getPoint(0, 0).getX(), grid.getPoint(0, grid.getRows()).getY(),
				grid.getPoint(grid.getCols(), 0).getX(), grid.getPoint(0, grid.getRows()).getY());
		p.line(grid.getPoint(0, 0).getX(), grid.getPoint(0, 0).getY(), grid.getPoint(0, 0).getX(),
				grid.getPoint(0, grid.getRows()).getY());
		p.line(grid.getPoint(grid.getCols(), 0).getX(), grid.getPoint(grid.getCols(), 0).getY(),
				grid.getPoint(grid.getCols(), grid.getRows()).getX(),
				grid.getPoint(grid.getCols(), grid.getRows()).getY());
		p.stroke(snake.getBodyColor().getColor());
		p.strokeWeight(5);
		int i = 0;
		for (SnakePart part : snake.getParts().subList(1, snake.getParts().size())) {
			p.line(snake.getParts().get(i).getPosition().getX(), snake.getParts().get(i).getPosition().getY(),
					part.getPosition().getX(), part.getPosition().getY());
			i++;
		}
		p.noStroke();
		p.fill(snake.getHeadColor().getColor());
		p.ellipse(snake.getParts().get(0).getPosition().getX(), snake.getParts().get(0).getPosition().getY(),
				snake.getParts().get(0).getSize(), snake.getParts().get(0).getSize());
	}
}
