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
     * HashMap containing ID's and names of the currently loaded Beer styles
     */
    protected HashMap<Integer, String> beerStyles = new HashMap<>();

    /**
     * All the currently loaded beers
     */
    protected ArrayList<Beer> beerStorage = new ArrayList<>();

    /**
     * Gets the beers by filter from the web api
     * @param filter The filter to add to the url to get beers
     * @return An ArrayList of beers gotten from the
     * @see JsonReader
     */
    public ArrayList<Beer> getBeersWithFilter(String filter){
        ArrayList<Beer> returnValue = new ArrayList<>();
        try {
            JSONObject jsonObject = JsonReader.readJsonFromUrl("http://api.brewerydb.com/v2/beers/?key=" + ApiKey + "&" + filter);
            JSONArray data = (JSONArray) jsonObject.get("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = (JSONObject) data.get(i);
                if(!object.isNull("id")){
                    Beer b = new Beer(object);
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
     * Gets a Beer by Beer id
     * @param id The id of the Beer to get
     * @return The searched Beer
     */
    public Beer getBeer(String id){
        ArrayList<Beer> Beers = getBeersWithFilter("ids="+id);
        if(Beers.size() == 0)
            return null;
        else
            return Beers.get(0);
    }

    /**
     * Gets the beers by the Beer style
     * @param idStyle The Beer style to get beers
     * @return An ArrayList of beers gotten from the
     */
    public ArrayList<Beer> getBeers(int idStyle){
        return getBeersWithFilter("styleId=" + idStyle);
    }

    /**
     * Gets the beers by the beer name
     * @param beerName The Beer name to get beers with
     * @return An ArrayList of beers gotten from the
     */
    public ArrayList<Beer> getBeers(String beerName){
        ArrayList<Beer> returnValue = new ArrayList<>();
        //Searches for Beer name in local storage
        for (Beer b:beerStorage) {
            if(b.name.contains(beerName))
                returnValue.add(b);
        }
        return returnValue;
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
     * @param searchTerm The term that has to be in the Beer style
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

    /**
     * Removes all the duplicates from local beer storage
     */
    public void removeDuplicateBeers(){
        for (int i = 0; i < beerStorage.size(); i++) {
            Beer beer1 = beerStorage.get(i);
            for (int j = 0; j < beerStorage.size(); j++) {
                Beer beer2 = beerStorage.get(j);
                if(beer1 != beer2){
                    if(beer1.toString().contains(beer2.toString()))
                        beerStorage.remove(beer2);
                }
            }
        }
    }

    /**
     * Removes all beers and beer styles from local storage
     */
    public void clearLocalStorage(){
        beerStorage = new ArrayList<>();
        beerStyles = new HashMap<>();
    }

    public String ApiKey = "17862d0e9821ed33bb504691196907e5";
}
