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

import pt.isec.gps.grupo12.mensagens.Constantes;
import pt.isec.gps.grupo12.mensagens.MConfirmacaoRecepcao;
import pt.isec.gps.grupo12.mensagens.MEnviarCor;
import pt.isec.gps.grupo12.mensagens.MLogin;
import pt.isec.gps.grupo12.mensagens.Mensagem;


public class TrataMensagens extends Thread implements EnviarMensagem{
    private RecebeMensagem recebeMensagem;
    private UDPcliente socket;
    
    public TrataMensagens(RecebeMensagem recebeMensagem) throws SocketException, UnknownHostException{
        this.recebeMensagem = recebeMensagem;
        this.socket = new UDPcliente(new DatagramSocket(), InetAddress.getByName(Constantes.IP_SERVIDOR), Constantes.PORTO_SERVIDOR);
    }
    
    public void startLoop(){
        start();
    }
    
    @Override
    public void run(){
    	while(true){
    		try {
				DatagramPacket readPacket = socket.read();
				Object obj = UDPcliente.transformByteToObject(readPacket);
				if(obj instanceof Mensagem){
					reencaminharMensagem((Mensagem)obj);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void reencaminharMensagem(Mensagem m){
    	// Aqui diz o que acontece a mensagem
    	// se a mensagem for instanceof MEstadoLogin faz 
    	// recebeMensagem.receberEstadoLogin((MEstadoLogin )m)
    }
    
    void enviaRecepcaoDeCor(MConfirmacaoRecepcao relatorio){
    	
    }
    
    @Override
    public void enviaEstadoLogin(MLogin login) throws IOException{
    	byte[] enviar = UDPcliente.transformObjectToByte(login);
    	socket.write(enviar);
    }
    
    @Override
    public void enviaCor(MEnviarCor cor) throws IOException{
    	
    }
}
