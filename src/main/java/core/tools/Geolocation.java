package core.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Geolocation {

    public static String getCityByPublicIp() throws IOException {
        return getLocation(getPublicIPAddress());
    }

    public static String getPublicIPAddress() {

        String systemipaddress = "";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            systemipaddress = sc.readLine().trim();
        } catch (Exception e) {
            systemipaddress = "Cannot know public IP";
        }
        return systemipaddress;
    }

    public static String getLocation(String publicIPAddress) throws IOException {

        String petURL = "https://ipapi.co/";
        petURL += publicIPAddress;
        petURL += "/json/";

        URL ipapi = new URL(petURL);

        URLConnection c = ipapi.openConnection();
        c.setRequestProperty("User-Agent", "java-ipapi-client");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(c.getInputStream())
        );
        String location = reader.readLine();
        location = reader.readLine();
        location = reader.readLine();

        String[] cadAux = location.split(" :");
        location = location.substring(13, location.length() -2);

        reader.close();

        return location;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getLocation(getPublicIPAddress()));
    }
}

