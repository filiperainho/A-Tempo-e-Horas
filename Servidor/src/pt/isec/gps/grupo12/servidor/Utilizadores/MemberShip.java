// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Utilizadores;

import java.util.HashMap;


public class MemberShip {
    private FonteDados fonteDados;
    private HashMap<String, UtilizadorOnline> utilizadoresOnlie;
    public void MemberShip(FonteDados fonteDados) {
        this.fonteDados = fonteDados; 
        this.utilizadoresOnlie = new HashMap<>();
    }

    public boolean login(String user, String pass) {
        return false;
    }

    public boolean logout(String user) {
        return false;
    }

    public boolean userExiste(String username) {
        return false;
    }

    public boolean isUserOnline(String username) {
        return false;
    }
    
    public UtilizadorOnline getUser(String nome){
        return null;
    }
}
