package com.cohesion.entities;

public class Point {
	private float X, Y;

	public Point() {
	};

	public Point(float X, float Y) {
		this.X = X;
		this.Y = Y;
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}

	@Override
	public String toString() {
		return "Point [X=" + X + ", Y=" + Y + "]";
	}
}
