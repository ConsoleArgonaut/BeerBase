package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author  Michael van der Heide
 * @version 1.0
 * @since   1.0    26.12.2016
 */
public class BeerAdmin {

    /**
     * HashMap containing ID's and names of the currently loaded beer styles
     */
    protected HashMap<Integer, String> beerStyles = new HashMap<>();

    /**
     * All the currently loaded beers
     */
    protected ArrayList<beer> beerStorage = new ArrayList<>();

    /**
     * Gets the beers by filter from the web api
     * @param filter The filter to add to the url to get beers
     * @return An ArrayList of beers gotten from the
     * @see JsonReader
     */
    public ArrayList<beer> getBeersWithFilter(String filter){
        ArrayList<beer> returnValue = new ArrayList<>();
        try {
            JSONObject jsonObject = JsonReader.readJsonFromUrl("http://api.brewerydb.com/v2/beers/?key=" + ApiKey + "&" + filter);
            JSONArray data = (JSONArray) jsonObject.get("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = (JSONObject) data.get(i);
                if(!object.isNull("id")){
                    beer b = new beer(object);
                    returnValue.add(b);
                    if(!beerStorage.contains(b))
                        beerStorage.add(b);
                }
            }
        }
        catch (Exception ex){ }
        return returnValue;
    }

    /**
     * Gets a beer by beer id
     * @param id The id of the beer to get
     * @return The searched beer
     */
    public beer getBeer(String id){
        ArrayList<beer> beers = getBeersWithFilter("ids="+id);
        if(beers.size() == 0)
            return null;
        else
            return beers.get(0);
    }

    /**
     * Gets the beers by the beer style
     * @param idStyle The beer style to get beers
     * @return An ArrayList of beers gotten from the
     */
    public ArrayList<beer> getBeers(int idStyle){
        return getBeersWithFilter("styleId=" + idStyle);
    }

    /**
     * Gets all beers styles
     * @return A HashMap containing all Beer Styles
     */
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

    /**
     * Gets all beers styles and searches for searchTerm
     * @param searchTerm The term that has to be in the beer style
     * @return A HashMap containing all fitting Beer Styles
     */
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
