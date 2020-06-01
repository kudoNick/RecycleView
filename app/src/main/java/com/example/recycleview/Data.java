package com.example.recycleview;

import org.json.JSONException;
import org.json.JSONObject;

public class Data {
    String name,realName,team,bio;

    public Data (JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("name")) {
            name = jsonObject.getString("name");
        }
        if (jsonObject.has("realname")) {
            realName = jsonObject.getString("realname");
        }
        if (jsonObject.has("team")) {
            team = jsonObject.getString("team");
        }
        if (jsonObject.has("bio")) {
            bio = jsonObject.getString("bio");
        }
    }
    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getTeam() {
        return team;
    }

    public String getBio() {
        return bio;
    }
}