package com.lmchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MainApplication {
	public static HashMap<String, Boolean> cache = new HashMap<>();

	public static List<String> apisForUsername = new ArrayList<>();

	public static List<String> apisForUuid = new ArrayList<>();

	public static void main(String[] args) {
		apisForUsername.add("https://api.mojang.com/users/profiles/minecraft/{nickname}");
		apisForUsername.add("https://minecraft-api.com/api/uuid/{nickname}/json");
		apisForUsername.add("https://api.minetools.eu/uuid/{nickname}");

		apisForUuid.add("https://api.namemc.com/profile/{uuid}/friends");
		apisForUuid.add("https://sessionserver.mojang.com/session/minecraft/profile/{uuid}");

		SpringApplication application = new SpringApplication(MainApplication.class);
		application.run(args);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				cache.clear();
			}
		}, 0, TimeUnit.MINUTES.toMillis(5));
	}
}
