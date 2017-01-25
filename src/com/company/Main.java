package com.company;

import java.util.ArrayList;
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
            switch (askQuestion("What do you wanna do?", new String[]{"Load and print all beer styles", "Search for beer style by name", "Get all beers by style", "Get all beers currently in local storage", "Get beer by id", "Exit"})){
                case 0:
                    //Load and print all beer styles
                    printBeerStyles(beerAdmin.getBeerStyles());
                    break;
                case 1:
                    //Search for beer style by name
                    writeline("Which style are you searching?");
                    seperator();
                    printBeerStyles(beerAdmin.getBeerStyles(getInput()));
                    break;
                case 2:
                    //Get all beers by style
                    writeline("Which style id?");
                    seperator();
                    for (beer b:beerAdmin.getBeers(getInputAsNumber())) {
                        writeline(b.id + ": " + b.name + "(" + b.description + ")");
                    }
                    break;
                case 3:
                    //Get all beers currently in local storage
                    seperator();
                    for (beer b:beerAdmin.beerStorage) {
                        writeline(b.id + ": " + b.name);
                    }
                    break;
                case 4:
                    //Get beer by id
                    writeline("Which id?");
                    seperator();
                    beer b = beerAdmin.getBeer(getInput());
                    if(b != null) {
                        writeline(b.id + " : " + b.name);
                        writeline(b.description);
                    }
                    break;
                case 5:
                    //Exit
                    isActive = false;
                    break;
                default:
                    writeline("Couldn't find option... Please try again :)");
                    break;
            }
        }
    }

    private static void printBeerStyles(HashMap<Integer, String> styles){
        if (styles.size() > 0)
            for (Integer i:styles.keySet()) {
                writeline(i + " : " + styles.get(i));
            }
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
        System.out.println("[]------------------------------------------------------------------------------------------------------------------------------[]");
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