// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Utilizadores;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;


public class MemberShip {
    private FonteDados fonteDados;
    private HashMap<String, UtilizadorOnline> utilizadoresOnline;
    public void MemberShip(FonteDados fonteDados) {
        this.fonteDados = fonteDados; 
        this.utilizadoresOnline = new HashMap<String, UtilizadorOnline>();
    }

    public boolean login(String user, String pass, int porto, String ip) {
    	if(fonteDados.userExiste(user) == true){
    		if(fonteDados.getPassword(user).compareTo(pass) == 0){
    			InetAddress ip_inet;
				try {
					ip_inet = InetAddress.getByName("10.0.0.1");
					UtilizadorOnline novo = new UtilizadorOnline(porto, ip_inet, user);	    			
	    			utilizadoresOnline.put(user, novo);
	    			return true;//utilizador logadooo
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}    			
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
}
