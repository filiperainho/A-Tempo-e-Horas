// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.mensagens;


public class MEstadoLogin extends Mensagem {
    private boolean estadoLogin;
    public MEstadoLogin(boolean estadoLogin){
        this.estadoLogin = estadoLogin;
    }
    
    public boolean getEstadoLogin(){
        return estadoLogin;
    }
}
