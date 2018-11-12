package com.cohesion.entities;

public class SnakePart {

	private Point position;
	private int size = 7;

	public SnakePart() {
	}

	public SnakePart(Point position) {
		super();
		this.position = position;
	}

	public SnakePart(float x, float y) {
		position = new Point(x, y);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "SnakePart [position=" + position + ", size=" + size + "]";
	}
}
