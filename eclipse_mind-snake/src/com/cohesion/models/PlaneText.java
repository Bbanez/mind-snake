package com.cohesion.models;

import java.util.HashMap;
import java.util.Map;

import com.cohesion.entities.Color;
import com.cohesion.entities.Point;

import processing.core.PApplet;
import processing.core.PConstants;

public class PlaneText {

	private PApplet gui;
	private Map<String, Float> dims;
	private Map<String, Color> colors;
	private String text = "";
	private int textSize = 12;
	private int textMode = -1;

	public PlaneText(PApplet gui) {
		dims = new HashMap<>();
		colors = new HashMap<>();
		this.gui = gui;
	}

	public PlaneText(PApplet gui, String text) {
		dims = new HashMap<>();
		colors = new HashMap<>();
		this.gui = gui;
		this.text = text;
	}

	public PlaneText(PApplet gui, float width, float height, Point position) {
		dims = new HashMap<>();
		colors = new HashMap<>();
		this.gui = gui;

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", position.getX());
		dims.put("y", position.getY());
		dims.put("xCenter", (position.getX() + width / 2));
		dims.put("yCenter", (position.getY() + height / 2) - 3);
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", position.getX());
		dims.put("y1Display", position.getY());
		dims.put("x2Display", position.getX() + width);
		dims.put("y2Display", position.getY() + height);
	}

	public void padding(int padding) {
		float width = dims.get("width") - padding;
		float height = dims.get("height") - padding;
		Point position = new Point(dims.get("x") + padding / 2, dims.get("y") + padding / 2);

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", position.getX());
		dims.put("y", position.getY());
		dims.put("xCenter", (position.getX() + width / 2));
		dims.put("yCenter", (position.getY() + height / 2) - 3);
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", position.getX());
		dims.put("y1Display", position.getY());
		dims.put("x2Display", position.getX() + width);
		dims.put("y2Display", position.getY() + height);
	}

	public void paddingTop(int padding) {
		float width = dims.get("width");
		float height = dims.get("height") - padding;
		Point position = new Point(dims.get("x"), dims.get("y") + padding);

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", position.getX());
		dims.put("y", position.getY());
		dims.put("xCenter", (position.getX() + width / 2));
		dims.put("yCenter", (position.getY() + height / 2) - 3);
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", position.getX());
		dims.put("y1Display", position.getY());
		dims.put("x2Display", position.getX() + width);
		dims.put("y2Display", position.getY() + height);
	}

	public void paddingRight(int padding) {
		float width = dims.get("width") - padding;
		float height = dims.get("height");
		Point position = new Point(dims.get("x"), dims.get("y"));

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", position.getX());
		dims.put("y", position.getY());
		dims.put("xCenter", (position.getX() + width / 2 - padding));
		dims.put("yCenter", (position.getY() + height / 2) - 3);
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", position.getX());
		dims.put("y1Display", position.getY());
		dims.put("x2Display", position.getX() + width - padding);
		dims.put("y2Display", position.getY() + height);
	}

	public void paddingBottom(int padding) {
		float width = dims.get("width");
		float height = dims.get("height") - padding;
		Point position = new Point(dims.get("x"), dims.get("y"));

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", position.getX());
		dims.put("y", position.getY());
		dims.put("xCenter", (position.getX() + width / 2));
		dims.put("yCenter", (position.getY() + height / 2 - padding) - 3);
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", position.getX());
		dims.put("y1Display", position.getY());
		dims.put("x2Display", position.getX() + width);
		dims.put("y2Display", position.getY() + height - padding);
	}

	public void paddingLeft(int padding) {
		float width = dims.get("width") - padding;
		float height = dims.get("height");
		Point position = new Point(dims.get("x") + padding, dims.get("y"));

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", position.getX());
		dims.put("y", position.getY());
		dims.put("xCenter", (position.getX() + width / 2));
		dims.put("yCenter", (position.getY() + height / 2) - 3);
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", position.getX());
		dims.put("y1Display", position.getY());
		dims.put("x2Display", position.getX() + width);
		dims.put("y2Display", position.getY() + height);
	}

	public void padding(int top_bottom, int left_right) {
		dims.put("x1Display", dims.get("x") + left_right);
		dims.put("y1Display", dims.get("y") + top_bottom);
		dims.put("x2Display", dims.get("x") + dims.get("width") - left_right);
		dims.put("y2Display", dims.get("y") + dims.get("height") - top_bottom);
	}

	public void setColors(Map<String, Color> colors) {
		this.colors = colors;
	}

	public void setColors(Color textPrimary, Color textHover, Color textActive) {
		colors.put("textPrimary", textPrimary);
		colors.put("textHover", textHover);
		colors.put("textActive", textActive);
	}

	public Map<String, Color> getColors() {
		return colors;
	}

	public void setTextPrimaryColor(Color color) {
		colors.put("textPrimary", color);
	}

	public Color getTextPromaryColor() {
		return colors.get("textPrimary");
	}

	public void setTextHoverColor(Color color) {
		colors.put("textHover", color);
	}

	public Color getTextHoverColor() {
		return colors.get("textHover");
	}

	public void setTextActiveColor(Color color) {
		colors.put("textActive", color);
	}

	public Color getTextActiveColor() {
		return colors.get("textActive");
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public int getTextMode() {
		return textMode;
	}

	public void setTextMode(int textMode) {
		this.textMode = textMode;
	}

	public void display() {
		if (textMode == -1) {
			gui.textAlign(PConstants.CENTER, PConstants.CENTER);
			gui.textSize(textSize);

			gui.fill(colors.get("textPrimary").getRgb().get(0), 
					colors.get("textPrimary").getRgb().get(1),
					colors.get("textPrimary").getRgb().get(2));
			gui.text(text, dims.get("xCenter"), dims.get("yCenter"));
		} else if (textMode == PApplet.LEFT) {
			gui.textAlign(PConstants.LEFT, PConstants.CENTER);
			gui.textSize(textSize);

			gui.fill(colors.get("textPrimary").getRgb().get(0), 
					colors.get("textPrimary").getRgb().get(1),
					colors.get("textPrimary").getRgb().get(2));
			gui.text(text, dims.get("x"), dims.get("yCenter"));
		} else if (textMode == PApplet.RIGHT) {
			gui.textAlign(PConstants.RIGHT, PConstants.CENTER);
			gui.textSize(textSize);

			gui.fill(colors.get("textPrimary").getRgb().get(0), 
					colors.get("textPrimary").getRgb().get(1),
					colors.get("textPrimary").getRgb().get(2));
			gui.text(text, dims.get("x2Display"), dims.get("yCenter"));
		}
	}

	@Override
	public String toString() {
		return "PlaneText [gui=" + gui + ", dims=" + dims + ", colors=" + colors + ", text=" + text + ", textSize="
				+ textSize + ", textMode=" + textMode + "]";
	}
}
