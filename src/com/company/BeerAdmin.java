package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Drake on 20.01.2017.
 */
public class BeerAdmin {
    public HashMap<Integer, String> beerStyles = new HashMap<>();

    /* Loads the beer styles from web api **/
    public void loadBeerStyles(){throw new NotImplementedException();}

    /* Returns beer styles, Id is separated from name with:: **/
    public ArrayList<String> printBeerStyles() {throw new NotImplementedException();}
    
    /* Returns beer styles, where name contains searched terms. Id is separated from name with:: **/
    public ArrayList<String> printBeerStyles(String search) {throw new NotImplementedException();}

    /* Returns all beers from style. Gets JSON file from web api and parses to beer class
    * http://api.brewerydb.com/v2/beers/?key=1511d0db4a1d6841481c672455358cff&styleId=5 **/
    public ArrayList<String> getBeerListForStyle(int idStyle) {throw new NotImplementedException();}

    /* Returns all Ids and Names from beers in local storage **/
    public ArrayList<String> printBeerList(){ throw new NotImplementedException(); }

    /* Returns Id, Name and on a new line description of the searched beer in local storage **/
    public ArrayList<String> printBeer(String id) { throw new NotImplementedException(); }
}
