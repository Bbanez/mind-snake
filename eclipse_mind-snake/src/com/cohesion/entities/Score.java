package com.cohesion.entities;

public class Score implements Comparable<Score> {

	private String id;
	private String name;
	private long date;
	private int score;
	private int time_played;

	public Score() {
	}

	public Score(String id, String name, long date, int score, int time_played) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.score = score;
		this.time_played = time_played;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTime_played() {
		return time_played;
	}

	public void setTime_played(int time_played) {
		this.time_played = time_played;
	}

	public void combine(Score s) {
		if (s.getDate() != 0)
			date = s.getDate();
		if (s.getName() != null)
			name = s.getName();
		if (s.getScore() != 0)
			score = s.getScore();
		if (s.getTime_played() != 0)
			time_played = s.getTime_played();
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", name=" + name + ", date=" + date + ", score=" + score + ", time_played="
				+ time_played + "]";
	}

	@Override
	public int compareTo(Score comparestu) {
		int compareage = ((Score) comparestu).getScore();
		/* For Ascending order */
		return compareage - this.score;

		/* For Descending order do like this */
		// return compareage-this.studentage;
	}
}
