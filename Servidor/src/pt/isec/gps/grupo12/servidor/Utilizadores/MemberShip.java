// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Utilizadores;

import java.util.HashMap;

import pt.isec.gps.grupo12.servidor.Pedidos.GestorPedidos;


public class MemberShip {
	private static MemberShip MEMBERSHIP_INSTANCE = null;
    private FonteDados fonteDados;
    private HashMap<String, UtilizadorOnline> utilizadoresOnlie;
    public MemberShip() {
        //this.fonteDados = new FonteDados(); 
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
    
    
    public static synchronized MemberShip getInstance(){
    	if(MEMBERSHIP_INSTANCE == null){
    		MEMBERSHIP_INSTANCE = new MemberShip();
    	}
    	return MEMBERSHIP_INSTANCE;
    }
}
