package com.company;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author  Michael van der Heide
 * @version 1.0
 * @since   1.0    26.12.2016
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

    /**
     * @return gets Beer as String (id;name;description;idStyle)
     */
    @Override
    public String toString(){
        return id + ";" + name + ";" + description + ";" + idStyle;
    }
}
