package com.cohesion.animations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.cohesion.entities.Point;

import processing.core.PApplet;
import processing.core.PImage;

public class Planets {

	private PApplet p;
	private int planetCount;
	private float t = 500;
	private List<Float> amp;
	private List<Float> freq;
	private List<Float> size;
	private List<Integer> color;
	private PImage backgroundImage;
	private Point center;

	public Planets(PApplet p, float maxRadious, int planetCount, Point center, String pathToImage) {
		this.p = p;
		this.center = center;
		this.planetCount = planetCount;

		backgroundImage = p.loadImage(pathToImage);

		amp = new ArrayList<>();
		freq = new ArrayList<>();
		size = new ArrayList<>();
		color = new ArrayList<>();

		int temp;
		float randAmp;
		for (int i = 0; i < planetCount; i++) {
			randAmp = p.random(10, maxRadious);
			amp.add(randAmp);
			if (randAmp < 50)
				freq.add((float) Math.random() / 2);
			else if (randAmp >= 50 && randAmp < 150)
				freq.add((float) Math.random() / 6);
			else if (randAmp >= 150 && randAmp < 250)
				freq.add((float) Math.random() / 10);
			else
				freq.add((float) Math.random() / 12);
			size.add(p.random(1, 5));
			temp = (int) p.random(10, 50);
			color.add(new Color(56 + temp, 15 + temp, 65 + temp).getRGB());
		}
	}

	public void display() {
		t += 1f / p.frameRate;
		p.noStroke();
		for (int i = 0; i < planetCount; i++) {
			p.fill(color.get(i));
			p.ellipse(center.getX() + (float) (amp.get(i) * Math.cos(t * freq.get(i))),
					center.getY() + (float) (amp.get(i) * Math.sin(t * freq.get(i))), size.get(i), size.get(i));
		}
		p.pushMatrix();
		p.translate(center.getX(), center.getY());
		p.rotate(t / 37);
		p.tint(255, 40); // Display at half opacity
		p.imageMode(PApplet.CENTER);
		p.image(backgroundImage, 0, 0);
		p.popMatrix();
	}
}
