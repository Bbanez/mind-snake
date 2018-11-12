package com.cohesion.entities;

import java.awt.Color;

import processing.core.PApplet;

public class Fruit {

	private Point position;
	private int size;
	private int COLOR = new Color(240, 212, 56).getRGB();

	public Fruit() {
	}

	public Fruit(Point position, int size) {
		super();
		this.position = position;
		this.size = size;
	}

	public Fruit(int atX, int atY, int size) {
		super();
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void display(PApplet p) {
		p.noStroke();
		p.fill(COLOR);
		p.ellipse(position.getX(), position.getY(), size, size);
	}

	@Override
	public String toString() {
		return "Fruit [position=" + position + ", size=" + size + "]";
	}
}
