package com.company;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Michael on 20.01.2017.
 */
public class beer {
    public String id;
    public String name;
    public String description;
    public int idStyle;

    public beer(){
        id = "";
        name = "";
        description = "";
        idStyle = 0;
    }

    public beer(JSONObject object) throws JSONException {
        id = object.getString("id");
        name = object.getString("name");
        description = object.getString("description");
        idStyle = object.getInt("styleId");
    }

    @Override
    public String toString(){
        return id + ";" + name + ";" + description + ";" + idStyle;
    }
}
