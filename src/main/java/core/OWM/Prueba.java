package core.OWM;

import org.apache.http.client.ClientProtocolException;
import org.graalvm.compiler.replacements.Log;
import org.json.JSONException;

import java.io.IOException;
import java.time.Clock;

public class Prueba {
    public static void main(String[] args) {

        OpenWeatherMap owm = new OpenWeatherMap("weather");
        owm.setApiKey("8d92c4241ad64d0fe808a2f501954581");

        try {
            CurrentWeather pruebas= owm.currentWeatherByCityName("Madrid");
            System.out.println(pruebas.getRainInstance());


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
