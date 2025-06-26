package com.work.logistics.utils;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class MapAPI {

    private static final String GEO_API_URL = "https://restapi.amap.com/v3/geocode/geo";
    private static final String KEY = "127c4ff2738a8c1a98ffa20089a13c8e";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public String getLocation(String address) {
        try {
            String url = GEO_API_URL + "?address=" + address + "&key=" + KEY;
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(response);
            if (root.path("status").asText().equals("1") && root.path("count").asInt() > 0) {
                return root.path("geocodes").get(0).path("location").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 失败返回null
    }
}