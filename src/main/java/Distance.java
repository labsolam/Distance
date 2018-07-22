import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Distance {

    public static void main(String[] args) {

        String firstLocation;
        String secondLocation;

        System.out.println("Welcome to Distance Checker!");

        firstLocation = Util.input("Please enter your first location:", "Invalid location. Please enter your first location");
        secondLocation = Util.input("Please enter your second location:", "Invalid location. Please enter your second location");

        System.out.println(calculateDistance(firstLocation, secondLocation));

    }

    private static double calculateDistance(String firstLocation, String secondLocation){
        double[] firstCoordinates = getCoordinates(firstLocation);
        double[] secondCoordinates = getCoordinates(secondLocation);


        double earth_radius = 6371; //metres
        double changeLat = Math.toRadians(secondCoordinates[0] - firstCoordinates[0]);
        double changeLong = Math.toRadians(secondCoordinates[1] - firstCoordinates[1]);
        double firstLatRadians = Math.toRadians(firstCoordinates[0]);
        double secondLatRadians = Math.toRadians(secondCoordinates[0]);

        double a = Math.sin(changeLat/2) * Math.sin(changeLat/2) + Math.sin(changeLong/2) * Math.sin(changeLong/2) * Math.cos(firstLatRadians) * Math.cos(secondLatRadians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = earth_radius * c;

        return d;

    }

    private static double[] getCoordinates(String location){
        //Get list of places (or auto select if popular result e.g. London, Paris)
        //Select place from list if not auto selected
        //Get coordinates for place

        location = location.replace(" ", "%20");

        URL url = null;
        HttpURLConnection connection = null;
        BufferedReader in = null;

        try { //TODO: Get the calling method to handle this exception
            url  = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="
                    + location
                    + "&key="
                    + Secret.API); //Create your own API key and store it in the API variable

            connection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder builder = new StringBuilder();

            while((inputLine = in.readLine()) != null){
                builder.append(inputLine);
            }

            double[] coordinates = new double[2];
            coordinates[0] = ((new JsonParser().parse(builder.toString())).getAsJsonObject().get("results")).getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble();
            coordinates[1] = ((new JsonParser().parse(builder.toString())).getAsJsonObject().get("results")).getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsDouble();

            return coordinates;


        } catch (MalformedURLException e) {
            e.printStackTrace(); //TODO: Log to file
            return null; //TODO: Get the calling method to handle this exception
        } catch (IOException e) {
            e.printStackTrace(); //TODO: Log to file
            return null; //TODO: Get the calling method to handle this exception
        } catch (Exception e) {
            e.printStackTrace(); //TODO: Log to file
            return null; //TODO: Get the calling method to handle this exception
        } finally {
            if(connection != null){
                connection.disconnect();
            }

            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                //TODO: Make a log file instead of outputting to the console
                System.err.println("Buffered Reader IOException" + e.getMessage());
            } catch (Exception e){
                System.err.println("Any other Bufferered Reader Exception" + e.getMessage());
            }
        }
    }
}
