package com.first.community.provider;

import com.alibaba.fastjson.JSON;
import com.first.community.DTO.AccessDTO;
import com.first.community.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Chiayhon
 * @create 2019 - 10 - 10
 */
@Component
public class GitHubProvider {
    public String getAccessToken(AccessDTO accessDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?" + accessToken)//bug1:多了access_token=
                .build();
        try{
            Response response = client.newCall(request).execute();
            String string = response.body().string();
//            GithubUser githubUser = JSON.parseObject(s, GithubUser.class);//将string的JSON对象转换为java的githubUser类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
