package com.distance;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Main {

    public static void main(String[] args) {

        String first_location;
        String second_location;

        System.out.println("Welcome to Distance Checker!");

        first_location = Utils.input("Please enter your first location:", "Invalid location. Please enter your first location");
        second_location = Utils.input("Please enter your second location:", "Invalid location. Please enter your second location");

        System.out.println(first_location + " " + second_location);

    }

    private static void getCoordinates(String location){
        //Get list of places (or auto select if popular result e.g. London, Paris)
        //Select place from list if not auto selected
        //Get coordinates for place

        String api = "";

    }
}
