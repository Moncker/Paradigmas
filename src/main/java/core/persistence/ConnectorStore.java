package core.persistence;

import core.model.Coordenadas;
import core.model.Localizacion;
import core.model.Tiempo;
import core.persistence.exceptions.AlreadyAddedException;
import core.persistence.exceptions.CityNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectorStore {

    String url = "paradigmas.db";
    Connection connect;

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
            Logger.getLogger(ConnectorStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // *************************************************************************************************************************
    // ************************************************** WEATHER METHODS ******************************************************
    // *************************************************************************************************************************

    // METODO PARA COGER EL TIEMPO  ACTUAL GUARDADO EN LA BBDD DE UNA LOCALIZACION
    public Tiempo getWeather(Localizacion localizacion) {
        ResultSet rs;
        Tiempo weather = null;
        try {
            PreparedStatement st = connect.prepareStatement("select * from historial as h join tiempo as t using(id_tiempo) where (h.coordenadaX = " + localizacion.getCoordenadas().getX() + " and h.coordenadaY = " + localizacion.getCoordenadas().getY() + ") and t.fecha = (SELECT MAX(fecha) from tiempo as tiem where tiem.id_tiempo = h.id_tiempo) ORDER BY fecha DESC ");
            rs = st.executeQuery();

            if (rs.isClosed()) throw new CityNotFoundException("No se tienen resultados de esa búsqueda");

            float grades = rs.getFloat("grados");
            String state = rs.getString("estado");
            float humidity = rs.getFloat("humedad");
            String date = rs.getString("fecha");

            weather = new Tiempo(grades, state, humidity, LocalDate.parse(date));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CityNotFoundException ex) {
            return null;
        }
        return weather;
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
            PreparedStatement st_time = connect.prepareStatement("insert into Tiempo (grados, estado, humedad, Fecha) values(?, ?, ?, ?)");
            st_time.setDouble(1, tiempo.getGrados());
            st_time.setString(2, tiempo.getEstado());
            st_time.setDouble(3, tiempo.getHumedad());
            st_time.setString(4, tiempo.getFecha().toString());

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

    // METODO PARA ACTUALIZAR LA BASE DE DATOS BORRANDO TIEMPOS MAS VIEJOS DE 3 DIAS
    public void updateWeatherDatabase() {
        try {
            PreparedStatement st_up = connect.prepareStatement("DELETE FROM tiempo WHERE fecha <= datetime('now', '-' || 3 || ' days')");
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
            }
        }
    }

    //METODO PARA BORRAR UN FAVORITO
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

    //METODO PARA LISTAR TODOS LOS FAVORITOS
    public List<Localizacion> getFavourites() {
        ResultSet rs_favs;
        try {
            List<Localizacion> list = new ArrayList<>();
            PreparedStatement st_favs = connect.prepareStatement("select * from localizacion as loc join favorito as fav using (coordenadaX, coordenadaY)");
            rs_favs = st_favs.executeQuery();

            while (rs_favs.next())
                list.add(new Localizacion(rs_favs.getString("nombre_ciudad"), new Coordenadas(rs_favs.getDouble("coordenadaX"), rs_favs.getDouble("coordenadaY"))));

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

    //METODO PARA BORRAR UN FAVORITO
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

    //METODO PARA LISTAR TODOS LOS FAVORITOS
    public Map<Localizacion, String> getTags() {
        ResultSet rs_tags;
        try {
            Map<Localizacion, String> map = new HashMap<>();
            PreparedStatement st_tags = connect.prepareStatement("select * from localizacion as loc join tag as t using (coordenadaX, coordenadaY)");
            rs_tags = st_tags.executeQuery();

            while (rs_tags.next())
                map.put(new Localizacion(rs_tags.getString("nombre_ciudad"), new Coordenadas(rs_tags.getDouble("coordenadaX"), rs_tags.getDouble("coordenadaY"))), rs_tags.getString("nombre_tag"));

            return map;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}