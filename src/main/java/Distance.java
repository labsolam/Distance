import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Distance {

    public static void main(String[] args) {

        String first_location;
        String second_location;

        System.out.println("Welcome to Distance Checker!");

        first_location = Util.input("Please enter your first location:", "Invalid location. Please enter your first location");
        second_location = Util.input("Please enter your second location:", "Invalid location. Please enter your second location");

        System.out.println(calculateDistance(first_location, second_location));

    }

    private static String calculateDistance(String first_location, String secon_location){
        return getCoordinates(first_location);
    }

    private static String getCoordinates(String location){
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

            Gson gson = new Gson();
            String json = gson.toJson(builder);

            return json;


        } catch (MalformedURLException e) {
            e.printStackTrace(); //TODO: Log to file
            return "Fail"; //TODO: Get the calling method to handle this exception
        } catch (IOException e) {
            e.printStackTrace(); //TODO: Log to file
            return "Fail"; //TODO: Get the calling method to handle this exception
        } catch (Exception e) {
            e.printStackTrace(); //TODO: Log to file
            return "Fail"; //TODO: Get the calling method to handle this exception
        } finally {
            if(url != null){
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
