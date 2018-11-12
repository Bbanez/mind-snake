package com.cohesion.models;

import java.util.HashMap;
import java.util.Map;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Sound {

	private static Minim minim;
	private static Map<String, AudioPlayer> players;

	public Sound(PApplet p) {
		minim = new Minim(p);
		players = new HashMap<>();
	}

	public void init() throws Exception {
		players.put("deep_in_mind", minim.loadFile(Global.ROOT_DIR + "resources/audio/deep_in_mind.mp3"));
		players.put("heart", minim.loadFile(Global.ROOT_DIR + "resources/audio/heart.mp3"));
		players.put("lags", minim.loadFile(Global.ROOT_DIR + "resources/audio/lags.mp3"));
		players.put("thought_0", minim.loadFile(Global.ROOT_DIR + "resources/audio/thought_1.mp3"));
		players.put("thought_1", minim.loadFile(Global.ROOT_DIR + "resources/audio/thought_2.mp3"));
		players.put("thought_2", minim.loadFile(Global.ROOT_DIR + "resources/audio/thought_3.mp3"));
		players.put("thought_3", minim.loadFile(Global.ROOT_DIR + "resources/audio/thought_4.mp3"));
	}

	public AudioPlayer getPlayer(String name) throws Exception {
		return players.get(name);
	}

	public Map<String, AudioPlayer> getPlayers() {
		return players;
	}
}
