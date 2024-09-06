package com.iafenvoy.sponsor.util;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.iafenvoy.sponsor.SponsorCore;
import com.ibm.icu.impl.Pair;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NetworkUtil {
    public static String getData(String url) {
        return getData(url, Method.GET, new ArrayList<>(), null);
    }

    public static String getData(String url, Method method, List<Pair<String, String>> header, String data) {
        HttpsURLConnection con = null;
        try {
            con = (HttpsURLConnection) new URL(url).openConnection();
            con.setRequestMethod(method.text);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            if (!header.isEmpty())
                for (Pair<String, String> p : header)
                    con.setRequestProperty(p.first, p.second);
            if (method == Method.POST) {
                OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8);
                osw.write(data);
                osw.flush();
                osw.close();
            }
        } catch (Exception e) {
            SponsorCore.LOGGER.error("Failed to fetch {}", url, e);
        }
        if (con != null)
            con.disconnect();
        else
            return "";

        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String temp;
            while (((temp = br.readLine())) != null)
                builder.append(temp).append("\n");
        } catch (Exception e) {
            SponsorCore.LOGGER.error("Failed to fetch {}", url, e);
        }
        return builder.toString();
    }

    public enum Method {
        POST("POST"), GET("GET");
        private final String text;

        Method(String text) {
            this.text = text;
        }
    }
}
