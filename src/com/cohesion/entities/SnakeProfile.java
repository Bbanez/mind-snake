package com.cohesion.entities;

import com.cohesion.entities.Color;

public class SnakeProfile {

	private Color bodyColor;
	private Color headColor;

	public SnakeProfile() {
	}

	public SnakeProfile(Color bodyColor, Color headColor) {
		super();
		this.bodyColor = bodyColor;
		this.headColor = headColor;
	}

	public Color getBodyColor() {
		return bodyColor;
	}

	public void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
	}

	public Color getHeadColor() {
		return headColor;
	}

	public void setHeadColor(Color headColor) {
		this.headColor = headColor;
	}

	@Override
	public String toString() {
		return "SnakeProfile [bodyColor=" + bodyColor + ", headColor=" + headColor + "]";
	}
}
