package utils;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    private static String accessToken;
    private static long expiryTime;

    public static synchronized String getToken() {
        if (accessToken == null || System.currentTimeMillis() >= expiryTime) {
            refreshAccessToken();
        }
        return accessToken;
    }

    private static void refreshAccessToken() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("client_id", ConfigReader.getProperty("client_id"));
        formParams.put("client_secret", ConfigReader.getProperty("client_secret"));
        formParams.put("grant_type", "refresh_token");
        formParams.put("refresh_token", ConfigReader.getProperty("refresh_token"));

        Response response = RestAssured.given()
                .formParams(formParams)
                .post(ConfigReader.getProperty("auth_token_url"));

        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("access_token");
            int expiresIn = response.jsonPath().getInt("expires_in");
            expiryTime = System.currentTimeMillis() + (expiresIn * 1000);
        } else {
            throw new RuntimeException("Failed to refresh token: " + response.asString());
        }
    }
}
