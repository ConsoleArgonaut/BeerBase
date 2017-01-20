package com.company;

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

    @Override
    public String toString(){
        return id + ";" + name + ";" + description + ";" + idStyle;
    }
}
