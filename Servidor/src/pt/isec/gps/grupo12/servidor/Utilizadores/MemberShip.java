// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Utilizadores;

import java.net.InetAddress;
import java.util.HashMap;


public class MemberShip {
	private static MemberShip MEMBERSHIP_INSTANCE = null;
    private FonteDados fonteDados;
    private HashMap<String, UtilizadorOnline> utilizadoresOnline;
    
    public MemberShip() {
        //this.fonteDados = new BaseDados(); 
        this.utilizadoresOnline = new HashMap<String, UtilizadorOnline>();
    }

    public boolean login(String user, String pass, int porto, InetAddress ip) {
    	if(fonteDados.userExiste(user) == true){
    		if(fonteDados.getPassword(user).compareTo(pass) == 0){
				UtilizadorOnline novo = new UtilizadorOnline(porto, ip, user);	    			
		    	utilizadoresOnline.put(user, novo);
		    	return true;//utilizador logadooo
    		}
    	}
        return false;//utilizador não existe ou password errada
    }

    public boolean logout(String user) {
    	if((UtilizadorOnline)utilizadoresOnline.get(user) != null){
    		utilizadoresOnline.remove(user);
    		return true;//logout com sucesso
    	}
        return false;
    }

    public boolean userExiste(String username) {    	
        return fonteDados.userExiste(username);
    }

    public boolean isUserOnline(String username) {
    	if((UtilizadorOnline)utilizadoresOnline.get(username) != null){
    		return true;//utilizador esta online
    	}
        return false;//utilizador esta offline
    }
    
    public UtilizadorOnline getUser(String nome){
        return (UtilizadorOnline)utilizadoresOnline.get(nome);
    }
    
    
    public static synchronized MemberShip getInstance(){
    	if(MEMBERSHIP_INSTANCE == null){
    		MEMBERSHIP_INSTANCE = new MemberShip();
    	}
    	return MEMBERSHIP_INSTANCE;
    }
}
