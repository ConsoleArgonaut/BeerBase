package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BeerAdmin beerAdmin = new BeerAdmin();
        seperator();
        writeline("Welcome to the Bier-Service!");
        seperator();
        writeline("Made by Enrico Chatelin and Michael van der Heide");
        boolean isActive = true;
        while(isActive){
            seperator();
            boolean beersWerePrinted = false;
            switch (askQuestion("What do you wanna do?", new String[]{"Load and print all Beer styles", "Search for Beer style by name", "Get all beers by style", "Get all beers currently in local storage", "Get Beer by id", "Search for Beer in local storage", "Manage beers in local storage", "Exit"})){
                case 0:
                    //Load and print all Beer styles
                    printBeerStyles(beerAdmin.getBeerStyles());
                    break;
                case 1:
                    //Search for Beer style by name
                    writeline("Which style are you searching?");
                    seperator();
                    printBeerStyles(beerAdmin.getBeerStyles(getInput()));
                    break;
                case 2:
                    //Get all beers by style
                    writeline("Which style id?");
                    seperator();
                    for (Beer b:beerAdmin.getBeers(getInputAsNumber())) {
                        writeline(b.id + ": " + b.name + "(" + b.description + ")");
                        beersWerePrinted = true;
                    }
                    if(!beersWerePrinted)
                        writeline("No beers were found for that style :(");
                    break;
                case 3:
                    //Get all beers currently in local storage
                    seperator();
                    for (Beer b:beerAdmin.beerStorage) {
                        writeline(b.id + ": " + b.name);
                        beersWerePrinted = true;
                    }
                    if(!beersWerePrinted)
                        writeline("No beers currently in storage :(");
                    break;
                case 4:
                    //Get Beer by id
                    writeline("Which id?");
                    seperator();
                    Beer beer1 = beerAdmin.getBeer(getInput());
                    if(beer1 != null) {
                        writeline(beer1.id + " : " + beer1.name);
                        writeline(beer1.description);
                    }
                    else {
                        writeline("Couldn't find a Beer with that id :(");
                    }
                    break;
                case 5:
                    //Search for Beer in local storage
                    writeline("Which name?");
                    seperator();
                    for (Beer b:beerAdmin.getBeers(getInput())) {
                        seperator();
                        writeline(b.id + " : " + b.name);
                        writeline(b.description);
                        beersWerePrinted = true;
                    }
                    if(!beersWerePrinted)
                        writeline("No Beer with that name was found :(");
                    break;
                case 6:
                    //Manage beers in local storage
                    seperator();
                    writeline("Managing local beers");
                    seperator();
                    boolean isManaging = true;
                    while (isManaging){
                        switch (askQuestion("What do you wanna do?", new String[]{"Add beer", "Remove beer", "List all beers", "Remove all duplicates from storage", "Exit"})){
                            case 0:
                                Beer beerToAdd = new Beer();
                                writeline("Whats the name of your beer?");
                                beerToAdd.name = getInput();
                                seperator();
                                writeline("Whats the description of your beer?");
                                beerToAdd.description = getInput();
                                seperator();
                                writeline("What the id of your beer?");
                                beerToAdd.id = getInput();
                                seperator();
                                writeline("What style does your beer have?");
                                beerToAdd.idStyle = getInputAsNumber();
                                seperator();
                                beerAdmin.beerStorage.add(beerToAdd);
                                writeline("Added beer to storage");
                                seperator();
                                break;
                            case 1:
                                writeline("Please enter the ID of the beer to remove");
                                String id = getInput();
                                Beer beerToRemove = null;
                                for (Beer b : beerAdmin.beerStorage){
                                    if(b.id.contains(id)){
                                        beerToRemove = b;
                                    }
                                }
                                if(beerToRemove != null){
                                    beerAdmin.beerStorage.remove(beerToRemove);
                                    writeline("Beer successfully removed");
                                }
                                else
                                    writeline("Couldn't find a beer with the id: " + id);
                                seperator();
                                break;
                            case 2:
                                boolean printed = false;
                                for (Beer b:beerAdmin.beerStorage) {
                                    seperator();
                                    writeline(b.id + " : " + b.name);
                                    writeline(b.description);
                                    printed = true;
                                }
                                if(!printed)
                                    writeline("No beers found");
                                seperator();
                                break;
                            case 3:
                                beerAdmin.removeDuplicateBeers();
                                writeline("All duplicates removed");
                                seperator();
                                break;
                            case 4:
                                isManaging = false;
                                break;
                            default:
                                writeline("Sorry, we didn't understand what you want to do :(");
                                break;
                        }
                    }
                    break;
                case 7:
                    //Exit
                    isActive = false;
                    break;
                default:
                    writeline("Couldn't find option... Please try again :)");
                    break;
            }
        }
        writeline("We hope to see you again soon :)");
    }

    private static void printBeerStyles(HashMap<Integer, String> styles){
        if (styles.size() > 0)
            for (Integer i:styles.keySet()) {
                writeline(i + " : " + styles.get(i));
            }
        else
            writeline("No Beer styles were found, or loaded");
    }
    /** Read line */
    private static String getInput(){
        boolean gettingInput = true;
        String returnValue = "";
        System.out.print("  ");
        Scanner in = new Scanner(System.in);
        returnValue = in.nextLine();
        if(returnValue != null)
            return returnValue;
        else
            return "";
    }
    private static int getInputAsNumber(){
        int returnValue = 0;
        boolean inputIsCorrect = false;
        while(!inputIsCorrect){
            String input = getInput();
            if(input.length() != 0){
                try{
                    returnValue = Integer.parseInt(input);
                    inputIsCorrect = true;
                }
                catch (Exception ex){
                    inputIsCorrect = false;
                    writeline("Please answer with a number");
                }
            }
            else
                writeline("Please give an input before pressing enter");
        }
        return returnValue;
    }
    /** Output of 1 line */
    private static void writeline(String text){
        System.out.println("  " + text);
    }
    /** Writes a separator to separate printlines */
    private static void seperator(){
        System.out.println("[]--------------------------------------------------------------------[]");
    }
    /** Ask a Question and get Answer */
    private static int askQuestion(String question, String[] possibleAnswers){
        int returnValue = 0;
        writeline(question);
        for (int i = 0; i < possibleAnswers.length; i++) {
            writeline((i+1) + ") " + possibleAnswers[i]);
        }
        boolean inputIsCorrect = false;
        while(!inputIsCorrect){
            returnValue = getInputAsNumber();
            returnValue--;
            if(returnValue >= possibleAnswers.length || returnValue < 0){
                writeline("Please answer correctly");
            }
            else {
                 inputIsCorrect = true;
            }
        }
        return returnValue;
    }
}