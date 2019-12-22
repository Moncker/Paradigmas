package core.OWM;

import core.model.Tiempo;
import org.apache.http.client.ClientProtocolException;
import org.graalvm.compiler.replacements.Log;
import org.json.JSONException;


import java.io.IOException;
import java.time.Clock;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private  OpenWeatherMap owm = new OpenWeatherMap("weather");
    HashMap<String, Tiempo> historial;

    public App(){
        this.owm.setApiKey("8d92c4241ad64d0fe808a2f501954581");
        this.historial= new HashMap<String, Tiempo>();
    };


    public Tiempo buscaTiempoPorNombre(String nombre) throws IOException {
        try {
            if (historial.containsKey(nombre)) {
                return historial.get(nombre);
            } else {
                CurrentWeather tiempo = owm.currentWeatherByCityName(nombre);
                double temp = tiempo.getMainInstance().getTemperature();
                double humedad = tiempo.getMainInstance().getHumidity();
                Date date=tiempo.getDateTime();
                Tiempo ti= new Tiempo(temp,humedad,date);
                return ti;
            }



        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }public Tiempo buscaTiempoPorCoordenadas(float lat,float lon) throws IOException {

        CurrentWeather tiempo = owm.currentWeatherByCoordinates(lat, lon);
        double temp = tiempo.getMainInstance().getTemperature();
        double humedad = tiempo.getMainInstance().getHumidity();
        Date date = tiempo.getDateTime();
        Tiempo ti = new Tiempo(temp, humedad, date);
        return ti;

    }
    public static void main(String[] args) {
        App instance=new App();

        try {
            Scanner myObj= new Scanner(System.in);
            System.out.println("Que quieres buscar? 1-coordenadas, 2-nombre de ciudad");
            String op = myObj.nextLine();  // Read user input
            if(op.equals(""+1)){
                System.out.println("Introduce latitud");
                String lat = myObj.nextLine();  // Read user input
                System.out.println("Introduce longitud");
                String lon = myObj.nextLine();  // Read user input
                Tiempo tiempo=instance.buscaTiempoPorCoordenadas(Float.parseFloat(lat),Float.parseFloat(lon));
                System.out.println("La temperatura es: ");
                System.out.println(tiempo.getGrados());
                System.out.println("La humedad es: ");
                System.out.println(tiempo.getHumedad());
            }else {

                System.out.println("Introduce el nombre de una ciudad para obtener el tiempo actual: ");

                String ciudad = myObj.nextLine();  // Read user input
                Tiempo tiempo = instance.buscaTiempoPorNombre(ciudad);
                System.out.println("La temperatura es: ");
                System.out.println(tiempo.getGrados());
                System.out.println("La humedad es: ");
                System.out.println(tiempo.getHumedad());
            }

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
