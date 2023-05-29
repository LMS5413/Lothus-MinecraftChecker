package com.lmchecker.functions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

import static com.lmchecker.MainApplication.*;

public class getRequestUUID {
    public static boolean validIsOriginal(String UUID) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Random random = new Random();
        Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        if (UUID_REGEX.matcher(UUID).matches()) {
            String apiUrl = apisForUuid.get(random.nextInt(apisForUuid.size()));
            Request request = new Request.Builder()
                    .url(apiUrl.replace("{uuid}", UUID))
                    .addHeader("Accept", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                cache.put(UUID, response.code() == 200);
                return response.code() == 200;
            }
        }
        String apiUrl = apisForUsername.get(random.nextInt(apisForUsername.size()));
        Request request = new Request.Builder()
                .url(apiUrl.replace("{nickname}", UUID))
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String body = response.body().string();
            if (body.equalsIgnoreCase("Player not found !")) {
                cache.put(UUID, false);
                return false;
            }
            if (response.code() == 404) {
                cache.put(UUID, false);
                return false;
            }
            JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
            if (jsonObject.get("id") == null && jsonObject.get("uuid") == null) {
                cache.put(UUID, false);
                return false;
            }
            cache.put(UUID, true);
            return true;
        }
    }
}
