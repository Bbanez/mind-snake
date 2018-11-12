package com.cohesion.threads;

import java.util.Date;

import com.cohesion.animations.Planets;
import com.cohesion.entities.Point;
import com.cohesion.entities.Score;
import com.cohesion.entities.Snake;
import com.cohesion.entities.SnakeProfile;
import com.cohesion.entities.User;
import com.cohesion.exceptions.CustomException;
import com.cohesion.models.Button;
import com.cohesion.entities.Color;
import com.cohesion.models.FruitModel;
import com.cohesion.models.Global;
import com.cohesion.models.InputField;
import com.cohesion.models.PlaneText;
import com.cohesion.models.Scene;
import com.cohesion.models.SnakeModel;
import com.cohesion.models.Sound;
import com.cohesion.services.ScoreService;
import com.cohesion.services.UserService;
import com.jogamp.common.util.InterruptSource.Thread;

import processing.core.PApplet;

public class MindSnake extends PApplet {

	private Planets planetAnimation;

	public static void main(String[] args) {
		PApplet.main("com.cohesion.threads.MindSnake");
	}

	public void settings() {
//		size(700, 700, P2D);
		fullScreen(P2D);
	}

	public void setup() {
		settings();
		frameRate(30);
		noStroke();
		Global.FONT_REG = createFont(Global.ROOT_DIR + "resources/fonts/MesloLGSDZ-Regular.ttf", 72);
		textFont(Global.FONT_REG);

		// -------------------------------------------------------------------------------
		// Initialize DATABASE services
		// -------------------------------------------------------------------------------
		Global.scoreService = new ScoreService();
		try {
			Global.scoreService.load(Global.ROOT_DIR);
//			Global.scoreService.findAll().stream().forEach(e -> System.out.println(e));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Global.userService = new UserService();
		try {
			Global.userService.load(Global.ROOT_DIR);
//			Global.userService.findAll().stream().forEach(e -> System.out.println(e));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// -------------------------------------------------------------------------------

		// -------------------------------------------------------------------------------
		// Initialize SOUNDS
		// -------------------------------------------------------------------------------
		Global.sound = new Sound(this);
		try {
			Global.sound.init();
			Global.sound.getPlayer("deep_in_mind").loop();
		} catch (Exception e1) {
			System.out.println("Was not able to load sound/s.");
			e1.printStackTrace();
		}
		// -------------------------------------------------------------------------------

		// -------------------------------------------------------------------------------
		// Initialize SCENES
		// -------------------------------------------------------------------------------
		initLoginScenes();
		initGameStandbyTopBarScene();
		initGameOnTopBarScene();
		initGameAreaScene();
		initGameOverScene();
		initScoreBoardScene();
		// -------------------------------------------------------------------------------

		// -------------------------------------------------------------------------------
		// Initialize ANIMATIONS
		// -------------------------------------------------------------------------------
		if(width < height)	{
			planetAnimation = new Planets(this, height / 2, 300, new Point(width / 2, height / 2),
					Global.ROOT_DIR + "resources/imgs/nebula_small.png");
		}else	{
			planetAnimation = new Planets(this, width / 2, 300, new Point(width / 2, height / 2),
					Global.ROOT_DIR + "resources/imgs/nebula_small.png");
		}
		// -------------------------------------------------------------------------------
		
		Global.backend.start();
		Global.gameTick.start();
	}

	public void draw() {
		background(Global.BACK_COLOR.getColor());
		planetAnimation.display();
		switch (Global.showScene) {
		case Global.LOGIN: {
			Global.login.get("form").display();
		}
			break;
		case Global.GAME_STANDBY: {
			Global.game.get("top_bar_standby").display();
			Global.snakeModel.display();
			Global.fruitModel.display();
			Global.game.get("game_info_standby").display();
		}
			break;
		case Global.GAME_ON: {
			Global.game.get("top_bar_game_on").display();
			Global.snakeModel.display();
			Global.fruitModel.display();
		}
			break;
		case Global.GAME_OVER: {
			fill(48, 15, 20, 70f);
			rect(0, 0, width, height);
			Global.game.get("game_over").display();
			Global.snakeModel.display();
			Global.fruitModel.display();
		}
			break;
		case Global.SCORE_BOARD: {
			Global.game.get("top_bar_standby").display();
			Global.scoreBoard.get("top_25_title").display();
			Global.scoreBoard.get("top_25_list").display();
		}
			break;
		case Global.EDIT_PROFILE: {
			Global.game.get("top_bar_standby").display();
			Global.editProfile.get("personal_info").display();
			Global.editProfile.get("snake_info").display();
			Global.editSnake.display();
		}
			break;
		}
		
		if (Global.exitGame) {
			Global.gameTick.stopThread();
			Global.backend.stopThred();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			exit();
		}
	}

	public void keyPressed() {
		switch (Global.showScene) {
		case Global.LOGIN: {
			if (key != 10) {
				InputField input = (InputField) Global.login.get("form").getChiled("input");
				if (input.isFocus()) {
					input.appendKey(key);
					Global.login.get("form").updateChiled("input", input);
				}
			} else {
				InputField input = (InputField) Global.login.get("form").getChiled("input");
				try {
					Global.user = Global.userService.findByName(input.getText());
				} catch (Exception e) {
					e.printStackTrace();
					Global.user = new User(null, input.getText(), new Date().getTime(), 0, 0, new Score(),
							new SnakeProfile(Global.DEF_SNAKE_BODY_COLOR, Global.DEF_SNAKE_HEAD_COLOR));
					try {
						Global.userService.add(Global.user);
						Global.user = Global.userService.lastEntry();
					} catch (Exception e1) {
						e1.printStackTrace();
						return;
					}
				}

				Global.showScene = Global.GAME_STANDBY;
				Global.snakeModel.setBodyColor(Global.user.getSnake().getBodyColor());
				Global.snakeModel.setHeadColor(Global.user.getSnake().getHeadColor());
				System.out.println("Hello " + Global.user.getName() + "!");

				// --------------------------
				// Add username to top bar
				// of the game.
				// --------------------------
				PlaneText text = new PlaneText(this, Global.game.get("top_bar_standby").getGrid().getxSpace(),
						Global.game.get("top_bar_standby").getGrid().getySpace(),
						Global.game.get("top_bar_standby").getGrid().getPoint(0));
				text.setText("User: " + Global.user.getName());
				text.setTextSize(14);
				text.setTextMode(LEFT);
				text.paddingLeft(10);
				text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
				Global.game.get("top_bar_standby").addChiled("user", text);
				initProfileScene();
				// --------------------------
			}
		}
			break;
		case Global.GAME_STANDBY: {
			if (key == 'q') {
				Global.showScene = Global.GAME_ON;
				Global.gameTick.startTicker();
			}
		}
			break;
		case Global.GAME_ON: {
			if (!Global.gameTick.getKeyQueue()) {
				if (key == CODED) {
					switch (keyCode) {
						case UP: {
							if (Global.snakeModel.getSnake().getDirection() != Snake.DOWN) {
								Global.snakeModel.getSnake().setDirection(Snake.UP);
								Global.gameTick.setKeyQueue();
							}
						}break;
						case DOWN: {
							if (Global.snakeModel.getSnake().getDirection() != Snake.UP) {
								Global.snakeModel.getSnake().setDirection(Snake.DOWN);
								Global.gameTick.setKeyQueue();
							}
						}break;
						case LEFT: {
							if (Global.snakeModel.getSnake().getDirection() != Snake.RIGHT) {
								Global.snakeModel.getSnake().setDirection(Snake.LEFT);
								Global.gameTick.setKeyQueue();
							}
						}break;
						case RIGHT: {
							if (Global.snakeModel.getSnake().getDirection() != Snake.LEFT) {
								Global.snakeModel.getSnake().setDirection(Snake.RIGHT);
								Global.gameTick.setKeyQueue();
							}
						}break;
					}
				} else {
					switch (key) {
						case 'w': {
							if (Global.snakeModel.getSnake().getDirection() != Snake.DOWN) {
								Global.snakeModel.getSnake().setDirection(Snake.UP);
								Global.gameTick.setKeyQueue();
							}
						}break;
						case 's': {
							if (Global.snakeModel.getSnake().getDirection() != Snake.UP) {
								Global.snakeModel.getSnake().setDirection(Snake.DOWN);
								Global.gameTick.setKeyQueue();
							}
						}break;
						case 'a': {
							if (Global.snakeModel.getSnake().getDirection() != Snake.RIGHT) {
								Global.snakeModel.getSnake().setDirection(Snake.LEFT);
								Global.gameTick.setKeyQueue();
							}
						}break;
						case 'd': {
							if (Global.snakeModel.getSnake().getDirection() != Snake.LEFT) {
								Global.snakeModel.getSnake().setDirection(Snake.RIGHT);
								Global.gameTick.setKeyQueue();
							}
						}break;
					}
				}
			}
		}break;
		case Global.GAME_OVER: {
			if (key == 'q') {
				Global.showScene = Global.GAME_STANDBY;
				Global.snakeModel = new SnakeModel(this, Global.game.get("game_area").getGrid());
				Global.snakeModel.setBodyColor(Global.user.getSnake().getBodyColor());
				Global.snakeModel.setHeadColor(Global.user.getSnake().getHeadColor());
				Global.fruitModel = new FruitModel(this, Global.game.get("game_area").getGrid());
				PlaneText text = (PlaneText) Global.game.get("top_bar_game_on").getChiled("tick_value");
				text.setText(Global.gameTick.getTickSpeed() + "[tps]");
				Global.game.get("top_bar_game_on").updateChiled("tick_value", text);
			}
		}break;
		case Global.EDIT_PROFILE: {
			for(String chiledId : Global.editProfile.get("snake_info").getChildrenIds())	{
				Object chiled = Global.editProfile.get("snake_info").getChiled(chiledId);
				if(chiled instanceof InputField)	{
					InputField input = (InputField) chiled;
					if(input.isFocus())	{
						input.appendKey(key);
						Global.editProfile.get("snake_info").updateChiled(chiledId, input);
					}
				}
			}
		}break;
		}
	}

	// -------------------------------------------------------------------------------
	// Initialize LOGIN scenes
	// -------------------------------------------------------------------------------
	public void initLoginScenes() {
		Global.login.put("form", new Scene(this, 300, 100, new Point(width / 2 - 150, height / 2 - 50)));
		Global.login.get("form").setGrid(1, 2);

		InputField input = new InputField(this);
		input.setBackgroundColor(new Color("160f38"));
		input.setColors(new Color("160f38"), new Color("140522"), new Color("140522"), new Color("e9e9e9"),
				new Color("e9e9e9"), new Color("e9e9e9"));
		input.setFocus();
		input.setTextSize(24);
		try {
			Global.login.get("form").appendChiled("input", input);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		PlaneText text = new PlaneText(this, "Type your name and press ENTER.");
		text.setTextSize(14);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.login.get("form").appendChiled("text", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------
	// Initialize TOP BAR scene for STANDBAY
	// -------------------------------------------------------------------------------
	public void initGameStandbyTopBarScene() {
		Scene topBar = new Scene(this, width, 30, new Point(0, 0));
		topBar.setGrid(10, 1);
		topBar.setColor("160f38");
		Global.game.put("top_bar_standby", topBar);

		// Go to score board button
		Button homeButton = new Button(this, Global.game.get("top_bar_standby").getGrid().getxSpace(),
				Global.game.get("top_bar_standby").getGrid().getySpace(),
				Global.game.get("top_bar_standby").getGrid().getPoint(6));
		homeButton.setText("HOME");
		homeButton.setColors(new Color("3d234a"), new Color("5b2b73"), new Color("5b2b73"), new Color("eeeeee"),
				new Color("eeeeee"), new Color("eeeeee"));
		homeButton.padding(10);
		homeButton.setTextSize(12);
		Global.game.get("top_bar_standby").addChiled("home_button", homeButton);

		// Go to score board button
		Button scoreButton = new Button(this, Global.game.get("top_bar_standby").getGrid().getxSpace(),
				Global.game.get("top_bar_standby").getGrid().getySpace(),
				Global.game.get("top_bar_standby").getGrid().getPoint(7));
		scoreButton.setText("SCORES");
		scoreButton.setColors(new Color("3d234a"), new Color("5b2b73"), new Color("5b2b73"), new Color("eeeeee"),
				new Color("eeeeee"), new Color("eeeeee"));
		scoreButton.padding(10);
		scoreButton.setTextSize(12);
		Global.game.get("top_bar_standby").addChiled("score_button", scoreButton);

		// Go to profile button
		Button editProfileButton = new Button(this, Global.game.get("top_bar_standby").getGrid().getxSpace(),
				Global.game.get("top_bar_standby").getGrid().getySpace(),
				Global.game.get("top_bar_standby").getGrid().getPoint(8));
		editProfileButton.setText("PROFILE");
		editProfileButton.setColors(new Color("3d234a"), new Color("5b2b73"), new Color("5b2b73"), new Color("eeeeee"),
				new Color("eeeeee"), new Color("eeeeee"));
		editProfileButton.padding(10);
		editProfileButton.setTextSize(12);
		Global.game.get("top_bar_standby").addChiled("edit_profile_button", editProfileButton);

		// Go to profile button
		Button exitGame = new Button(this, Global.game.get("top_bar_standby").getGrid().getxSpace(),
				Global.game.get("top_bar_standby").getGrid().getySpace(),
				Global.game.get("top_bar_standby").getGrid().getPoint(9));
		exitGame.setText("EXIT");
		exitGame.setColors(new Color("3d234a"), new Color("5b2b73"), new Color("5b2b73"), new Color("eeeeee"),
				new Color("eeeeee"), new Color("eeeeee"));
		exitGame.padding(10);
		exitGame.setTextSize(12);
		Global.game.get("top_bar_standby").addChiled("exit_game", exitGame);
	}

	// -------------------------------------------------------------------------------
	// Initialize TOP BAR for GAME ON
	// -------------------------------------------------------------------------------
	public void initGameOnTopBarScene() {
		Scene topBar = new Scene(this, width, 30, new Point(0, 0));
		topBar.setGrid(10, 1);
		topBar.setColor("160f38");
		Global.game.put("top_bar_game_on", topBar);

		// TICK SPEED
		PlaneText text = new PlaneText(this, Global.game.get("top_bar_game_on").getGrid().getxSpace(),
				Global.game.get("top_bar_game_on").getGrid().getySpace(),
				Global.game.get("top_bar_game_on").getGrid().getPoint(1));
		text.setText("Tick: ");
		text.setTextSize(14);
		text.setTextMode(RIGHT);
		text.paddingLeft(10);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.game.get("top_bar_game_on").addChiled("tick_text", text);

		text = new PlaneText(this, Global.game.get("top_bar_game_on").getGrid().getxSpace(),
				Global.game.get("top_bar_game_on").getGrid().getySpace(),
				Global.game.get("top_bar_game_on").getGrid().getPoint(2));
		text.setText("" + Global.gameTick.getTickSpeed() + "[tps]");
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.paddingLeft(10);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.game.get("top_bar_game_on").addChiled("tick_value", text);

		// TIME IN GAME
		text = new PlaneText(this, Global.game.get("top_bar_game_on").getGrid().getxSpace(),
				Global.game.get("top_bar_game_on").getGrid().getySpace(),
				Global.game.get("top_bar_game_on").getGrid().getPoint(4));
		text.setText("Time: ");
		text.setTextSize(14);
		text.setTextMode(RIGHT);
		text.paddingLeft(10);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.game.get("top_bar_game_on").addChiled("time_text", text);

		text = new PlaneText(this, Global.game.get("top_bar_game_on").getGrid().getxSpace(),
				Global.game.get("top_bar_game_on").getGrid().getySpace(),
				Global.game.get("top_bar_game_on").getGrid().getPoint(5));
		text.setText("" + Global.gameTick.getTimeInGame() + "[s]");
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.paddingLeft(10);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.game.get("top_bar_game_on").addChiled("time_value", text);

		// SCORE
		text = new PlaneText(this, Global.game.get("top_bar_game_on").getGrid().getxSpace(),
				Global.game.get("top_bar_game_on").getGrid().getySpace(),
				Global.game.get("top_bar_game_on").getGrid().getPoint(7));
		text.setText("Score: ");
		text.setTextSize(14);
		text.setTextMode(RIGHT);
		text.paddingLeft(10);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.game.get("top_bar_game_on").addChiled("score_text", text);

		text = new PlaneText(this, Global.game.get("top_bar_game_on").getGrid().getxSpace(),
				Global.game.get("top_bar_game_on").getGrid().getySpace(),
				Global.game.get("top_bar_game_on").getGrid().getPoint(8));
		text.setText("" + Global.gameTick.getScore());
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.paddingLeft(10);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.game.get("top_bar_game_on").addChiled("score_value", text);
	}

	// -------------------------------------------------------------------------------
	// Initialize GAME AREA
	// -------------------------------------------------------------------------------
	public void initGameAreaScene() {
		Scene gameArea = new Scene(this, 650, 650, new Point(width / 2 - 325, height / 2 - 310));
		gameArea.setGrid(100, 100);
		Global.game.put("game_area", gameArea);
		Global.snakeModel = new SnakeModel(this, gameArea.getGrid());
		Global.fruitModel = new FruitModel(this, gameArea.getGrid());

		// Standby text
		Scene gameInfo = new Scene(this, 300, 100, new Point(width / 2 - 150, height / 2 - 50));
		gameInfo.setGrid(1, 2);
		Global.game.put("game_info_standby", gameInfo);

		PlaneText text = new PlaneText(this, "Press Q on keyboard to start the game...");
		text.setTextSize(20);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.game.get("game_info_standby").appendChiled("standby_text", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------
	// Initialize GAME OVER scenes
	// -------------------------------------------------------------------------------
	public void initGameOverScene() {
		Scene gameOver = new Scene(this, 400, 400, new Point(width / 2 - 200, height / 2 - 200));
		gameOver.setGrid(1, 8);
		Global.game.put("game_over", gameOver);

		PlaneText text = new PlaneText(this, "GAME SCORE");
		text.setTextSize(24);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.game.get("game_over").appendChiled("title", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		text = new PlaneText(this, "User: ");
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.game.get("game_over").appendChiled("user", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		text = new PlaneText(this, "Score: ");
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.game.get("game_over").appendChiled("score", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		text = new PlaneText(this, "Time: ");
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.game.get("game_over").appendChiled("time", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		text = new PlaneText(this, "Date: ");
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.game.get("game_over").appendChiled("date", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		text = new PlaneText(this, Global.game.get("game_over").getGrid().getxSpace(),
				Global.game.get("game_over").getGrid().getySpace(), Global.game.get("game_over").getGrid().getPoint(7));
		text.setText("Press Q key to get back to Home page...");
		text.setTextSize(18);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.game.get("game_over").addChiled("info", text);
	}

	// -------------------------------------------------------------------------------
	// Initialize TOP 25
	// -------------------------------------------------------------------------------
	public void initScoreBoardScene() {
		Scene top25Title = new Scene(this, 400, 50, new Point(width / 2 - 200, height / 2 - 320));
		top25Title.setGrid(1, 1);
		Global.scoreBoard.put("top_25_title", top25Title);

		PlaneText text = new PlaneText(this, "TOP 25");
		text.setTextSize(30);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.scoreBoard.get("top_25_title").appendChiled("title", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		Scene top25List = new Scene(this, 400, 500, new Point(width / 2 - 200, height / 2 - 250));
		top25List.setGrid(2, 25);
		Global.scoreBoard.put("top_25_list", top25List);

		for (int i = 0; i < 25; i++) {
			text = new PlaneText(this, "");
			text.setTextSize(14);
			text.setTextMode(LEFT);
			text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
			try {
				Global.scoreBoard.get("top_25_list").appendChiled("user_" + i, text);
			} catch (CustomException e) {
				e.printStackTrace();
			}

			text = new PlaneText(this, "");
			text.setTextSize(14);
			text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
			try {
				Global.scoreBoard.get("top_25_list").appendChiled("user_" + i + "_score", text);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
	}

	// -------------------------------------------------------------------------------
	// Initialize PROFILE scenes
	// -------------------------------------------------------------------------------
	public void initProfileScene() {
		Scene personalInfo = new Scene(this, width / 2 - 40, height - 60, new Point(20, 40));
		personalInfo.setGrid(1, 25);
		Global.editProfile.put("personal_info", personalInfo);

		PlaneText text = new PlaneText(this, Global.user.getName());
		text.setTextSize(30);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.editProfile.get("personal_info").appendChiled("username", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		text = new PlaneText(this, Global.editProfile.get("personal_info").getGrid().getxSpace(),
				Global.editProfile.get("personal_info").getGrid().getySpace(),
				Global.editProfile.get("personal_info").getGrid().getPoint(2));
		text.setText("Games played: " + Global.user.getGame_count());
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.editProfile.get("personal_info").addChiled("game_count", text);

		text = new PlaneText(this, Global.editProfile.get("personal_info").getGrid().getxSpace(),
				Global.editProfile.get("personal_info").getGrid().getySpace(),
				Global.editProfile.get("personal_info").getGrid().getPoint(3));
		text.setText("Play time: " + Global.user.getPlay_time() + "[s]");
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.editProfile.get("personal_info").addChiled("play_time", text);

		text = new PlaneText(this, Global.editProfile.get("personal_info").getGrid().getxSpace(),
				Global.editProfile.get("personal_info").getGrid().getySpace(),
				Global.editProfile.get("personal_info").getGrid().getPoint(4));
		text.setText("Best score: " + Global.user.getMax_score().getScore());
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.editProfile.get("personal_info").addChiled("max_score", text);

		text = new PlaneText(this, Global.editProfile.get("personal_info").getGrid().getxSpace(),
				Global.editProfile.get("personal_info").getGrid().getySpace(),
				Global.editProfile.get("personal_info").getGrid().getPoint(5));
		text.setText("Created: " + new Date(Global.user.getCreated_at()));
		text.setTextSize(14);
		text.setTextMode(LEFT);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.editProfile.get("personal_info").addChiled("created_at", text);

		Scene snakeInfo = new Scene(this, width / 2 - 20, height - 60, new Point(width / 2 + 10, 40));
		snakeInfo.setGrid(1, 25);
		Global.editProfile.put("snake_info", snakeInfo);
		
		// EDIT SNAKE TITILE
		text = new PlaneText(this, "Edit Snake");
		text.setTextSize(30);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		try {
			Global.editProfile.get("snake_info").appendChiled("title", text);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		// Text for snake head
		text = new PlaneText(this, Global.editProfile.get("snake_info").getGrid().getxSpace(),
				Global.editProfile.get("snake_info").getGrid().getySpace(),
				Global.editProfile.get("snake_info").getGrid().getPoint(2));
		text.setText("Head color [HEX]");
		text.setTextSize(14);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.editProfile.get("snake_info").addChiled("head_color_text", text);
		
		// Input field for snake head color
		InputField input = new InputField(this, Global.editProfile.get("snake_info").getGrid().getxSpace(),
				Global.editProfile.get("snake_info").getGrid().getySpace(),
				Global.editProfile.get("snake_info").getGrid().getPoint(3));
		input.setText(Global.user.getSnake().getHeadColor().toHex());
		input.setBackgroundColor(new Color("160f38"));
		input.setColors(new Color("160f38"), new Color("140522"), new Color("140522"), new Color("e9e9e9"),
				new Color("e9e9e9"), new Color("e9e9e9"));
		input.setTextSize(14);
		input.padding(5, (int)Global.editProfile.get("snake_info").getGrid().getxSpace() - 100);
		Global.editProfile.get("snake_info").addChiled("head_color_value", input);
		
		// Text for snake body color
		text = new PlaneText(this, Global.editProfile.get("snake_info").getGrid().getxSpace(),
				Global.editProfile.get("snake_info").getGrid().getySpace(),
				Global.editProfile.get("snake_info").getGrid().getPoint(4));
		text.setText("Body color [HEX]");
		text.setTextSize(14);
		text.setColors(new Color("eeeeee"), new Color("eeeeee"), new Color("eeeeee"));
		Global.editProfile.get("snake_info").addChiled("body_color_text", text);
		
		// Input field for snake body color
		input = new InputField(this, Global.editProfile.get("snake_info").getGrid().getxSpace(),
				Global.editProfile.get("snake_info").getGrid().getySpace(),
				Global.editProfile.get("snake_info").getGrid().getPoint(5));
		input.setText(Global.user.getSnake().getBodyColor().toHex());
		input.setBackgroundColor(new Color("160f38"));
		input.setColors(new Color("160f38"), new Color("140522"), new Color("140522"), new Color("e9e9e9"),
				new Color("e9e9e9"), new Color("e9e9e9"));
		input.setTextSize(14);
		input.padding(5, (int)Global.editProfile.get("snake_info").getGrid().getxSpace() - 100);
		Global.editProfile.get("snake_info").addChiled("body_color_value", input);
		
		// Update changes for snake BUTTON
		Button button = new Button(this, Global.editProfile.get("snake_info").getGrid().getxSpace(),
				Global.editProfile.get("snake_info").getGrid().getySpace(),
				Global.editProfile.get("snake_info").getGrid().getPoint(7));
		button.setText("UPDATE");
		button.setColors(new Color("3d234a"), new Color("5b2b73"), new Color("5b2b73"), new Color("eeeeee"),
				new Color("eeeeee"), new Color("eeeeee"));
		button.padding(10);
		button.setTextSize(12);
		button.padding(0, (int)Global.editProfile.get("snake_info").getGrid().getxSpace() - 250);
		Global.editProfile.get("snake_info").addChiled("update_button", button);
		
		// Generate snake for edit preview
		Global.editSnake = new Snake(this, 
				3*width/4 - 25*5, 
				Global.editProfile.get("snake_info").getGrid().getPoint(6).getY() 
				+ Global.editProfile.get("snake_info").getGrid().getySpace()/2, 
				Snake.LEFT, 5, 5);
		Global.editSnake.generateParts(50);
	}
}
