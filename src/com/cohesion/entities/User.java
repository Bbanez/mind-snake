package com.cohesion.entities;

public class User {

	private String id;
	private String name;
	private long created_at;
	private int game_count;
	private long play_time;
	private Score max_score;
	private SnakeProfile snake;

	public User() {
	}

	public User(RawUser user, Score score) {
		id = user.getId();
		name = user.getName();
		created_at = user.getCreated_at();
		game_count = user.getGame_count();
		play_time = user.getPlay_time();
		max_score = score;
		snake = user.getSnake();
	}

	public User(String id, String name, long created_at, int game_count, long play_time, Score max_score,
			SnakeProfile snake) {
		super();
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.game_count = game_count;
		this.play_time = play_time;
		this.max_score = max_score;
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

	public Score getMax_score() {
		return max_score;
	}

	public void setMax_score(Score max_score) {
		this.max_score = max_score;
	}

	public void combine(User u) {
		if (u.getCreated_at() != 0)
			created_at = u.getCreated_at();
		if (u.getGame_count() != 0)
			game_count = u.getGame_count();
		if (u.getMax_score() != null)
			max_score.combine(u.getMax_score());
		if (u.getPlay_time() != 0)
			play_time = u.getPlay_time();
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
		return "User [id=" + id + ", name=" + name + ", created_at=" + created_at + ", game_count=" + game_count
				+ ", play_time=" + play_time + ", max_score=" + max_score + ", snake=" + snake + "]";
	}
}
