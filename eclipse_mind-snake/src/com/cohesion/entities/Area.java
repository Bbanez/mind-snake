package com.cohesion.entities;

import java.util.ArrayList;
import java.util.List;

public class Area {

	private List<Point> corners;
	private Point center;
	private float width;
	private float height;
	private float halfWidth;
	private float halfHeight;

	public Area(float width, float height) {
		this.width = width;
		this.height = height;
		halfWidth = width / 2f;
		halfHeight = height / 2f;

		float offsX = 0;
		float offsY = 0;

		corners = new ArrayList<>();
		corners.add(new Point((int) offsX, (int) offsY)); // TOP LEFT
		corners.add(new Point((int) offsX + (int) width, (int) offsY)); // TOP RIGHT
		corners.add(new Point((int) offsX + (int) width, (int) offsY + (int) height)); // BOTTOM RIGHT
		corners.add(new Point((int) offsX, (int) offsY + (int) height)); // BOTTOM LEFT

		center = new Point((int) (halfWidth + offsX), (int) (halfHeight + offsY));
	}

	public Area(float width, float height, float offsX, float offsY) {
		this.width = width;
		this.height = height;
		halfWidth = width / 2f;
		halfHeight = height / 2f;

		corners = new ArrayList<>();
		corners.add(new Point((int) offsX, (int) offsY)); // TOP LEFT
		corners.add(new Point((int) offsX + (int) width, (int) offsY)); // TOP RIGHT
		corners.add(new Point((int) offsX + (int) width, (int) offsY + (int) height)); // BOTTOM RIGHT
		corners.add(new Point((int) offsX, (int) offsY + (int) height)); // BOTTOM LEFT

		center = new Point((int) (halfWidth + offsX), (int) (halfHeight + offsY));
	}

	public List<Point> getCorners() {
		return corners;
	}

	public void setCorners(List<Point> corners) {
		this.corners = corners;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getHalfWidth() {
		return halfWidth;
	}

	public void setHalfWidth(float halfWidth) {
		this.halfWidth = halfWidth;
	}

	public float getHalfHeight() {
		return halfHeight;
	}

	public void setHalfHeight(float halfHeight) {
		this.halfHeight = halfHeight;
	}

	@Override
	public String toString() {
		return "Area [corners=" + corners + ", center=" + center + ", width=" + width + ", height=" + height
				+ ", halfWidth=" + halfWidth + ", halfHeight=" + halfHeight + "]";
	}
}
