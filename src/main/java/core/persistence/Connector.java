package core.persistence;

import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.exceptions.AlreadyAddedException;
import core.Exceptions.CityNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

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
            PreparedStatement st = connect.prepareStatement("select * from historial join tiempo using(id_tiempo) where (coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY() + ") and fecha = date('now') order by id_tiempo desc LIMIT 1");
            rs = st.executeQuery();

            if (rs.next()){
                float grades = rs.getFloat("grados");
                String state = rs.getString("estado");
                float humidity = rs.getFloat("humedad");
                String date = rs.getString("fecha");
                String consDate = rs.getString("fecha_consulta");

                weather = new Tiempo(grades, state, humidity, LocalDate.parse(date), LocalDate.parse(consDate));
            } else{
                throw new CityNotFoundException("No se tiene informacion sobre esta ciudad.");
            }

        } catch (SQLException | CityNotFoundException e) {
            e.printStackTrace();
        }
        return weather;
    }

    // METODO PARA COGER LA PREDICCION DE VARIOS DIAS DE UNA LOCALIZACION
    // Devuelve una lista vacía si no encuentra informacion
    // Devuelve la lista con los tiempos de cada dia que tengamos almacenados
    public List<Tiempo> getWeatherDays(Localizacion localizacion){
        List<Tiempo> prevision = new ArrayList<>();
        ResultSet rs_city;
        ResultSet rs_prev;
        try{
            PreparedStatement st_city = connect.prepareStatement("select COUNT() as cantidad from localizacion where coordenadaX = "+ localizacion.getCoordenadas().getX() +" and coordenadaY = "+localizacion.getCoordenadas().getY());
            PreparedStatement st_prev = connect.prepareStatement("select * from (select * from tiempo join historial using(id_tiempo) where coordenadaX = "+ localizacion.getCoordenadas().getX() +" and coordenadaY = "+ localizacion.getCoordenadas().getY() +" order by id_tiempo DESC) group by fecha;");

            rs_city = st_city.executeQuery();
            int saved = rs_city.getInt("cantidad");

            if (saved == 0) throw new CityNotFoundException("No se tienen datos de la ciudad");

            rs_prev = st_prev.executeQuery();

            while (rs_prev.next()){

                float grades = rs_prev.getFloat("grados");
                String state = rs_prev.getString("estado");
                float humidity = rs_prev.getFloat("humedad");
                String date = rs_prev.getString("fecha");
                String consDate = rs_prev.getString("fecha_consulta");

                Tiempo weather = new Tiempo(grades, state, humidity, LocalDate.parse(date), LocalDate.parse(consDate));
                prevision.add(weather);
            }

            return prevision;

        }catch (SQLException | CityNotFoundException e){
            e.printStackTrace();
        }
        return prevision;
    }

    // METODO PARA GUARDAR UN TIEMPO DE UNA LOCALIZACION
    public void saveWeather(Tiempo tiempo, Localizacion localizacion) {
        ResultSet rs_loc;
        try {
            // Comprobar localidad existente en bbdd, si no existe crear
            PreparedStatement st_loc = connect.prepareStatement("select COUNT(nombre_ciudad) as cantidad from localizacion where coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY());
            rs_loc = st_loc.executeQuery();
            int saved = rs_loc.getInt("cantidad");

            if (saved == 0) {
                // creamos localidad
                PreparedStatement st_cre_loc = connect.prepareStatement("insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values (? , ?,  ?)");

                st_cre_loc.setString(1, localizacion.getName());
                st_cre_loc.setDouble(2, localizacion.getCoordenadas().getX());
                st_cre_loc.setDouble(3, localizacion.getCoordenadas().getY());

                st_cre_loc.execute();
            }

            // Crear tiempo
            PreparedStatement st_time = connect.prepareStatement("insert into Tiempo (grados, estado, humedad, Fecha, fecha_consulta) values(?, ?, ?, ?, ?)");
            st_time.setDouble(1, tiempo.getGrados());
            st_time.setString(2, tiempo.getEstado());
            st_time.setDouble(3, tiempo.getHumedad());
            st_time.setString(4, tiempo.getFecha().toString());
            st_time.setString(5, tiempo.getFecha_consulta().toString());

            st_time.execute();

            // Crear en el historial
            PreparedStatement st_hist = connect.prepareStatement("insert into Historial (id_tiempo, coordenadaX, coordenadaY) values (last_insert_rowid(), ?, ?)");
            st_hist.setDouble(1, localizacion.getCoordenadas().getX());
            st_hist.setDouble(2, localizacion.getCoordenadas().getY());

            st_hist.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // METODO PARA ACTUALIZAR LA BASE DE DATOS BORRANDO TIEMPOS MAS VIEJOS AL DIA DE HOY
    public void updateWeatherDatabase() {
        try {
            PreparedStatement st_up = connect.prepareStatement("DELETE FROM tiempo WHERE fecha < datetime('now', '-' || 1 || ' days')");
            st_up.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // *************************************************************************************************************************
    // ************************************************ FAVOURITE METHODS ******************************************************
    // *************************************************************************************************************************

    // METODO PARA GUARDAR UN FAVORITO
    public void saveFavourite(Localizacion localizacion) {
        ResultSet rs_loc;
        try {
            PreparedStatement st_loc = connect.prepareStatement("select COUNT(nombre_ciudad) as cantidad from localizacion where coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY());
            rs_loc = st_loc.executeQuery();
            int exist = rs_loc.getInt("cantidad");

            if (exist == 0) {
                // No existe la ciudad a añadir a favoritos
                // creamos localidad
                PreparedStatement st_cre_loc = connect.prepareStatement("insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values (? , ?,  ?)");

                st_cre_loc.setString(1, localizacion.getName());
                st_cre_loc.setDouble(2, localizacion.getCoordenadas().getX());
                st_cre_loc.setDouble(3, localizacion.getCoordenadas().getY());

                st_cre_loc.execute();
            }
            // creamos favorito
            PreparedStatement st_cre_fav = connect.prepareStatement("insert into Favorito (coordenadaX, coordenadaY) values (?,  ?)");

            st_cre_fav.setDouble(1, localizacion.getCoordenadas().getX());
            st_cre_fav.setDouble(2, localizacion.getCoordenadas().getY());

            st_cre_fav.execute();

        } catch (SQLException e) {
            try {
                throw new AlreadyAddedException("La ciudad ya está en favoritos.");
            } catch (AlreadyAddedException alreadyAdded) {
                alreadyAdded.printStackTrace();
            }
        }
    }

    // METODO PARA BORRAR UN FAVORITO
    public void deleteFavourite(Localizacion localizacion) {
        ResultSet rs_exist;
        try {
            PreparedStatement st_exist = connect.prepareStatement("select COUNT() as cantidad from favorito where coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY());
            rs_exist = st_exist.executeQuery();
            int exist = rs_exist.getInt("cantidad");

            if (exist == 0) throw new CityNotFoundException("La ciudad no se encuentra entre los favoritos.");

            PreparedStatement st_del_fav = connect.prepareStatement("DELETE FROM favorito WHERE coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY());

            st_del_fav.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CityNotFoundException e) {
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

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // *************************************************************************************************************************
    // **************************************************** TAG METHODS ********************************************************
    // *************************************************************************************************************************

    // METODO PARA GUARDAR UN FAVORITO
    public void saveTag(Localizacion localizacion, String nombreTag) {
        ResultSet rs_loc;
        try {
            PreparedStatement st_loc = connect.prepareStatement("select COUNT() as cantidad from localizacion where coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY());
            rs_loc = st_loc.executeQuery();
            int exist = rs_loc.getInt("cantidad");

            if (exist == 0) {
                // No existe la ciudad a añadir tag
                // creamos localidad
                PreparedStatement st_cre_loc = connect.prepareStatement("insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values (? , ?,  ?)");

                st_cre_loc.setString(1, localizacion.getName());
                st_cre_loc.setDouble(2, localizacion.getCoordenadas().getX());
                st_cre_loc.setDouble(3, localizacion.getCoordenadas().getY());

                st_cre_loc.execute();
            }
            // creamos tag
            PreparedStatement st_cre_tag = connect.prepareStatement("insert into Tag (nombre_tag, coordenadaX, coordenadaY) values (?, ?,  ?)");

            st_cre_tag.setString(1, nombreTag);
            st_cre_tag.setDouble(2, localizacion.getCoordenadas().getX());
            st_cre_tag.setDouble(3, localizacion.getCoordenadas().getY());

            st_cre_tag.execute();
        } catch (SQLException e) {
            try {
                throw new AlreadyAddedException("La ciudad ya tiene un tag asignado.");
            } catch (AlreadyAddedException alreadyAdded) {
            }
        }
    }

    // METODO PARA BORRAR UN FAVORITO
    public void deleteTag(Localizacion localizacion) {
        ResultSet rs_exist;
        try {
            PreparedStatement st_exist = connect.prepareStatement("select COUNT() as cantidad from tag where coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY());
            rs_exist = st_exist.executeQuery();
            int exist = rs_exist.getInt("cantidad");

            if (exist == 0) throw new CityNotFoundException("La localización no tiene un tag asignado.");

            PreparedStatement st_del_tag = connect.prepareStatement("DELETE FROM tag WHERE coordenadaX = " + localizacion.getCoordenadas().getX() + " and coordenadaY = " + localizacion.getCoordenadas().getY());

            st_del_tag.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CityNotFoundException e) {
        }
    }

    // METODO PARA LISTAR TODOS LOS FAVORITOS
    // Devuelve null si no tiene ningún tag vinculado
    // Devuelve un mapa con todos los tags almacenados
    public Map<Localizacion, String> getTags() {
        ResultSet rs_tags;
        try {
            Map<Localizacion, String> map = new HashMap<>();
            PreparedStatement st_tags = connect.prepareStatement("select * from localizacion as loc join tag as t using (coordenadaX, coordenadaY)");
            rs_tags = st_tags.executeQuery();

            while (rs_tags.next())
                map.put(new Localizacion(rs_tags.getString("nombre_ciudad"), new Coordenadas(rs_tags.getFloat("coordenadaX"), rs_tags.getFloat("coordenadaY"))), rs_tags.getString("nombre_tag"));

            return map;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}