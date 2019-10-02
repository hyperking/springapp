package com.springapi.utils;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * SlackAuth
 */
public class SlackAuth {

    public String slackuri = "https://slack.com/api/oauth.access";
    private String client_secret = "e86a36cb76dced9628c7898aa8c241c4";
    private String client_id = "2983885750.614017141575";
    private String redirect_uri = "http://localhost:8090/user/oauth";
    public String slackrequest_param = "";

    public HashMap<String, Object> fetch(String endpoint, String method, HashMap<String, String> payload) throws IOException {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        payload.forEach((k, v) -> {
            slackrequest_param += k + "=" + v + ("&");
            map.add(k, v);
        });
        // MediaType JSON = MediaType.get("application/x-www-form-urlencoded");
        // OkHttpClient client = new OkHttpClient();
        // RequestBody body = RequestBody.create(JSON, slackrequest_param);
        // Request request = new Request.Builder().url(endpoint).post(body).build();
        // try (Response response = client.newCall(request).execute()) {
        //     return response.body().string();
        // } catch (Exception e) {
        //     return e.toString();
        // }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<HashMap> response = restTemplate.postForEntity(endpoint, request, HashMap.class);
        // ResponseEntity<HashMap> response = restTemplate.exchange(endpoint, HttpMethod.POST, request, HashMap.class);
        return response.getBody();
    }

    public HashMap<String, Object> getAccessToken(HashMap<String, String> payload) {
        payload.put("client_id", client_id);
        payload.put("client_secret", client_secret);
        payload.put("redirect_uri", redirect_uri);
        HashMap<String, Object> result = new HashMap<>();
        try {
            result = fetch(slackuri, "POST", payload);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // HashMap<String, String> result = fetch( slackuri, "POST", payload);
        return result;
    }
}