package com.cohesion.threads;

import java.util.Date;
import java.util.Random;

import com.cohesion.entities.Score;
import com.cohesion.models.Global;
import com.cohesion.models.PlaneText;
import com.cohesion.models.SoftwareTimer;

public class GameTick extends Thread {

	private long startTickSpeed;
	private long tickTime;
	private long tickSpeed;
	private float timeInGame;
	private int score;
	private boolean running = false;
	private boolean pause = true;
	private SoftwareTimer tickIncTimer;
	private boolean keyQueue = false;

	public GameTick(long tickSpeed) {
		this.tickSpeed = tickSpeed;
		startTickSpeed = tickSpeed;
		tickTime = (long) (1000 / tickSpeed);
		timeInGame = 0;
		score = 0;
		running = true;
		pause = true;
		tickIncTimer = new SoftwareTimer();
	}

	public void run() {
		while (running) {
			if (!pause) {
				if (tickSpeed < 23) {
					if (!tickIncTimer.isStarted()) {
						tickIncTimer.start(45000);
					} else {
						if (tickIncTimer.check()) {
							tickIncTimer.stop();
							tickSpeed++;
							PlaneText text = (PlaneText) Global.game.get("top_bar_game_on").getChiled("tick_value");
							text.setText(tickSpeed + "[tps]");
							Global.game.get("top_bar_game_on").updateChiled("tick_value", text);
							setTickTime();
							try {
								Global.sound.getPlayer("heart").rewind();
								Global.sound.getPlayer("heart").play();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}

				if (Global.snakeModel.isEatingFruit(Global.fruitModel.getFruit())) {
					Global.fruitModel.generateNewFruit();
					score++;
					int rand = new Random().nextInt(3);
					PlaneText scoreValue = (PlaneText) Global.game.get("top_bar_game_on").getChiled("score_value");
					scoreValue.setText("" + score);
					Global.game.get("top_bar_game_on").updateChiled("score_value", scoreValue);
					try {
						Global.sound.getPlayer("thought_" + rand).rewind();
						Global.sound.getPlayer("thought_" + rand).play();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				Global.snakeModel.getSnake().calulatePosition();

				if (Global.snakeModel.isOut() || Global.snakeModel.isEnatingTail()) {
					tickIncTimer.stop();
					pause = true;
					try {
						Global.sound.getPlayer("lags").rewind();
						Global.sound.getPlayer("lags").play();
					} catch (Exception e) {
						e.printStackTrace();
					}

					updateGameInfo();

					Global.showScene = Global.GAME_OVER;
				}
				timeInGame += tickTime / 1000f;
				PlaneText timeInGameValue = (PlaneText) Global.game.get("top_bar_game_on").getChiled("time_value");
				timeInGameValue.setText("" + (int) timeInGame + "[s]");
				Global.game.get("top_bar_game_on").updateChiled("time_value", timeInGameValue);
			}
			keyQueue = false;
			try {
				Thread.sleep(tickTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean getKeyQueue() {
		return keyQueue;
	}

	public void setKeyQueue() {
		keyQueue = true;
	}

	public void stopThread() {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	public long getTickTime() {
		return tickTime;
	}

	public void setTickTime(long tickTime) {
		this.tickTime = tickTime;
	}

	public void setTickTime() {
		tickTime = (long) (1000 / tickSpeed);
	}

	public long getTickSpeed() {
		return tickSpeed;
	}

	public void setTickSpeed(long tickSpeed) {
		this.tickSpeed = tickSpeed;
	}

	public boolean isPause() {
		return pause;
	}

	public void startTicker() {
		this.pause = false;
		tickSpeed = startTickSpeed;
		setTickTime();
		score = 0;
		timeInGame = 0;
	}

	public int getScore() {
		return score;
	}

	public int getTimeInGame() {
		return (int) timeInGame;
	}

	@Override
	public String toString() {
		return "GameTick [tickTime=" + tickTime + ", tickSpeed=" + tickSpeed + ", running=" + running + "]";
	}

	private void updateGameInfo() {
		// ---------------------
		// Update scores and user
		// ---------------------
		Score scoreEntitiy = null;
		try {
			scoreEntitiy = new Score(null, Global.user.getName(), new Date().getTime(), score, (int) timeInGame);
			Global.scoreService.add(scoreEntitiy);
			scoreEntitiy = Global.scoreService.lastEntry();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (scoreEntitiy != null) {
			if (Global.user.getMax_score().getScore() < score) {
				Global.user.setMax_score(scoreEntitiy);
			}
		}
		Global.user.setGame_count(Global.user.getGame_count() + 1);
		Global.user.setPlay_time(Global.user.getPlay_time() + (int) timeInGame);
		if (scoreEntitiy.getScore() > Global.user.getMax_score().getScore()) {
			Global.user.setMax_score(scoreEntitiy);
		}
		try {
			Global.userService.update(Global.user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ---------------------

		// ---------------------
		// Update profile
		// ---------------------
		PlaneText text = (PlaneText) Global.editProfile.get("personal_info").getChiled("game_count");
		text.setText("Games played: " + Global.user.getGame_count());
		Global.editProfile.get("personal_info").updateChiled("game_count", text);

		text = (PlaneText) Global.editProfile.get("personal_info").getChiled("play_time");
		text.setText("Play time: " + Global.user.getPlay_time() + "[s]");
		Global.editProfile.get("personal_info").updateChiled("play_time", text);

		text = (PlaneText) Global.editProfile.get("personal_info").getChiled("max_score");
		text.setText("Best score: " + Global.user.getMax_score().getScore());
		Global.editProfile.get("personal_info").updateChiled("max_score", text);
		// ---------------------

		text = (PlaneText) Global.game.get("game_over").getChiled("user");
		text.setText("User: " + Global.user.getName());
		Global.game.get("game_over").updateChiled("user", text);

		text = (PlaneText) Global.game.get("game_over").getChiled("time");
		text.setText("Game time: " + (int) timeInGame + "[s]");
		Global.game.get("game_over").updateChiled("time", text);

		text = (PlaneText) Global.game.get("game_over").getChiled("date");
		text.setText("Date: " + new Date().toString());
		Global.game.get("game_over").updateChiled("date", text);

		text = (PlaneText) Global.game.get("game_over").getChiled("score");
		text.setText("Score: " + score);
		Global.game.get("game_over").updateChiled("score", text);
	}
}
