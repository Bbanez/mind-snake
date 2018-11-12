package com.cohesion.models;

import java.awt.Color;

import com.cohesion.entities.Fruit;

import processing.core.PApplet;

public class FruitModel {

	private PApplet p;
	private Fruit fruit;
	private Grid grid;
	private int COLOR = new Color(240, 212, 56).getRGB();

	public FruitModel(PApplet p, Grid grid) {
		this.p = p;
		this.grid = grid;
		fruit = new Fruit(grid.getPoint((int) p.random(2, grid.getCols() - 2), (int) p.random(2, grid.getRows() - 2)),
				9);
	}

	public Fruit getFruit() {
		return fruit;
	}

	public void generateNewFruit() {
		fruit = new Fruit(grid.getPoint((int) p.random(2, grid.getCols() - 2), (int) p.random(2, grid.getRows() - 2)),
				9);
	}

	public void display() {
		p.noStroke();
		p.fill(COLOR);
		p.ellipse(fruit.getPosition().getX(), fruit.getPosition().getY(), fruit.getSize(), fruit.getSize());
	}
}
