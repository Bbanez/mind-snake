package com.cohesion.entities;

import java.util.ArrayList;
import java.util.List;

public class Color {

	private int color;
	private List<Integer> rgb;

	public Color() {
		rgb = new ArrayList<>();
	}

	public Color(int color, List<Integer> rgb) {
		super();
		this.color = color;
		this.rgb = rgb;
	}

	public Color(String hexColor) {
		rgb = new ArrayList<>();
		try {
			color = Integer.parseInt(hexColor, 16);
		} catch (Exception e) {
			e.printStackTrace();
			color = 0;
		}
		rgb.add((color & 0xff0000) >> 16);
		rgb.add((color & 0x00ff00) >> 8);
		rgb.add((color & 0x0000ff));
		color += 255 << 24;
	}

	public Color(int r, int g, int b) {
		rgb = new ArrayList<>();
		rgb.add(r << 16);
		rgb.add(g << 8);
		rgb.add(b);
		color = rgb.get(0) + rgb.get(1) + rgb.get(2);
		color += 255 << 24;
	}

	public void set(String hexColor) {
		try {
			color = Integer.parseInt(hexColor, 16);
		} catch (Exception e) {
			e.printStackTrace();
			color = 0;
		}
		rgb.add((color & 0xff0000) >> 16);
		rgb.add((color & 0x00ff00) >> 8);
		rgb.add((color & 0x0000ff));
		color += 255 << 24;
	}

	public void set(int r, int g, int b) {
		rgb.add(r << 16);
		rgb.add(g << 8);
		rgb.add(b);
		color = rgb.get(0) + rgb.get(1) + rgb.get(2);
		color += 255 << 24;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public List<Integer> getRgb() {
		return rgb;
	}

	public void setRgb(List<Integer> rgb) {
		this.rgb = rgb;
	}

	public String toHex()	{
		try {
			return Integer.toHexString((color & 0xffffff));
		} catch (Exception e) {
			e.printStackTrace();
			return "000000";
		}
	}
	
	@Override
	public String toString() {
		return "Color [color=" + color + ", rgb=" + rgb + "]";
	}
}
