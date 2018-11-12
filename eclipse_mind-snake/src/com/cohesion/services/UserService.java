package com.cohesion.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cohesion.entities.RawUser;
import com.cohesion.entities.Score;
import com.cohesion.entities.SnakeProfile;
import com.cohesion.entities.User;
import com.cohesion.models.FilesystemHandler;
import com.cohesion.models.Global;
import com.cohesion.models.RandomStringGen;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class UserService {

	private static String ENTITIES_DB_URI = "resources/dat/users.collection";
	private static Encryption encryption;
	private static FilesystemHandler filesystem;
	private static RandomStringGen strGen;
	private static List<User> entities;
	private static List<RawUser> rawEntities;
	private static ScoreService service = Global.scoreService;

	public UserService() {
		filesystem = new FilesystemHandler();
		encryption = new Encryption();
		strGen = new RandomStringGen();
	}

	public void load(String projectRoot) throws Exception {
		ENTITIES_DB_URI = projectRoot + ENTITIES_DB_URI;
		String data = Global.encryption.decrypt(filesystem.read(ENTITIES_DB_URI));
		if (data != null && !data.equals("")) {
			ObjectMapper mapper = new ObjectMapper();
			rawEntities = mapper.readValue(data, new TypeReference<List<RawUser>>() {
			});
			entities = new ArrayList<>();
			rawEntities.stream().forEach(re -> {
				try {
					entities.add(new User(re, service.findById(re.getMax_score_id())));
				} catch (Exception e) {
					entities.add(new User(re, new Score()));
				}
			});
		} else {
			rawEntities = new ArrayList<>();
			entities = new ArrayList<>();
		}
	}

	public List<User> findAll() {
		return entities;
	}

	public User findById(String id) throws Exception {
		return entities.stream().filter(e -> e.getId().equals(id)).findFirst().get();
	}

	public List<User> findAllById(List<String> ids) throws Exception {
		List<User> ret_ent = new ArrayList<>();
		ids.stream().forEach(id -> {
			ret_ent.add(entities.stream().filter(e -> e.getId().equals(id)).findFirst().get());
		});
		return ret_ent;
	}

	public User findByName(String name) throws Exception {
		return entities.stream().filter(e -> e.getName().equals(name)).findFirst().get();
	}

	public List<User> findAllByName(List<String> names) throws Exception {
		List<User> ret_ent = new ArrayList<>();
		names.stream().forEach(name -> {
			ret_ent.add(entities.stream().filter(e -> e.getName().equals(name)).findFirst().get());
		});
		return ret_ent;
	}

	public void add(User entity) throws Exception {
		entity.setId(Base64.encode(new String(strGen.getRandomString(16) + new Date().getTime()).getBytes()));
		entities.add(entity);
		updateDB();
	}

	public void update(User entity) throws Exception {
		User en = null;
		en = entities.stream().filter(e -> e.getName().equals(entity.getName())).findFirst().get();
		int i = 0;
		boolean found = false;
		for (User e : entities) {
			if (e.getName().equals(en.getName())) {
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

	public void forceUpdate(User entity) throws Exception {
		int i = 0;
		boolean found = false;
		for (User e : entities) {
			if (e.getName().equals(entity.getName())) {
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

	public void delete(User entity) throws Exception {
		entities.remove(entity);
	}

	public void delete(String id) throws Exception {
		User en = findByName(id);
		entities.remove(en);
	}

	public User lastEntry() throws Exception {
		return entities.get(entities.size() - 1);
	}

	public User getUser(String name) throws Exception {
		try {
			return findByName(name);
		} catch (Exception e) {
			System.out.println("User '" + name + "' does not exists. Creating...");
		}
		User user = new User(null, name, new Date().getTime(), 0, 0, new Score(), new SnakeProfile());
		add(user);
		System.out.println("DONE");
		return findByName(name);
	}

	private void updateDB() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		rawEntities = new ArrayList<>();
		entities.stream().forEach(e -> {
			rawEntities.add(new RawUser(e));
		});
		String data = encryption.encrypt(mapper.writeValueAsString(rawEntities));

		filesystem.clear(ENTITIES_DB_URI);
		filesystem.write(data, ENTITIES_DB_URI);
	}
}
