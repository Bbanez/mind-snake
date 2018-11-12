package com.cohesion.models;

import java.awt.Color;

import com.cohesion.entities.Score;

import processing.core.PApplet;

public class BoardModel {

	private static final int TEXT_COLOR = new Color(233, 233, 233).getRGB();
	private PApplet p;
	private Grid grid;

	public BoardModel(PApplet p, Grid grid) {
		this.p = p;
		this.grid = grid;
	}

	public void display() {
		p.fill(TEXT_COLOR);
		p.textAlign(PApplet.LEFT);
		p.textSize(30);
		p.text(Global.user.getName(), grid.getPoint(1, 2).getX(), grid.getPoint(1, 2).getY());
		p.text("Score Board", grid.getPoint(20, 2).getX(), grid.getPoint(20, 2).getY());
		p.textSize(18);
		p.text("Name", grid.getPoint(20, 5).getX(), grid.getPoint(20, 5).getY());
		p.text("Score", grid.getPoint(30, 5).getX(), grid.getPoint(30, 5).getY());
		p.textSize(14);
		p.text("Games: " + Global.user.getGame_count(), grid.getPoint(1, 4).getX(), grid.getPoint(1, 4).getY());
		p.text("Play Time: " + Global.user.getPlay_time(), grid.getPoint(1, 5).getX(), grid.getPoint(1, 5).getY());
		p.text("Best Score: " + Global.user.getMax_score().getScore(), grid.getPoint(1, 6).getX(),
				grid.getPoint(1, 6).getY());
		int i = 7;
		int j = 1;
		for (Score score : Global.scoreService.getBestByScore(25)) {
			p.text(j + ".", grid.getPoint(20, i).getX(), grid.getPoint(20, i).getY());
			p.text(score.getName(), grid.getPoint(22, i).getX(), grid.getPoint(22, i).getY());
			p.text(score.getScore(), grid.getPoint(30, i).getX(), grid.getPoint(30, i).getY());
			i++;
			j++;
		}
	}

}
