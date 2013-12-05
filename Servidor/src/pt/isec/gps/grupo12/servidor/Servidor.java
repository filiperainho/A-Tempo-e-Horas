package pt.isec.gps.grupo12.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import pt.isec.gps.grupo12.mensagens.*;
import pt.isec.gps.grupo12.servidor.Pedidos.GestorPedidos;
import pt.isec.gps.grupo12.servidor.Utilizadores.MemberShip;

public class Servidor {
	private static Servidor SERVIDOR_INSTANCE = null;
	private UDPservidor socket;
	
	private Servidor(){
		try {
			this.socket = new UDPservidor(new DatagramSocket(Constantes.PORTO_SERVIDOR));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startLoop(){
		while(true){
			try {
				DatagramPacket readPacket = socket.read();
				Object read;
			
				read = UDPservidor.transformByteToObject(readPacket);
			
				if(read instanceof Mensagem){
					reencaminharMensagem((Mensagem)read, readPacket);
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
	
	private void reencaminharMensagem(Mensagem read, DatagramPacket readPacket){
		GestorPedidos gestorPedidos = GestorPedidos.getInstance();
		MemberShip memberShip = MemberShip.getInstance();
		InetAddress ip = readPacket.getAddress();
		int porto = readPacket.getPort();
		
		if(read instanceof MEnviarCor){
			gestorPedidos.addPedido(((MReencaminharCor) read).getRemetente(), ((MEnviarCor) read).getDestinatarios(), ((MEnviarCor) read).getRgb());
		}
		
		if(read instanceof MConfirmacaoRecepcao){
			gestorPedidos.addRecepcaoDeCor( ((MConfirmacaoRecepcao) read).getCodigoPedido(), ((MConfirmacaoRecepcao) read).getDestinatario(), ((MConfirmacaoRecepcao) read).getRecebida());
		}
		
		else if(read instanceof MLogin){
			String userName = ((MLogin) read).getNome();
			boolean resposta = false;
			if(((MLogin) read).isLoginIn()){
				resposta = memberShip.login( userName, ((MLogin) read).getPass(), porto, ip);
				MEstadoLogin mEstadoLogin = new MEstadoLogin(resposta, MEstadoLogin.RESPOSTA_LOGIN);
				responder(mEstadoLogin, porto, ip, userName);
			}
			else{
				resposta = memberShip.logout(userName);
				MEstadoLogin mEstadoLogin = new MEstadoLogin(resposta, MEstadoLogin.RESPOSTA_LOGOUT);
				responder(mEstadoLogin, porto, ip, userName);
			}
		}
	}

	public void responder(Mensagem escrever, int porto, InetAddress ip, String destinatario){
		try {
			byte[] obj = UDPservidor.transformObjectToByte(escrever);
			
			socket.write(obj, porto, ip);
		} catch (IOException e) {
			System.out.println("O utilizador " + destinatario + " saiu!");
			MemberShip.getInstance().logout(destinatario);
		}
	}
	
	public static synchronized Servidor getInstance(){
    	if(SERVIDOR_INSTANCE == null){
    		SERVIDOR_INSTANCE = new Servidor();
    	}
    	return SERVIDOR_INSTANCE;
    }
}
