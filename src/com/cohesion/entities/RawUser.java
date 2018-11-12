package com.cohesion.entities;

public class RawUser {

	private String id;
	private String name;
	private long created_at;
	private int game_count;
	private long play_time;
	private String max_score_id;
	private SnakeProfile snake;

	public RawUser() {
	}

	public RawUser(User user) {
		id = user.getId();
		name = user.getName();
		created_at = user.getCreated_at();
		game_count = user.getGame_count();
		play_time = user.getPlay_time();
		max_score_id = user.getMax_score().getId();
		snake = user.getSnake();
	}

	public RawUser(String id, String name, long created_at, int game_count, long play_time, String max_score_id,
			SnakeProfile snake) {
		super();
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.game_count = game_count;
		this.play_time = play_time;
		this.max_score_id = max_score_id;
		this.snake = snake;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public int getGame_count() {
		return game_count;
	}

	public void setGame_count(int game_count) {
		this.game_count = game_count;
	}

	public long getPlay_time() {
		return play_time;
	}

	public void setPlay_time(long play_time) {
		this.play_time = play_time;
	}

	public String getMax_score_id() {
		return max_score_id;
	}

	public void setMax_score_id(String max_score_id) {
		this.max_score_id = max_score_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SnakeProfile getSnake() {
		return snake;
	}

	public void setSnake(SnakeProfile snake) {
		this.snake = snake;
	}

	@Override
	public String toString() {
		return "RawUser [id=" + id + ", name=" + name + ", created_at=" + created_at + ", game_count=" + game_count
				+ ", play_time=" + play_time + ", max_score_id=" + max_score_id + ", snake=" + snake + "]";
	}
}
