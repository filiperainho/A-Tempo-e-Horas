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
			System.out.println("Socket UDP criado com sucesso!");
		} catch (SocketException e) {
			System.err.println("Socket UDP não foi criado com sucesso!");
			System.exit(-5);
		}
		System.out.println("Servidor criado!");
	}
	
	public void startLoop(){
		System.out.println("Iniciado ciclo de leitura de mensagens!\n\n");
		while(true){
			try {
				DatagramPacket readPacket = socket.read();
				Object read;
				
				read = UDPservidor.transformByteToObject(readPacket);
			
				if(read instanceof Mensagem){
					reencaminharMensagem((Mensagem)read, readPacket);
				}
				
			} catch (IOException e) {
				System.err.println("IOException while(true) startLoop()");
			} catch (ClassNotFoundException e) {
				System.err.println("ClassNotFoundException while(true) startLoop()");
			}
		}
	}
	
	private void reencaminharMensagem(Mensagem read, DatagramPacket readPacket){
		GestorPedidos gestorPedidos = GestorPedidos.getInstance();
		MemberShip memberShip = MemberShip.getInstance();
		InetAddress ip = readPacket.getAddress();
		int porto = readPacket.getPort();
		
		if(read instanceof MEnviarCor){
			gestorPedidos.addPedido(((MEnviarCor) read).getRemetente(), ((MEnviarCor) read).getDestinatarios(), ((MEnviarCor) read).getRgb());
			System.out.println("Recebido MEnviarCor " + ((MEnviarCor) read).getRemetente() + " " + ((MEnviarCor) read).getRgb() + ((MEnviarCor) read).getDestinatarios());
		}
		
		if(read instanceof MConfirmacaoRecepcao){
			gestorPedidos.addRecepcaoDeCor( ((MConfirmacaoRecepcao) read).getCodigoPedido(), ((MConfirmacaoRecepcao) read).getDestinatario(), ((MConfirmacaoRecepcao) read).getRecebida());
			System.out.println("Recebido MConfirmacaoRecepcao " + ((MConfirmacaoRecepcao) read).getCodigoPedido() + " " + ((MConfirmacaoRecepcao) read).getDestinatario() + " " + ((MConfirmacaoRecepcao) read).getRecebida());
		}
		
		else if(read instanceof MLogin){
			String userName = ((MLogin) read).getNome();
			System.out.println("Recebido MLogin " + userName);
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
			System.out.println("Enviado login=" + resposta + " para o utilizador " + userName);
		}
		
		else if(read instanceof MAdicionarContato){
			System.out.println("Recebido MAdicionarContato " + ((MAdicionarContato) read).getQueAdiciona() + " " + ((MAdicionarContato) read).getUserNameAdicionar());
			boolean resposta = memberShip.userExiste(((MAdicionarContato) read).getUserNameAdicionar());
			String userName = ((MAdicionarContato) read).getUserNameAdicionar();
			if(resposta){
				String nome = memberShip.getNomeComUserName(userName);
				MRespostaAdicaoContato mRespostaAdicaoContato = new MRespostaAdicaoContato(resposta, userName, nome);
				responder(mRespostaAdicaoContato, porto, ip, ((MAdicionarContato) read).getQueAdiciona());
			}
			else{
				MRespostaAdicaoContato mRespostaAdicaoContato = new MRespostaAdicaoContato(resposta, userName, null);
				responder(mRespostaAdicaoContato, porto, ip, ((MAdicionarContato) read).getQueAdiciona());
			}
			System.out.println("Enviado adicionarContato=" + resposta + " para o utilizador " + userName);
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
	
	public static void main(String args[]){
		MemberShip.getInstance();
		GestorPedidos.getInstance();
		Servidor.getInstance().startLoop();
	}
}
