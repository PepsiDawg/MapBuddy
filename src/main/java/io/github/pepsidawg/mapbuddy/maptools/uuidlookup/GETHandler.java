package io.github.pepsidawg.mapbuddy.maptools.uuidlookup;

import io.github.pepsidawg.mapbuddy.utilities.UUIDUtil;
import org.bukkit.Bukkit;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class GETHandler {
    static String  baseURL = "https://api.mojang.com/users/profiles/minecraft/";

    static public UUID getUsernameUUID(String player) throws Exception{
        int responseCode;
        String result;

        if(Bukkit.getOfflinePlayer(player).isOnline())
            return Bukkit.getPlayer(player).getUniqueId();

        try {
            URL url = new URL(baseURL + player);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            responseCode = connection.getResponseCode();

            if(responseCode == 200 && connection.getResponseMessage().equalsIgnoreCase("ok")) {
                result = parseResponse(connection.getInputStream());
                return UUID.fromString(UUIDUtil.toLongForm(result));
            }
        } catch (Exception e) {}

        throw new Exception("Player " + player + " was not found! Is the username correct?");
    }

    static private String parseResponse(InputStream input) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
        String inLine;
        StringBuffer response = new StringBuffer();

        try {
            while ((inLine = buffer.readLine()) != null) {
                response.append(inLine);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(response.toString());
        return obj.getString("id");
    }
}
