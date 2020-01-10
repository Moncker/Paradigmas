package core.persistence;

import core.Exceptions.CityNotFoundException;
import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.Exceptions.AlreadyAddedException;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector implements IDataBase{

    String url = "paradigmas.db";
    private Connection connect;

    // METODO PARA CONECTAR A LA BASE DE DATOS
    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            PreparedStatement st_fk = connect.prepareStatement("PRAGMA foreign_keys = ON");
            st_fk.execute();
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
    }

    // METODO PARA DESCONECTARSE DE LA BASE DE DATOS
    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // *************************************************************************************************************************
    // ************************************************** WEATHER METHODS ******************************************************
    // *************************************************************************************************************************

    // METODO PARA COGER EL TIEMPO  ACTUAL GUARDADO EN LA BBDD DE UNA LOCALIZACION
    // Devuelve null si no encuentra información almacenada
    // Devuelve el tiempo más cercano a la fecha actual
    public Tiempo getWeather(Localizacion localizacion) {
        ResultSet rs;
        Tiempo weather = null;
        try {
            PreparedStatement st = connect.prepareStatement("select * from historial join tiempo using(id_tiempo) join localizacion using(coordenadaX, coordenadaY) where ((ROUND(localizacion.coordenadaX, 5) = " + localizacion.getCoordenadas().getX() + " and ROUND(localizacion.coordenadaY, 5) = " + localizacion.getCoordenadas().getY() + ") or nombre_ciudad = '"+ localizacion.getName() +"') and fecha = date('now') order by id_tiempo desc LIMIT 1");
            rs = st.executeQuery();

            if (rs.next()){
                float grades = rs.getFloat("grados");
                String state = rs.getString("estado");
                float humidity = rs.getFloat("humedad");
                String date = rs.getString("fecha");
                String consDate = rs.getString("fecha_consulta");
                String ciudad = rs.getString("nombre_ciudad");

                weather = new Tiempo(ciudad, grades, state, humidity, LocalDate.parse(date), LocalDate.parse(consDate));
            } else{
                throw new CityNotFoundException("No se tiene informacion sobre esta ciudad.");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (CityNotFoundException e){

        }
        return weather;
    }

    // METODO PARA COGER LA PREDICCION DE VARIOS DIAS DE UNA LOCALIZACION
    // Devuelve una lista vacía si no encuentra informacion
    // Devuelve la lista con los tiempos de cada dia que tengamos almacenados
    public Tiempo[] getWeatherDays(Localizacion localizacion){
        Tiempo[] prevision = new Tiempo[3];
        ResultSet rs_city;
        ResultSet rs_prev;
        try{
            PreparedStatement st_city = connect.prepareStatement("select COUNT() as cantidad from localizacion where (ROUND(coordenadaX,5) = "+ localizacion.getCoordenadas().getX() +" and ROUND(coordenadaY,5) = "+localizacion.getCoordenadas().getY() +") or nombre_ciudad = '"+ localizacion.getName()+"'");
            PreparedStatement st_prev = connect.prepareStatement("select * from (select * from tiempo join historial using(id_tiempo) join localizacion using(coordenadaX, coordenadaY) where (ROUND(localizacion.coordenadaX,5) = "+ localizacion.getCoordenadas().getX() +" and ROUND(localizacion.coordenadaY,5) = "+ localizacion.getCoordenadas().getY() +") or nombre_ciudad = '"+ localizacion.getName() +"' order by id_tiempo ASC) group by fecha");

            rs_city = st_city.executeQuery();
            int saved = rs_city.getInt("cantidad");

            if (saved == 0) throw new CityNotFoundException("No se tienen datos de la ciudad");

            rs_prev = st_prev.executeQuery();
            int index = 0;

            while (rs_prev.next()){
                float grades = rs_prev.getFloat("grados");
                String state = rs_prev.getString("estado");
                float humidity = rs_prev.getFloat("humedad");
                String date = rs_prev.getString("fecha");
                String consDate = rs_prev.getString("fecha_consulta");
                String ciudad = rs_prev.getString("nombre_ciudad");

                Tiempo weather = new Tiempo(ciudad, grades, state, humidity, LocalDate.parse(date), LocalDate.parse(consDate));
                prevision[index] = weather;
                index ++;
            }

            rs_city.close();
            rs_prev.close();
            st_city.close();
            st_prev.close();

        }catch (SQLException e){
            e.printStackTrace();
        }catch (CityNotFoundException e){

        }
        return prevision;
    }

    public Tiempo getWeatherCoor(float lat, float lon){
        ResultSet rs;
        Tiempo tiempo = null;
        try{
            PreparedStatement st = connect.prepareStatement("select * from HistorialCoordenada join tiempo using(id_tiempo) where (coordenadaX = "+ lat + " and coordenadaY = "+lon +") and fecha = date('now') order by id_tiempo desc LIMIT 1");
            rs = st.executeQuery();

            while(rs.next()){
                float grades = rs.getFloat("grados");
                String state = rs.getString("estado");
                float humidity = rs.getFloat("humedad");
                String date = rs.getString("fecha");
                String consDate = rs.getString("fecha_consulta");

                tiempo = new Tiempo(lat +" - "+ lon, grades, state, humidity, LocalDate.parse(date), LocalDate.parse(consDate));
            }



        }catch (SQLException e){
            e.printStackTrace();
        }
        return tiempo;
    }

    public Tiempo[] getWeatherDaysCoor(float lat, float lon){
        ResultSet rs;
        Tiempo[] prevision = new Tiempo[3];

        try {
            PreparedStatement st = connect.prepareStatement("select * from (select * from tiempo join historialCoordenada using(id_tiempo) where coordenadaX = "+ lat +" and coordenadaY = "+ lon +" order by id_tiempo ASC) group by fecha");
            rs = st.executeQuery();
            int indice = 0;
            while(rs.next()){
                float grades = rs.getFloat("grados");
                String state = rs.getString("estado");
                float humidity = rs.getFloat("humedad");
                String date = rs.getString("fecha");
                String consDate = rs.getString("fecha_consulta");

                Tiempo tiempo = new Tiempo(lat+" - "+ lon, grades, state, humidity, LocalDate.parse(date), LocalDate.parse(consDate));
                prevision[indice] = tiempo;
                indice++;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return prevision;
    }

    public void saveWeatherCoor(Tiempo tiempo, float lat, float lon){
        try{
            PreparedStatement st = connect.prepareStatement("insert into HistorialCoordenada (id_tiempo, coordenadaX, coordenadaY) values(last_insert_rowid(), ?, ?)");
            PreparedStatement st_tiempo = connect.prepareStatement("insert into tiempo (grados, estado, humedad, Fecha, fecha_consulta) values (?, ?, ?, ?, ?)");

            st_tiempo.setDouble(1, tiempo.getGrados());
            st_tiempo.setString(2, tiempo.getEstado());
            st_tiempo.setDouble(3, tiempo.getHumedad());
            st_tiempo.setString(4, tiempo.getFecha().toString());
            st_tiempo.setString(5, tiempo.getFecha_consulta().toString());

            st_tiempo.execute();

            st.setFloat(1, lat);
            st.setFloat(2, lon);
            st.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // METODO PARA GUARDAR UN TIEMPO DE UNA LOCALIZACION
    public void saveWeather(Tiempo tiempo, Localizacion localizacion) {
        ResultSet rs_exs;
        ResultSet rs_loc;
        try {
            // Comprobar localidad existente en bbdd, si no existe crear
            PreparedStatement st_exs = connect.prepareStatement("select COUNT() as cantidad from localizacion where (ROUND(coordenadaX, 5) = " + localizacion.getCoordenadas().getX() + " and ROUND(coordenadaY, 5) = " + localizacion.getCoordenadas().getY() + ") or  nombre_ciudad = '"+localizacion.getName()+"'");

            rs_exs = st_exs.executeQuery();
            int saved = rs_exs.getInt("cantidad");

            if (saved == 0) {
                // creamos localidad
                PreparedStatement st_cre_loc = connect.prepareStatement("insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values (? , ?,  ?)");

                st_cre_loc.setString(1, localizacion.getName().toLowerCase());
                st_cre_loc.setFloat(2, localizacion.getCoordenadas().getX());
                st_cre_loc.setFloat(3, localizacion.getCoordenadas().getY());

                st_cre_loc.execute();
            }

            // Crear tiempo
            PreparedStatement st_wt = connect.prepareStatement("insert into Tiempo (grados, estado, humedad, Fecha, fecha_consulta) values(?, ?, ?, ?, ?)");
            st_wt.setDouble(1, tiempo.getGrados());
            st_wt.setString(2, tiempo.getEstado());
            st_wt.setDouble(3, tiempo.getHumedad());
            st_wt.setString(4, tiempo.getFecha().toString());
            st_wt.setString(5, tiempo.getFecha_consulta().toString());

            st_wt.execute();

            // Crear en el historial
            PreparedStatement st_loc = connect.prepareStatement("select * from localizacion where (coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = "+ localizacion.getCoordenadas().getY() + ") or nombre_ciudad = '"+ localizacion.getName().toLowerCase()+"'");
            rs_loc = st_loc.executeQuery();
            float coorX = rs_loc.getFloat("coordenadaX");
            float coorY = rs_loc.getFloat("coordenadaY");

            PreparedStatement st_hist = connect.prepareStatement("insert into Historial (id_tiempo, coordenadaX, coordenadaY) values (last_insert_rowid(), ?, ?)");
            st_hist.setFloat(1, coorX);
            st_hist.setFloat(2, coorY);

            st_hist.execute();

            rs_exs.close();
            rs_loc.close();
            st_loc.close();
            st_hist.close();
            st_exs.close();
            st_wt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // METODO PARA ACTUALIZAR LA BASE DE DATOS BORRANDO TIEMPOS MAS VIEJOS AL DIA DE HOY
    public void updateWeatherDatabase() {
        try {
            PreparedStatement st_up = connect.prepareStatement("DELETE FROM tiempo WHERE fecha < datetime('now', '-' || 1 || ' days')");
            st_up.execute();
            st_up.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // *************************************************************************************************************************
    // ************************************************ FAVOURITE METHODS ******************************************************
    // *************************************************************************************************************************

    // METODO PARA GUARDAR UN FAVORITO
    public void saveFavourite(Localizacion localizacion) {
        try {
            // creamos favorito
            PreparedStatement st_cre_fav = connect.prepareStatement("insert into Favorito (coordenadaX, coordenadaY) values (?,  ?)");

            st_cre_fav.setDouble(1, localizacion.getCoordenadas().getX());
            st_cre_fav.setDouble(2, localizacion.getCoordenadas().getY());

            st_cre_fav.execute();

            st_cre_fav.close();
        } catch (SQLException e) {
            try {
                throw new AlreadyAddedException("La ciudad ya está en favoritos.");
            } catch (AlreadyAddedException alreadyAdded) {

            }
        }
    }

    // METODO PARA BORRAR UN FAVORITO
    public void deleteFavourite(Localizacion localizacion) {
        try {
            PreparedStatement st_del_fav = connect.prepareStatement("DELETE FROM favorito WHERE ROUND(coordenadaX,5) = " + localizacion.getCoordenadas().getX() + " and ROUND(coordenadaY,5) = " + localizacion.getCoordenadas().getY());

            st_del_fav.execute();

            st_del_fav.close();
        } catch (SQLException e) {
            e.printStackTrace();
       // } catch (CityNotFoundException e) {
        }
    }

    // METODO PARA LISTAR TODOS LOS FAVORITOS
    // Devuelve null si no tiene favoritos
    // Devuelve una lista con todos los favoritos
    public List<Localizacion> getFavourites() {
        ResultSet rs_favs;
        try {
            List<Localizacion> list = new ArrayList<>();
            PreparedStatement st_favs = connect.prepareStatement("select * from localizacion as loc join favorito as fav using (coordenadaX, coordenadaY)");
            rs_favs = st_favs.executeQuery();

            while (rs_favs.next())
                list.add(new Localizacion(rs_favs.getString("nombre_ciudad"), new Coordenadas(rs_favs.getFloat("coordenadaX"), rs_favs.getFloat("coordenadaY"))));

            rs_favs.close();
            st_favs.close();

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // *************************************************************************************************************************
    // **************************************************** TAG METHODS ********************************************************
    // *************************************************************************************************************************

    // METODO PARA GUARDAR UN TAG
    public void saveTag(Localizacion localizacion, String nombreTag) {
        try {
            // creamos tag
            PreparedStatement st_cre_tag = connect.prepareStatement("insert into Tag (nombre_tag, coordenadaX, coordenadaY) values (?, ?,  ?)");

            st_cre_tag.setString(1, nombreTag);
            st_cre_tag.setDouble(2, localizacion.getCoordenadas().getX());
            st_cre_tag.setDouble(3, localizacion.getCoordenadas().getY());

            st_cre_tag.execute();

            st_cre_tag.close();
        } catch (SQLException e) {
            try {
                throw new AlreadyAddedException("La ciudad ya tiene un tag asignado.");
            } catch (AlreadyAddedException alreadyAdded) {
            }
        }
    }

    // METODO PARA BORRAR UN TAG
    public void deleteTag(Localizacion localizacion, String nombreTag) {
        try {
            PreparedStatement st_del_tag = connect.prepareStatement("DELETE FROM tag WHERE (ROUND(coordenadaX,5) = " + localizacion.getCoordenadas().getX() + " and ROUND(coordenadaY,5) = " + localizacion.getCoordenadas().getY()+") and nombre_tag = '"+nombreTag+"'");

            st_del_tag.execute();

            st_del_tag.close();
        } catch (SQLException e) {
            e.printStackTrace();
        //} catch (CityNotFoundException e) {
        }
    }

    // METODO PARA LISTAR TODOS LOS TAGS
    // Devuelve null si no tiene ningún tag vinculado
    // Devuelve un mapa con todos los tags almacenados
    public Map<Localizacion, List<String>> getTags() {
        ResultSet rs_tags;
        try {
            Map<Localizacion, List<String>> map = new HashMap<>();
            PreparedStatement st_tags = connect.prepareStatement("select * from localizacion as loc join tag as t using (coordenadaX, coordenadaY)");
            rs_tags = st_tags.executeQuery();

            while (rs_tags.next()){
                String nombre = rs_tags.getString("nombre_ciudad");
                Localizacion localizacion = new Localizacion(nombre.substring(0,1).toUpperCase()+nombre.substring(1), new Coordenadas(rs_tags.getFloat("coordenadaX"), rs_tags.getFloat("coordenadaY")));

                if (!map.containsKey(localizacion)) {
                    // Creamos nueva entrada en el mapa
                    map.put(localizacion, new ArrayList<>());
                }
                map.get(localizacion).add(rs_tags.getString("nombre_tag"));
            }

            rs_tags.close();
            st_tags.close();

            return map;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // METODO PARA LISTAR TODOS LOS TAGS DE UNA CIUDAD
    // Devuelve null si no tiene ningun tag
    // Devuelve una lista con todos los tags
    public List<String> getTagsCity (Localizacion localizacion){
        ResultSet rs;
        try{
            PreparedStatement st = connect.prepareStatement("select nombre_tag from tag join localizacion using(coordenadaX, coordenadaY) where (ROUND(localizacion.coordenadaX,5) = "+localizacion.getCoordenadas().getX()+" and ROUND(coordenadaY,5) = "+ localizacion.getCoordenadas().getY()+") or nombre_ciudad = '"+localizacion.getName()+"'");
            rs = st.executeQuery();
            List<String> tags = new ArrayList<>();

            while(rs.next())
                tags.add(rs.getString("nombre_tag"));

            return tags;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // *************************************************************************************************************************
    // **************************************************** EXTRA METHODS ******************************************************
    // *************************************************************************************************************************

    // METODO PARA RECOGER LAS COORDENADAS DE UNA LOCALIZACION
    public Coordenadas getCoor(String nombre){
        ResultSet rs;
        Coordenadas coor = null;
        try {
            PreparedStatement st = connect.prepareStatement("select coordenadaX, coordenadaY from localizacion where nombre_ciudad = '"+nombre.toLowerCase()+"'");
            rs = st.executeQuery();

            if (rs.next())
                coor = new Coordenadas(rs.getFloat("coordenadaX"), rs.getFloat("coordenadaY"));

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coor;
    }

    // METODO PARA RECOGER EL NOMBRE DE UNA LOCALIZACION
    public String getNombre(Coordenadas coordenadas){
        ResultSet rs;
        String nombre = null;
        try{
            PreparedStatement st = connect.prepareStatement("select nombre_ciudad from localizacion where ROUND(coordenadaX, 5) = "+coordenadas.getX()+" and ROUND(coordenadaY, 5) = "+coordenadas.getY());
            rs = st.executeQuery();

            if (rs.next())
                nombre = rs.getString("nombre_ciudad");

            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombre;
    }
}