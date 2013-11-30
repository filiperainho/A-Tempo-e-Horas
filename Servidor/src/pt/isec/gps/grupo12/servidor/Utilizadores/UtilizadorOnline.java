// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Utilizadores;

import java.net.InetAddress;


public class UtilizadorOnline {
    private int porto;
    private InetAddress ip;
    private String username;
    
    public void UtilizadorOnline(int porto, InetAddress ip, String username) {
        this.porto = porto;
        this.ip = ip;
        this.username = username;
    }

    public int getPorto() {
        return porto;
    }

    public InetAddress getIP() {
        return ip;
    }

    public String getUsername() {
        return username;
    }
}
