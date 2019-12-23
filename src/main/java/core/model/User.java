package core.model;

import java.util.HashMap;
import java.util.LinkedList;

/*
 * Patron singleton
 */
public class User {
    private int code; //Codigo unico del cliente
    private static User user;
    private LinkedList <Localizacion> favoritos; // Guardadas como localizaciones
    private HashMap<Localizacion, String> tags; // Solo aquí existe el nombrado propio del cliente

    private User(){
        favoritos = new LinkedList<>();
        tags = new HashMap<>();
    }

    public static User getSingletonInstance(){
        if (user == null)
            user = new User();
        return user;
    }

    public void addFavorito(String ciudad){
        favoritos.add(new Localizacion(ciudad));
    }


}
