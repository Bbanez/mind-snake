package com.cohesion.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cohesion.entities.Color;
import com.cohesion.entities.Point;

import processing.core.PApplet;

public class InputField {

	private PApplet gui;
	private List<Character> data;
	private String text = "";
	private Map<String, Float> dims;
	private Map<String, Color> colors;
	private boolean activeLatch = false;
	private boolean focus = false;
	private int textSize = 12;
	private int textMode = -1;

	public InputField(PApplet gui) {
		this.gui = gui;
		data = new ArrayList<>();
		dims = new HashMap<>();
		colors = new HashMap<>();
		colors.put("background", new Color("e3e3e3"));
	}

	public InputField(PApplet gui, float width, float height, Point position) {
		this.gui = gui;
		data = new ArrayList<>();
		dims = new HashMap<>();
		colors = new HashMap<>();

		dims.put("width", width);
		dims.put("height", height);
		dims.put("x", position.getX());
		dims.put("y", position.getY());
		dims.put("xCenter", (position.getX() + width / 2));
		dims.put("yCenter", (position.getY() + height / 2));
		dims.put("widthHalf", width / 2);
		dims.put("heightHalf", height / 2);
		dims.put("x1Display", position.getX());
		dims.put("y1Display", position.getY());
		dims.put("x2Display", position.getX() + width);
		dims.put("y2Display", position.getY() + height);

		colors.put("background", new Color("e3e3e3"));
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
		float width = dims.get("width") - left_right;
		float height = dims.get("height") - top_bottom;
		Point position = new Point(dims.get("x") + left_right / 2, 
				dims.get("y") + top_bottom / 2);

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

	public void setColors(Map<String, Color> colors) {
		this.colors = colors;
	}

	public void setColors(Color primary, Color hover, Color active) {
		colors.put("primary", primary);
		colors.put("hover", hover);
		colors.put("active", active);
		colors.put("textPrimary", new Color("000000"));
		colors.put("textHover", new Color("000000"));
		colors.put("textActive", new Color("000000"));
	}

	public void setColors(Color primary, Color hover, Color active, Color textPrimary, Color textHover,
			Color textActive) {
		colors.put("primary", primary);
		colors.put("hover", hover);
		colors.put("active", active);
		colors.put("textPrimary", textPrimary);
		colors.put("textHover", textHover);
		colors.put("textActive", textActive);
	}

	public Map<String, Color> getColors() {
		return colors;
	}

	public void setPrimaryColor(Color color) {
		colors.put("primary", color);
	}

	public Color getPromaryColor() {
		return colors.get("primary");
	}

	public void setTextPrimaryColor(Color color) {
		colors.put("textPrimary", color);
	}

	public Color getTextPromaryColor() {
		return colors.get("textPrimary");
	}

	public void setHoverColor(Color color) {
		colors.put("hover", color);
	}

	public Color getHoverColor() {
		return colors.get("hover");
	}

	public void setTextHoverColor(Color color) {
		colors.put("textHover", color);
	}

	public Color getTextHoverColor() {
		return colors.get("textHover");
	}

	public void setActiveColor(Color color) {
		colors.put("active", color);
	}

	public Color getActiveColor() {
		return colors.get("active");
	}

	public void setTextActiveColor(Color color) {
		colors.put("textActive", color);
	}

	public Color getTextActiveColor() {
		return colors.get("textActive");
	}

	public void setBackgroundColor(Color color) {
		colors.put("background", color);
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextMode(int textMode) {
		this.textMode = textMode;
	}

	public int getTextMode() {
		return textMode;
	}

	public void setFocus() {
		focus = true;
	}

	public void reset() {
		focus = false;
	}

	public void appendKey(char key) {
		switch (key) {
			case 0x08: { // Backspace
				if (data.size() > 0)
					data.remove(data.size() - 1);
			}break;
			default: {
				data.add(key);
			}break;
		}

		text = "";
		data.stream().forEach(d -> text += d);
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text)	{
		this.text = text;
	}

	public boolean isFocus() {
		return focus;
	}

	public void display() {
		gui.strokeWeight(5);
		gui.rectMode(PApplet.CORNERS);
		Point printAt = new Point();
		switch (textMode) {
		case -1: {
			gui.textAlign(PApplet.CENTER, PApplet.CENTER);
			printAt = new Point(dims.get("xCenter"), dims.get("yCenter"));
		}
			break;
		case PApplet.LEFT: {
			gui.textAlign(PApplet.LEFT, PApplet.CENTER);
			printAt = new Point(dims.get("x"), dims.get("yCenter"));
		}
			break;
		case PApplet.RIGHT: {
			gui.textAlign(PApplet.LEFT, PApplet.CENTER);
			printAt = new Point(dims.get("x2Display"), dims.get("yCenter"));
		}
			break;
		}
		gui.textSize(textSize);

		if (gui.mouseX > dims.get("x1Display") && gui.mouseX < dims.get("x2Display")
				&& gui.mouseY > dims.get("y1Display") && gui.mouseY < dims.get("y2Display")) {

			if (gui.mousePressed && !activeLatch) {
				activeLatch = true;
			} else if (!gui.mousePressed && activeLatch) {
				activeLatch = false;
				focus = true;
			}

			if (!focus) {
				gui.fill(colors.get("background").getRgb().get(0), 
						colors.get("background").getRgb().get(1),
						colors.get("background").getRgb().get(2));
				gui.stroke(colors.get("hover").getRgb().get(0), 
						colors.get("hover").getRgb().get(1),
						colors.get("hover").getRgb().get(2));
				gui.rect(dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"), dims.get("y2Display"));

				gui.fill(colors.get("textHover").getRgb().get(0), 
						colors.get("textHover").getRgb().get(1),
						colors.get("textHover").getRgb().get(2));
				gui.text(text, printAt.getX(), printAt.getY());
			}
		} else {
			if (gui.mousePressed && focus) {
				focus = false;
			}

			if (!focus) {
				gui.fill(colors.get("background").getRgb().get(0), 
						colors.get("background").getRgb().get(1),
						colors.get("background").getRgb().get(2));
				gui.stroke(colors.get("primary").getRgb().get(0), 
						colors.get("primary").getRgb().get(1),
						colors.get("primary").getRgb().get(2));
				gui.rect(dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"), dims.get("y2Display"));

				gui.fill(colors.get("textPrimary").getRgb().get(0), 
						colors.get("textPrimary").getRgb().get(1),
						colors.get("textPrimary").getRgb().get(2));
				gui.text(text, printAt.getX(), printAt.getY());
			}
		}

		if (focus) {
			gui.fill(colors.get("background").getRgb().get(0), 
					colors.get("background").getRgb().get(1),
					colors.get("background").getRgb().get(2));
			gui.stroke(colors.get("active").getRgb().get(0), 
					colors.get("active").getRgb().get(1),
					colors.get("active").getRgb().get(2));
			gui.rect(dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"), dims.get("y2Display"));

			gui.fill(colors.get("textActive").getRgb().get(0), 
					colors.get("textActive").getRgb().get(1),
					colors.get("textActive").getRgb().get(2));
			gui.text(text, printAt.getX(), printAt.getY());
		}
	}
}
