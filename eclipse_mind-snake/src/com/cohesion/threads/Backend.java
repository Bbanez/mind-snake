package com.cohesion.threads;

import com.cohesion.entities.Color;
import com.cohesion.entities.Score;
import com.cohesion.models.Button;
import com.cohesion.models.Global;
import com.cohesion.models.InputField;
import com.cohesion.models.PlaneText;

public class Backend extends Thread {

	private boolean loop = true;

	public Backend() {

	}

	public void run() {
		while (loop) {
			switch (Global.showScene) {
				case Global.GAME_STANDBY: {
					for (String chiledId : Global.game.get("top_bar_standby").getChildrenIds()) {
						Object chiled = Global.game.get("top_bar_standby").getChiled(chiledId);
	
						if (chiled instanceof Button) {
							Button button = (Button) chiled;
							if (button.isActive()) {
								button.reset();
								if (chiledId.equals("score_button")) {
									Global.showScene = Global.SCORE_BOARD;
									int i = 0;
									int j = 1;
									for (Score score : Global.scoreService.getBestByScore(25)) {
										PlaneText user = (PlaneText) Global.scoreBoard.get("top_25_list")
												.getChiled("user_" + i);
										if (j > 9)
											user.setText(j + ". " + score.getName());
										else
											user.setText(j + ".  " + score.getName());
										Global.scoreBoard.get("top_25_list").updateChiled("user_" + i, user);
	
										PlaneText scoree = (PlaneText) Global.scoreBoard.get("top_25_list")
												.getChiled("user_" + i + "_score");
										scoree.setText("" + score.getScore());
										Global.scoreBoard.get("top_25_list").updateChiled("user_" + i + "_score", scoree);
	
										i++;
										j++;
									}
								} else if (chiledId.equals("edit_profile_button")) {
									Global.showScene = Global.EDIT_PROFILE;
								} else if (chiledId.equals("exit_game")) {
									Global.exitGame = true;
								} else if (chiledId.equals("home_button")) {
									Global.showScene = Global.GAME_STANDBY;
								}
							}
						}
					}
				}break;
				case Global.SCORE_BOARD: {
					for (String chiledId : Global.game.get("top_bar_standby").getChildrenIds()) {
						Object chiled = Global.game.get("top_bar_standby").getChiled(chiledId);
	
						if (chiled instanceof Button) {
							Button button = (Button) chiled;
							if (button.isActive()) {
								button.reset();
								if (chiledId.equals("score_button")) {
									Global.showScene = Global.SCORE_BOARD;
								} else if (chiledId.equals("edit_profile_button")) {
									Global.showScene = Global.EDIT_PROFILE;
								} else if (chiledId.equals("exit_game")) {
									Global.exitGame = true;
								} else if (chiledId.equals("home_button")) {
									Global.showScene = Global.GAME_STANDBY;
								}
							}
						}
					}
				}break;
				case Global.EDIT_PROFILE: {
					for (String chiledId : Global.game.get("top_bar_standby").getChildrenIds()) {
						Object chiled = Global.game.get("top_bar_standby").getChiled(chiledId);
	
						if (chiled instanceof Button) {
							Button button = (Button) chiled;
							if (button.isActive()) {
								button.reset();
								if (chiledId.equals("score_button")) {
									Global.showScene = Global.SCORE_BOARD;
									int i = 0;
									int j = 1;
									for (Score score : Global.scoreService.getBestByScore(25)) {
										PlaneText user = (PlaneText) Global.scoreBoard.get("top_25_list")
												.getChiled("user_" + i);
										if (j > 9)
											user.setText(j + ". " + score.getName());
										else
											user.setText(j + ".  " + score.getName());
										Global.scoreBoard.get("top_25_list").updateChiled("user_" + i, user);
	
										PlaneText scoree = (PlaneText) Global.scoreBoard.get("top_25_list")
												.getChiled("user_" + i + "_score");
										scoree.setText("" + score.getScore());
										Global.scoreBoard.get("top_25_list").updateChiled("user_" + i + "_score", scoree);
	
										i++;
										j++;
									}
								} else if (chiledId.equals("edit_profile_button")) {
									Global.showScene = Global.EDIT_PROFILE;
								} else if (chiledId.equals("exit_game")) {
									Global.exitGame = true;
								} else if (chiledId.equals("home_button")) {
									Global.showScene = Global.GAME_STANDBY;
								}
							}
						}
					}
					for(String chiledId : Global.editProfile.get("snake_info").getChildrenIds())	{
						Object chiled = Global.editProfile.get("snake_info").getChiled(chiledId);
						if (chiled instanceof InputField)	{
							InputField input = (InputField) chiled;
							if (chiledId.equals("body_color_value")
									&& input.getText().length() == 6
									&&!input.getText().equals(
											Global.editSnake.getBodyColor().toHex()))	{
								Global.editSnake.setBodyColor(new Color(input.getText()));
							}else if (chiledId.equals("head_color_value")
									&& input.getText().length() == 6
									&&!input.getText().equals(
											Global.editSnake.getHeadColor().toHex()))	{
								Global.editSnake.setHeadColor(new Color(input.getText()));
							}
						}else if (chiled instanceof Button)	{
							Button button = (Button) chiled;
							if(button.isActive())	{
								button.reset();
								if(chiledId.equals("update_button"))	{
									Global.user.getSnake().setHeadColor(Global.editSnake.getHeadColor());
									Global.user.getSnake().setBodyColor(Global.editSnake.getBodyColor());
									Global.snakeModel.setBodyColor(Global.editSnake.getBodyColor());
									Global.snakeModel.setHeadColor(Global.editSnake.getHeadColor());
									try {
										Global.userService.update(Global.user);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				}break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopThred() {
		loop = false;
	}

	public boolean isRunning() {
		return loop;
	}
}
