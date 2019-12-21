package core.OWM;

import org.apache.http.client.ClientProtocolException;
import org.graalvm.compiler.replacements.Log;
import org.json.JSONException;


import java.io.IOException;
import java.time.Clock;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {

        OpenWeatherMap owm = new OpenWeatherMap("weather");
        owm.setApiKey("8d92c4241ad64d0fe808a2f501954581");

        try {
            Scanner myObj= new Scanner(System.in);
            System.out.println("Introduce el nombre de una ciudad para obtener el tiempo actual: ");

            String ciudad = myObj.nextLine();  // Read user input
            CurrentWeather weather=owm.currentWeatherByCityName(ciudad);

            System.out.println("La temperatura es: ");
            System.out.println(weather.getMainInstance().getTemperature());
            System.out.println("La humedad es: ");
            System.out.println(weather.getMainInstance().getHumidity());



        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
