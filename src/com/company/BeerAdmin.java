package com.company;

import com.sun.javafx.collections.MappingChange;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Drake on 20.01.2017.
 */
public class BeerAdmin {
    public HashMap<Integer, String> beerStyles = new HashMap<>();

    public ArrayList<beer> getBeersWithFilter(String filter){
        ArrayList<beer> returnValue = new ArrayList<>();
        try {
            JSONObject jsonObject = JsonReader.readJsonFromUrl("http://api.brewerydb.com/v2/beers/?key=" + ApiKey + "&" + filter);
            JSONArray data = (JSONArray) jsonObject.get("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = (JSONObject) data.get(i);
                if(!object.isNull("id"))
                    returnValue.add(new beer(object));
            }
        }
        catch (Exception ex){ }
        return returnValue;
    }

    public beer getBeer(String id){
        ArrayList<beer> beers = getBeersWithFilter("id="+id);
        return beers.get(0);
    }

    public ArrayList<beer> getBeers(int idStyle){
        return getBeersWithFilter("styleId=" + idStyle);
    }

    public HashMap<Integer, String> getBeerStyles(){
        beerStyles = new HashMap<>();
        try{
            JSONObject jsonObject = JsonReader.readJsonFromUrl("http://api.brewerydb.com/v2/styles/?key=" + ApiKey);
            JSONArray data = (JSONArray) jsonObject.get("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = (JSONObject) data.get(i);
                beerStyles.put(object.getInt("id"), object.getString("name"));
            }
        }catch (Exception ex){}
        return beerStyles;
    }

    public HashMap<Integer, String> getBeerStyles(String searchTerm) {
        HashMap<Integer, String> searchResult = new HashMap<>();
        for (Integer i:beerStyles.keySet()) {
            String style = beerStyles.get(i);
            if(style.contains(searchTerm))
                searchResult.put(i, style);
        }
        return searchResult;
    }

    public String ApiKey = "17862d0e9821ed33bb504691196907e5";
}
