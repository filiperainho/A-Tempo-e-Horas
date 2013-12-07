// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import java.io.IOException;
import java.util.List;



public interface EnviarMensagem {
	public abstract void efectuarLogin(String userName, String password) throws IOException;
	public abstract void efectuarLogout() throws IOException;
    public abstract void enviarCor(String rgb, List<String> destinatarios) throws IOException;
    public abstract boolean isLogged();
    public abstract String getUserName();
    public abstract void terminarServico();
    public abstract List<Contato> getContatos();
    public abstract void adicionarContato(String userName);
    public abstract void removeContato(String userName);
}
