package com.cohesion.models;

import java.util.HashMap;
import java.util.Map;

import com.cohesion.entities.Color;
import com.cohesion.entities.Snake;
import com.cohesion.entities.User;
import com.cohesion.services.Encryption;
import com.cohesion.services.KeyService;
import com.cohesion.services.ScoreService;
import com.cohesion.services.UserService;
import com.cohesion.threads.Backend;
import com.cohesion.threads.GameTick;

import processing.core.PFont;

public class Global {

	// SCENES
	public static Map<String, Scene> login = new HashMap<>();
	public static Map<String, Scene> scoreBoard = new HashMap<>();
	public static Map<String, Scene> game = new HashMap<>();
	public static Map<String, Scene> editProfile = new HashMap<>();

	public static final int LOGIN = 1;
	public static final int GAME_STANDBY = 10;
	public static final int GAME_ON = 11;
	public static final int GAME_OVER = 12;
	public static final int SCORE_BOARD = 20;
	public static final int EDIT_PROFILE = 30;
	public static final Color BACK_COLOR = new Color(41, 10, 45);
	public static final Color DEF_SNAKE_BODY_COLOR = new Color(80, 200, 163);
	public static final Color DEF_SNAKE_HEAD_COLOR = new Color(80, 245, 196);
	public static final String ROOT_DIR = System.getProperty("user.dir").replace("\\", "/") + "/";

	public static boolean exitGame = false;
	public static Snake editSnake;

	// Models
	public static SnakeModel snakeModel;
	public static FruitModel fruitModel;
	public static int showScene = LOGIN;
	public static PFont FONT_REG = new PFont();
	public static Sound sound;
	public static InputBox loginField;

	// SERVICES
	public static ScoreService scoreService = new ScoreService();
	public static UserService userService = new UserService();
	public static User user = new User();
	public static KeyService keyService = new KeyService();

	// CIFER
	public static Encryption encryption = new Encryption();

	// THREADS
	public static GameTick gameTick = new GameTick(12);
	public static Backend backend = new Backend();
}
