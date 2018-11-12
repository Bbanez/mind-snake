package com.cohesion.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cohesion.entities.Color;
import com.cohesion.entities.Point;

import processing.core.PApplet;
import processing.core.PConstants;

public class TextArea {

	private PApplet gui;
	private Map<String, Float> dims;
	private Map<String, Color> colors;
	private List<String> data;
	private int displayOffs = 0;
	private int fontSize = 8;
	private final int[] printMargin = { 5, 5 };

	private boolean activeLatch = false;
	private boolean focus = false;

	public TextArea(PApplet gui) {
		dims = new HashMap<>();
		colors = new HashMap<>();
		this.gui = gui;
		colors.put("background", new Color("d0d0d0"));
		data = new ArrayList<>();
	}

	public TextArea(PApplet gui, float width, float height, Point position) {
		dims = new HashMap<>();
		colors = new HashMap<>();
		data = new ArrayList<>();
		this.gui = gui;

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

		colors.put("background", new Color("d0d0d0"));
	}

	public void padding(int padding) {
		dims.put("x1Display", dims.get("x") + padding);
		dims.put("y1Display", dims.get("y") + padding);
		dims.put("x2Display", dims.get("x") + dims.get("width") - padding);
		dims.put("y2Display", dims.get("y") + dims.get("height") - padding);
	}

	public void paddingTop(int padding) {
		dims.put("y1Display", dims.get("y") + padding);
	}

	public void paddingRight(int padding) {
		dims.put("x2Display", dims.get("x") + dims.get("width") - padding);
	}

	public void paddingBottom(int padding) {
		dims.put("y2Display", dims.get("y") + dims.get("height") - padding);
	}

	public void paddingLeft(int padding) {
		dims.put("x1Display", dims.get("x") + padding);
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

	public void appendText(String s) {

		for (int i = 0; i < s.length(); i++) {
			if (data.size() > 0) {
				if (data.get(data.size() - 1).length() * fontSize < dims.get("width")) {

					if (s.charAt(i) == '\n') {
						data.add("");

						if ((data.size() - 1) * (fontSize + 3) > dims.get("height") - fontSize - 20) {
							displayOffs++;
						}
					} else if (s.charAt(i) != '\r') {
						data.set(data.size() - 1, data.get(data.size() - 1) + s.charAt(i));
					}
				} else {
					data.add("" + s.charAt(i));

					if ((data.size() - 1) * (fontSize + 3) > dims.get("height") - fontSize - 20) {
						displayOffs++;
					}
				}
			} else {
				data.add("" + s.charAt(i));

				if ((data.size() - 1) * (fontSize + 3) > dims.get("height") - fontSize - 20) {
					displayOffs++;
				}
			}
		}
	}

	public List<String> getData() {
		return data;
	}

	public String getDataAsString() {
		String s = "";

		for (int i = 0; i < data.size(); i++) {
			s += "\r\n" + data.get(i);
		}

		return s;
	}

	public void display() {
		gui.strokeWeight(2);
		gui.rectMode(PConstants.CORNERS);
		gui.textAlign(PConstants.LEFT);

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
				for (int i = displayOffs; i < data.size(); i++) {
					float printRowY = dims.get("y1Display") + (fontSize + 3) * (i - displayOffs) + fontSize;
					gui.text(data.get(i), dims.get("x1Display") + printMargin[0], printRowY + printMargin[1]);
				}
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

				for (int i = displayOffs; i < data.size(); i++) {
					float printRowY = dims.get("y1Display") + (fontSize + 3) * (i - displayOffs) + fontSize;
					gui.text(data.get(i), dims.get("x1Display") + printMargin[0], printRowY + printMargin[1]);
				}
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
			for (int i = displayOffs; i < data.size(); i++) {
				float printRowY = dims.get("y1Display") + (fontSize + 3) * (i - displayOffs) + fontSize;
				gui.text(data.get(i), dims.get("x1Display") + printMargin[0], printRowY + printMargin[1]);
			}
		}
	}
}
