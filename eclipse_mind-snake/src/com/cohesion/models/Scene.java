package com.cohesion.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cohesion.entities.Color;
import com.cohesion.entities.Point;
import com.cohesion.exceptions.CustomException;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Scene {

	private PApplet gui;
	private Map<String, Float> dims;
	private Map<String, Object> children;
	private Grid grid;
	private Color color;
	private PImage image;
	private boolean colorImage = false;
	private boolean background = false;

	int counter = 0;

	public Scene(PApplet gui) {
		dims = new HashMap<>();
		children = new HashMap<>();
		this.gui = gui;
		color = new Color();
	}

	public Scene(PApplet gui, float width, float height, Point offset) {
		dims = new HashMap<>();
		children = new HashMap<>();
		this.gui = gui;
		color = new Color();

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

//		System.out.println(dims);
	}

	public void setGrid(int col, int row) {
		grid = new Grid(gui, this, col, row);
//		System.out.println(grid);
	}

	public Grid getGrid() {
		return grid;
	}

	public Map<String, Float> getDimensions() {
		return dims;
	}

	public void setDimensions(Map<String, Float> dims) {
		this.dims = dims;
	}

	public void padding(int padding) {
		dims.put("x1Display", dims.get("x") - padding);
		dims.put("y1Display", dims.get("y") - padding);
		dims.put("x2Display", dims.get("x") + dims.get("width") - padding);
		dims.put("y2Display", dims.get("y") + dims.get("height") - padding);
	}

	public void paddingTop(int padding) {
		dims.put("y1Display", dims.get("y") - padding);
	}

	public void paddingRight(int padding) {
		dims.put("x2Display", dims.get("x") - dims.get("width") - padding);
	}

	public void paddingBottom(int padding) {
		dims.put("y2Display", dims.get("y") - dims.get("height") - padding);
	}

	public void paddingLeft(int padding) {
		dims.put("x1Display", dims.get("x") - padding);
	}

	public void padding(int top_bottom, int left_right) {
		dims.put("x1Display", dims.get("x") - top_bottom);
		dims.put("y1Display", dims.get("y") - left_right);
		dims.put("x2Display", dims.get("x") + dims.get("width") - top_bottom);
		dims.put("y2Display", dims.get("y") + dims.get("height") - left_right);
	}

	public void setColor(String hexColor) {
		colorImage = false;
		color.set(hexColor);
		background = true;
	}

	public void setImage(String pathToImage) {
		try {
			image = gui.loadImage(pathToImage);
			colorImage = true;
		} catch (Exception e) {
			colorImage = false;
			e.printStackTrace();
		}
	}

	public void addChiled(String id, Object chiled) {
		children.put(id, chiled);
	}

	public void appendChiled(String id, Object chiled) throws CustomException {

		if (children.size() < grid.getPoints().size()) {
			if (chiled instanceof Button) {
				Button button = (Button) chiled;

				Point a = grid.getPoint(children.size());

				Button b = new Button(gui, dims.get("width") / grid.getSize().get(0),
						dims.get("height") / grid.getSize().get(1), a);
				b.setColors(button.getColors());
				b.setText(button.getText());
				children.put(id, b);
			} else if (chiled instanceof InputField) {
				InputField input = (InputField) chiled;

				Point a = grid.getPoint(children.size());

				InputField i = new InputField(gui, dims.get("width") / grid.getSize().get(0),
						dims.get("height") / grid.getSize().get(1), a);
				i.setColors(input.getColors());
				i.setTextSize(input.getTextSize());
				i.setTextMode(input.getTextMode());

				if (input.isFocus())
					i.setFocus();
				children.put(id, i);
			} else if (chiled instanceof TextArea) {
				TextArea textArea = (TextArea) chiled;

				Point a = grid.getPoint(children.size());

				TextArea t = new TextArea(gui, dims.get("width") / grid.getSize().get(0),
						dims.get("height") / grid.getSize().get(1), a);
				t.setColors(textArea.getColors());
				children.put(id, t);
			} else if (chiled instanceof PlaneText) {
				PlaneText planeText = (PlaneText) chiled;

				Point a = grid.getPoint(children.size());

				PlaneText t = new PlaneText(gui, dims.get("width") / grid.getSize().get(0),
						dims.get("height") / grid.getSize().get(1), a);
				t.setColors(planeText.getColors());
				t.setText(planeText.getText());
				t.setTextMode(planeText.getTextMode());
				t.setTextSize(planeText.getTextSize());
				children.put(id, t);
			} else if (chiled instanceof Scene) {
				Scene scene = (Scene) chiled;

				Point a = grid.getPoint(children.size());

				Scene s = new Scene(gui, dims.get("width") / grid.getSize().get(0),
						dims.get("height") / grid.getSize().get(1), a);
				for (int i = 0; i < scene.getChildrenIds().size(); i++) {
					s.appendChiled(scene.getChildrenIds().get(i), scene.getChiled(scene.getChildrenIds().get(i)));
				}
				children.put(id, s);
			}
		} else {
			throw new CustomException("Can not append child to this scene. Limit reached!");
		}
	}

	public void updateChiled(String id, Object chiled) {
		if (children.containsKey(id)) {
			children.put(id, chiled);
		}
	}

	public Object getChiled(String id) {
		return children.get(id);
	}

	public List<String> getChildrenIds() {
		List<String> ids = new ArrayList<>();
		children.entrySet().stream().forEach(c -> ids.add(c.getKey()));
		return ids;
	}

	public void removeChiled(String id) {
		children.remove(id);
	}

	public void display() {

		if (colorImage) {
			gui.image(image, dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"),
					dims.get("y2Display"));
		} else if (background) {
			gui.noStroke();
			gui.fill(color.getColor());
			gui.rectMode(PConstants.CORNERS);
			gui.rect(dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"), dims.get("y2Display"));
		}

		if (children.size() > 0 && grid.getPoints().size() > 0) {
			children.entrySet().stream().forEach(c -> {
				if (c.getValue() instanceof Button) {
					Button button = (Button) c.getValue();
					button.display();
				} else if (c.getValue() instanceof InputField) {
					InputField input = (InputField) c.getValue();
					input.display();
				} else if (c.getValue() instanceof TextArea) {
					TextArea textArea = (TextArea) c.getValue();
					textArea.display();
				} else if (c.getValue() instanceof PlaneText) {
					PlaneText planeText = (PlaneText) c.getValue();
					planeText.display();
				} else if (c.getValue() instanceof Scene) {
					Scene scene = (Scene) c.getValue();
					scene.display();
				}
			});
		}
	}

	public void displayGrid() {
		grid.display(gui);
	}
}
