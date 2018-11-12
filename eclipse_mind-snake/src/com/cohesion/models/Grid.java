package com.cohesion.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cohesion.entities.Point;

import processing.core.PApplet;

public class Grid {

	private PApplet gui;
	private Map<String, Float> dims;
	private Map<String, Point> points;
	private List<Integer> size;

	public Grid(PApplet gui, float width, float height, Point offset, int numOfRows, int numOfColumns) {
		width = width - offset.getX();

		dims = new HashMap<>();
		points = new HashMap<>();
		this.gui = gui;

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", offset.getX());
		dims.put("y", offset.getY());
		dims.put("xCenter", (offset.getX() + width / 2));
		dims.put("yCenter", (offset.getY() + height / 2));
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", offset.getX());
		dims.put("y1Display", offset.getY());
		dims.put("x2Display", offset.getX() + width);
		dims.put("y2Display", offset.getY() + height);

		size = new ArrayList<>();
		size.add(numOfColumns);
		size.add(numOfRows);
		setSize(size.get(0), size.get(1));
	}

	public Grid(PApplet gui, Scene scene, int numOfColumns, int numOfRows) {
		size = new ArrayList<>();
		size.add(numOfColumns);
		size.add(numOfRows);

		dims = scene.getDimensions();
		points = new HashMap<>();

		this.gui = gui;
		setSize(size.get(0), size.get(1));
	}

	public void setScene(Scene scene) {
		dims = scene.getDimensions();
	}

	public Map<String, Point> getPoints() {
		return points;
	}

	public Point getPoint(int abs) {
		try {
			int col = -1, row = -1;

			for (int r = 0; r < size.get(1); r++) {
				for (int c = 0; c < size.get(0); c++) {
					if (r * (size.get(0) - 1) + r + c == abs) {
						col = c;
						row = r;
					}
				}
			}
			if (col != -1 && row != -1)
				return points.get(col + "," + row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Point getPoint(int column, int row) {
		try {
			return points.get(column + "," + row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSize(int columns, int rows) {
		float xStep, yStep;

		xStep = dims.get("width") / columns;
		yStep = dims.get("height") / rows;

		dims.put("xStep", xStep);
		dims.put("yStep", yStep);

		size.set(0, columns);
		size.set(1, rows);

		points = new HashMap<>();

		for (int i = 0; i <= columns; i++) {
			for (int j = 0; j <= rows; j++) {
				points.put(i + "," + j, new Point(dims.get("x") + i * xStep, dims.get("y") + j * yStep));
			}
		}
	}

	public List<Integer> getSize() {
		return size;
	}

	public int getCols() {
		return size.get(0);
	}

	public int getRows() {
		return size.get(1);
	}

	public float getxSpace() {
		return dims.get("xStep");
	}

	public float getySpace() {
		return dims.get("yStep");
	}

	public float getWidth() {
		return dims.get("width");
	}

	public float getHeight() {
		return dims.get("height");
	}

	public List<Point> getCellCorners(int row, int col) {
		List<Point> ps = new ArrayList<>();
		ps.add(points.get(0 + "," + 0));
		ps.add(points.get(size.get(0) + "," + 0));
		ps.add(points.get(size.get(0) + "," + size.get(1)));
		ps.add(points.get(0 + "," + size.get(1)));
		return ps;
	}

	public void display(PApplet p) {
		p.stroke(127);
		p.strokeWeight(1);
		// Draw vertical lines
		for (int i = 0; i <= size.get(0); i++) {
			try {
				p.line(points.get(i + "," + size.get(1)).getX(), points.get(i + "," + size.get(1)).getY(),
						points.get(i + "," + 0).getX(), points.get(i + "," + 0).getY());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Draw horizontal lines
		for (int i = 0; i <= size.get(1); i++) {
			try {
				p.line(points.get(size.get(0) + "," + i).getX(), points.get(size.get(0) + "," + i).getY(),
						points.get(0 + "," + i).getX(), points.get(0 + "," + i).getY());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		p.noStroke();
	}

	@Override
	public String toString() {
		return "Grid [gui=" + gui + ", dims=" + dims + ", points=" + points + ", size=" + size + "]";
	}
}
