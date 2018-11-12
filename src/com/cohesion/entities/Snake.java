package com.cohesion.entities;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Snake {

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	private PApplet p;
	private Color HEAD_COLOR = new Color(80, 245, 196);
	private Color BODY_COLOR = new Color(80, 200, 163);

	private List<SnakePart> parts;
	private int direction;
	private float xStep;
	private float yStep;

	public Snake(float startX, float startY, int startDirection, float xStep, float yStep) {
		parts = new ArrayList<>();
		parts.add(new SnakePart(startX, startY));
		switch (startDirection) {
		case UP: {
			direction = UP;
		}
			break;
		case DOWN: {
			direction = DOWN;
		}
			break;
		case LEFT: {
			direction = LEFT;
		}
			break;
		case RIGHT: {
			direction = RIGHT;
		}
			break;
		default: {
			direction = LEFT;
		}
			break;
		}
		this.xStep = xStep;
		this.yStep = yStep;
	}

	public Snake(PApplet p, float startX, float startY, int startDirection, float xStep, float yStep) {
		this.p = p;
		parts = new ArrayList<>();
		parts.add(new SnakePart(startX, startY));
		switch (startDirection) {
		case UP: {
			direction = UP;
		}
			break;
		case DOWN: {
			direction = DOWN;
		}
			break;
		case LEFT: {
			direction = LEFT;
		}
			break;
		case RIGHT: {
			direction = RIGHT;
		}
			break;
		default: {
			direction = LEFT;
		}
			break;
		}
		this.xStep = xStep;
		this.yStep = yStep;
	}
	
	public void generateParts(int num)	{
		for (int i = 1; i < num; i++) {
			switch (direction) {
			case Snake.UP: {
				parts.add(new SnakePart(parts.get(0).getPosition().getX(), 
						parts.get(0).getPosition().getY() + yStep
						+ yStep*i));
			}
				break;
			case Snake.DOWN: {
				parts.add(new SnakePart(parts.get(0).getPosition().getX(), 
						parts.get(0).getPosition().getY() - yStep
						- parts.get(0).getPosition().getY()*i));
			}
				break;
			case Snake.LEFT: {
				parts.add(new SnakePart(parts.get(0).getPosition().getX() + xStep
						+ xStep*i, 
						parts.get(0).getPosition().getY()));
			}
				break;
			case Snake.RIGHT: {
				parts.add(new SnakePart(parts.get(0).getPosition().getX() - xStep
						- xStep*i, 
						parts.get(0).getPosition().getY()));
			}
				break;
			}
		}
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		switch (direction) {
		case UP: {
			this.direction = UP;
		}
			break;
		case DOWN: {
			this.direction = DOWN;
		}
			break;
		case LEFT: {
			this.direction = LEFT;
		}
			break;
		case RIGHT: {
			this.direction = RIGHT;
		}
			break;
		}
	}

	public void addPart(SnakePart part) {
		parts.add(part);
	}

	public void addPartFirst() {
		List<SnakePart> newSnake = new ArrayList<>();
		newSnake.add(new SnakePart(new Point(parts.get(0).getPosition().getX(), parts.get(0).getPosition().getY())));
		parts.stream().forEach(e -> {
			newSnake.add(new SnakePart(new Point(e.getPosition().getX(), e.getPosition().getY())));
		});
		switch (direction) {
			case UP: {
				newSnake.get(0).getPosition().setY(newSnake.get(0).getPosition().getY() - yStep);
			}break;
			case DOWN: {
				newSnake.get(0).getPosition().setY(newSnake.get(0).getPosition().getY() + yStep);
			}break;
			case LEFT: {
				newSnake.get(0).getPosition().setX(newSnake.get(0).getPosition().getX() - xStep);
			}break;
			case RIGHT: {
				newSnake.get(0).getPosition().setX(newSnake.get(0).getPosition().getX() + xStep);
			}break;
		}
		parts = newSnake;
	}

	public List<SnakePart> getParts() {
		return parts;
	}

	public void setHeadColor(Color color)	{
		HEAD_COLOR = color;
	}
	
	public Color getHeadColor()	{
		return HEAD_COLOR;
	}
	
	public void setBodyColor(Color color)	{
		BODY_COLOR = color;
	}
	
	public Color getBodyColor()	{
		return BODY_COLOR;
	}
	
	public void calulatePosition() {
		List<SnakePart> buff = new ArrayList<>();
		parts.stream().forEach(e -> {
			buff.add(new SnakePart(new Point(e.getPosition().getX(), e.getPosition().getY())));
		});
		switch (direction) {
		case UP: {
			parts.get(0).getPosition().setY(buff.get(0).getPosition().getY() - yStep);
		}
			break;
		case DOWN: {
			parts.get(0).getPosition().setY(buff.get(0).getPosition().getY() + yStep);
		}
			break;
		case LEFT: {
			parts.get(0).getPosition().setX(buff.get(0).getPosition().getX() - xStep);
		}
			break;
		case RIGHT: {
			parts.get(0).getPosition().setX(buff.get(0).getPosition().getX() + xStep);
		}
			break;
		}
		for (int i = 1; i < parts.size(); i++) {
			parts.get(i).setPosition(buff.get(i - 1).getPosition());
		}
	}

	public Point nextPosition() {
		switch (direction) {
		case UP: {
			return new Point(parts.get(0).getPosition().getX(), parts.get(0).getPosition().getY() - yStep);
		}
		case DOWN: {
			return new Point(parts.get(0).getPosition().getX(), parts.get(0).getPosition().getY() + yStep);
		}
		case LEFT: {
			return new Point(parts.get(0).getPosition().getX() - xStep, parts.get(0).getPosition().getY());
		}
		case RIGHT: {
			return new Point(parts.get(0).getPosition().getX() + xStep, parts.get(0).getPosition().getY());
		}
		}
		return new Point();
	}

	public void display()	{
		p.stroke(BODY_COLOR.getColor());
		p.strokeWeight(5);
		int i = 0;
		for (SnakePart part : parts.subList(1, parts.size())) {
			p.line(parts.get(i).getPosition().getX(), parts.get(i).getPosition().getY(),
					part.getPosition().getX(), part.getPosition().getY());
			i++;
		}
		p.noStroke();
		p.fill(HEAD_COLOR.getColor());
		p.ellipse(parts.get(0).getPosition().getX(), parts.get(0).getPosition().getY(),
				parts.get(0).getSize(), parts.get(0).getSize());
	}
	
	public void display(PApplet p) {
		p.fill(127);
		for (SnakePart part : parts) {
			p.ellipse(part.getPosition().getX(), part.getPosition().getY(), part.getSize(), part.getSize());
		}
	}

	@Override
	public String toString() {
		return "Snake [parts=" + parts + ", direction=" + direction + "]";
	}
}
