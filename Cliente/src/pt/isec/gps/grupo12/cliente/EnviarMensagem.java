// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import java.io.IOException;
import java.util.List;

import pt.isec.gps.grupo12.mensagens.MEnviarCor;
import pt.isec.gps.grupo12.mensagens.MLogin;



public interface EnviarMensagem {
	public abstract void efectuarLogin(String userName, String password) throws IOException;
	public abstract void efectuarLogout() throws IOException;
    public abstract void enviarCor(String rgb, List<String> destinatarios) throws IOException;
    public abstract boolean isLogged();
    public abstract String getUserName();
    public abstract void terminarServico();
}
