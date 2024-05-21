package api;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class SynonymAntonym {
    public static final String API = "https://api.datamuse.com/words?";
    public static final int DEFAULT_LIMIT = 50;

    public static ArrayList<String> getSynonyms(final String word) {
        return getSynonyms(word, DEFAULT_LIMIT);
    }

    public static ArrayList<String> getAntonyms(final String word) {
        return getAntonyms(word, DEFAULT_LIMIT);
    }

    public static ArrayList<String> getSynonyms(final String word, final int max) {
        try {
            String urlStr = API + String.format("&ml=%s", URLEncoder.encode(word, StandardCharsets.UTF_8).replace("+", "%20"))
                    + String.format("&max=%d", max);

            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlStr))
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final JSONArray jsonArray = new JSONArray(response.body());
            final ArrayList<String> words = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject obj = jsonArray.getJSONObject(i);
                words.add(obj.getString("word"));
            }
            return words;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> getAntonyms(final String word, final int max) {
        try {
            String urlStr = API + String.format("&rel_ant=%s", URLEncoder.encode(word, StandardCharsets.UTF_8).replace("+", "%20"))
                    + String.format("&max=%d", max);

            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlStr))
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final JSONArray jsonArray = new JSONArray(response.body());
            final ArrayList<String> words = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject obj = jsonArray.getJSONObject(i);
                words.add(obj.getString("word"));
            }
            return words;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
