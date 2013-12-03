// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import pt.isec.gps.grupo12.mensagens.Constantes;
import pt.isec.gps.grupo12.mensagens.MConfirmacaoRecepcao;
import pt.isec.gps.grupo12.mensagens.MEnviarCor;
import pt.isec.gps.grupo12.mensagens.MLogin;
import pt.isec.gps.grupo12.mensagens.Mensagem;


public class TrataMensagens extends Thread implements EnviarMensagem{
    private RecebeMensagem recebeMensagem;
    private UDPcliente socket;
    private String userLogged;
    private boolean isLogged;
    private boolean sair;
    
    public TrataMensagens(RecebeMensagem recebeMensagem) throws SocketException, UnknownHostException{
        this.recebeMensagem = recebeMensagem;
        this.userLogged = null;
        this.isLogged = false;
        this.socket = new UDPcliente(new DatagramSocket(), InetAddress.getByName(Constantes.IP_SERVIDOR), Constantes.PORTO_SERVIDOR);
    }
    
    public void startLoop(){
        start();
    }
    
    @Override
    public void run(){
    	sair = false;
    	try {
			socket.setSoTimeout(Constantes.CLIENTE_SOCKET_TIMEOUT);
		} catch (SocketException e1) {
			recebeMensagem.erroComunicacao();
			sair = true;
		}
    	while(!sair){
    		try {
				DatagramPacket readPacket = socket.read();
				Object obj = UDPcliente.transformByteToObject(readPacket);
				if(obj instanceof Mensagem){
					reencaminharMensagem((Mensagem)obj);
				}
			} catch (IOException e) {
				recebeMensagem.erroComunicacao();
				sair = true;
			} catch (ClassNotFoundException e) {
				continue;
			}
    	}
    }
    
    public void reencaminharMensagem(Mensagem m){
    	// ver rencaminhar no servidor
    	// Aqui diz o que acontece a mensagem
    	// se a mensagem for instanceof MEstadoLogin faz 
    	// recebeMensagem.receberEstadoLogin((MEstadoLogin )m)
    }
    
    
    
    // Métodos para a parte gráfica
    @Override
    public boolean isLogged(){
    	return this.isLogged;
    }
    @Override
    public void efectuarLogin(String userName, String password) throws IOException{
    	MLogin mensagem = new MLogin(userName, password, true);
    	byte[] enviar = UDPcliente.transformObjectToByte(mensagem);
    	socket.write(enviar);
    }
    
    @Override
    public void efectuarLogout() throws IOException{
    	MLogin mensagem = new MLogin(userLogged, null, false);
    	byte[] enviar = UDPcliente.transformObjectToByte(mensagem);
    	socket.write(enviar);
    }
    
    @Override
    public void enviarCor(String rgb, List<String> destinatarios) throws IOException{
    	MEnviarCor mensagem = new MEnviarCor(rgb, userLogged, destinatarios);
    	byte[] enviar = UDPcliente.transformObjectToByte(mensagem);
    	socket.write(enviar);
    }
}
