package com.cohesion.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cohesion.entities.Score;
import com.cohesion.models.FilesystemHandler;
import com.cohesion.models.RandomStringGen;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ScoreService {

	private static String ENTITIES_DB_URI = "resources/dat/scores.collection";
	private static Encryption encryption;
	private static FilesystemHandler filesystem;
	private static RandomStringGen strGen;
	private static List<Score> entities;

	public ScoreService() {
		filesystem = new FilesystemHandler();
		strGen = new RandomStringGen();
		encryption = new Encryption();
	}

	public void load(String projectRoot) throws Exception {
		ENTITIES_DB_URI = projectRoot + ENTITIES_DB_URI;
		String data = encryption.decrypt(filesystem.read(ENTITIES_DB_URI));
		if (data != null && !data.equals("")) {
			ObjectMapper mapper = new ObjectMapper();
			entities = mapper.readValue(data, new TypeReference<List<Score>>() {
			});
		} else {
			entities = new ArrayList<>();
		}
	}

	public List<Score> findAll() {
		return entities;
	}

	public Score findById(String id) throws Exception {
		return entities.stream().filter(e -> e.getId().equals(id)).findFirst().get();
	}

	public List<Score> findAllById(List<String> ids) throws Exception {
		List<Score> ret_ent = new ArrayList<>();
		ids.stream().forEach(id -> {
			ret_ent.add(entities.stream().filter(e -> e.getId().equals(id)).findFirst().get());
		});
		return ret_ent;
	}

	public void add(Score entity) throws Exception {
		entity.setId(Base64.encode(new String(strGen.getRandomString(16) + new Date().getTime()).getBytes()));
		entities.add(entity);
		updateDB();
	}

	public void update(Score entity) throws Exception {
		Score en = null;
		en = entities.stream().filter(e -> e.getId().equals(entity.getId())).findFirst().get();
		int i = 0;
		boolean found = false;
		for (Score e : entities) {
			if (e.getId().equals(en.getId())) {
				found = true;
				break;
			}
			i++;
		}
		if (!found) {
			i = -1;
		}
		en.combine(entity);
		entities.set(i, en);
		updateDB();
	}

	public void forceUpdate(Score entity) throws Exception {
		int i = 0;
		boolean found = false;
		for (Score e : entities) {
			if (e.getId().equals(entity.getId())) {
				found = true;
				break;
			}
			i++;
		}
		if (!found) {
			i = -1;
		}

		entities.set(i, entity);
		updateDB();
	}

	public void delete(Score entity) throws Exception {
		entities.remove(entity);
	}

	public void delete(String id) throws Exception {
		Score en = findById(id);
		entities.remove(en);
	}

	public Score lastEntry() throws Exception {
		return entities.get(entities.size() - 1);
	}

	public List<Score> getBestByScore(int count) {
		List<Score> returnScores = new ArrayList<>();
		entities.stream().forEach(e -> {
			returnScores.add(new Score(e.getId(), e.getName(), e.getDate(), e.getScore(), e.getTime_played()));
		});
		Collections.sort(returnScores);
		if (returnScores.size() < count) {
			return returnScores;
		}
		return returnScores.subList(0, count);
	}

	private void updateDB() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String data = encryption.encrypt(mapper.writeValueAsString(entities));

		filesystem.clear(ENTITIES_DB_URI);
		filesystem.write(data, ENTITIES_DB_URI);
	}
}
