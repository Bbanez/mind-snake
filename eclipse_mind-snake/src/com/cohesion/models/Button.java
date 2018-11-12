package com.cohesion.models;

import java.util.HashMap;
import java.util.Map;

import com.cohesion.entities.Color;
import com.cohesion.entities.Point;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button {

	private PApplet p;

	private Map<String, Float> dims;
	private Map<String, Color> colors;
	private String text = "";
	private int textSize = 12;
	private boolean active = false;
	private boolean activeLatch = false;

	public Button(PApplet p) {
		dims = new HashMap<>();
		colors = new HashMap<>();
		this.p = p;
	}

	public Button(PApplet p, float width, float height, Point position) {
		dims = new HashMap<>();
		colors = new HashMap<>();
		this.p = p;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
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

	public void reset() {
		active = false;
	}

	public void display() {
		p.noStroke();
		p.rectMode(PConstants.CORNERS);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.textSize(textSize);

		if (p.mouseX > dims.get("x1Display") && p.mouseX < dims.get("x2Display") && p.mouseY > dims.get("y1Display")
				&& p.mouseY < dims.get("y2Display")) {

			if (p.mousePressed && !activeLatch) {
				activeLatch = true;
			} else if (!p.mousePressed && activeLatch) {
				activeLatch = false;
				active = true;
			}

			if (!active) {
				p.fill(colors.get("hover").getRgb().get(0), 
						colors.get("hover").getRgb().get(1),
						colors.get("hover").getRgb().get(2));
				p.rect(dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"), dims.get("y2Display"));

				p.fill(colors.get("textHover").getRgb().get(0), 
						colors.get("textHover").getRgb().get(1),
						colors.get("textHover").getRgb().get(2));
				p.text(text, dims.get("xCenter"), dims.get("yCenter"));
			}
		} else {
			if (!active) {
				p.fill(colors.get("primary").getRgb().get(0), 
						colors.get("primary").getRgb().get(1),
						colors.get("primary").getRgb().get(2));
				p.rect(dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"), dims.get("y2Display"));

				p.fill(colors.get("textActive").getRgb().get(0), 
						colors.get("textActive").getRgb().get(1),
						colors.get("textActive").getRgb().get(2));
				p.text(text, dims.get("xCenter"), dims.get("yCenter"));
			}
		}

		if (active) {
			p.fill(colors.get("active").getRgb().get(0), 
					colors.get("active").getRgb().get(1),
					colors.get("active").getRgb().get(2));
			p.rect(dims.get("x1Display"), dims.get("y1Display"), dims.get("x2Display"), dims.get("y2Display"));

			p.fill(colors.get("textActive").getRgb().get(0), 
					colors.get("textActive").getRgb().get(1),
					colors.get("textActive").getRgb().get(2));
			p.text(text, dims.get("xCenter"), dims.get("yCenter"));
		}
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public String toString() {
		return "Button [p=" + p + ", dims=" + dims + ", colors=" + colors + ", text=" + text + ", textSize=" + textSize
				+ ", active=" + active + "]";
	}
}
