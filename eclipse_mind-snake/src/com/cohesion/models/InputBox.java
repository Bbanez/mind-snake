package com.cohesion.models;

import java.util.ArrayList;
import java.util.List;

import com.cohesion.entities.Point;

import processing.core.PApplet;

public class InputBox {

	public static final int TYPE_TEXT = 1;
	public static final int TYPE_NUMBER = 2;
	public static final int TYPE_PASS = 3;

	private static final int BACK_COLOR = new java.awt.Color(22, 15, 56).getRGB();
	private static final int BORDER_COLOR = new java.awt.Color(20, 5, 34).getRGB();
	private static final int TEXT_COLOR = new java.awt.Color(233, 233, 233).getRGB();

	private PApplet p;

	private Point position;
	private float width, height;
	private List<Character> data;
	private String dataAsString = "";

	public InputBox(PApplet p) {
		this.p = p;
		data = new ArrayList<>();
	}

	public InputBox(PApplet p, Point position, float width, float height) {
		this.p = p;
		this.position = position;
		this.width = width;
		this.height = height;
		data = new ArrayList<>();
	}

	public String getData() {
		return dataAsString;
	}

	public void append(char key) {
		switch (key) {
		case 8: {
			if (data.size() > 0)
				data.remove(data.size() - 1);
		}
			break;
		default: {
			data.add(key);
		}
			break;
		}
		dataAsString = "";
		data.stream().forEach(e -> {
			dataAsString += e;
		});
	}

	public void display() {
		p.stroke(BORDER_COLOR);
		p.strokeWeight(3);
		p.fill(BACK_COLOR);
		p.rectMode(PApplet.CENTER);
		p.rect(position.getX(), position.getY(), width, height);
		p.fill(TEXT_COLOR);
		p.textAlign(PApplet.CENTER, PApplet.CENTER);
		p.textSize(20);
		p.text(dataAsString, position.getX(), position.getY());
	}
}
