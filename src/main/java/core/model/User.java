package core.model;

import java.util.HashMap;
import java.util.LinkedList;

public class User {
    public int code;
    public LinkedList <Localizacion> favoritos; // Guardadas como localizaciones
    public HashMap<Localizacion, String> tags; // Solo aquí existe el nombrado propio del cliente


}
