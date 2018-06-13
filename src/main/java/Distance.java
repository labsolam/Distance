import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Distance {

    public static void main(String[] args) {

        String first_location;
        String second_location;

        System.out.println("Welcome to Distance Checker!");

        first_location = Util.input("Please enter your first location:", "Invalid location. Please enter your first location");
        second_location = Util.input("Please enter your second location:", "Invalid location. Please enter your second location");

        System.out.println(first_location + " " + second_location);

        getCoordinates(first_location);

    }

    private static void getCoordinates(String location){
        //Get list of places (or auto select if popular result e.g. London, Paris)
        //Select place from list if not auto selected
        //Get coordinates for place

        URL url;

        try {
            url  = new URL("https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&key=AIzaSyAbOlediGeU36fOE0KvSBokaZaNaLklefk");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;

            while((inputLine = in.readLine()) != null){
                System.out.println(inputLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

        }

    }
}
