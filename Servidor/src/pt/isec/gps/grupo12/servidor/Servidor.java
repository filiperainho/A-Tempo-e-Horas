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
	public static final int PORTO_SERVIDOR = 1111;
	private static Servidor Servidor_INSTANCE = null;
	private UDPservidor socket;
	
	public Servidor(){
		try {
			this.socket = new UDPservidor(new DatagramSocket(PORTO_SERVIDOR));
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
					reencaminharMensagem((Mensagem)read);
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
	
	private void reencaminharMensagem(Mensagem read){
		GestorPedidos gestorPedidos = GestorPedidos.getInstance();
		MemberShip memberShip = MemberShip.getInstance();
		
		if(read instanceof MEnviarCor){
			gestorPedidos.addPedido(((MReencaminharCor) read).getRemetente(), ((MEnviarCor) read).getDestinatarios(), ((MEnviarCor) read).getRgb());
		}
		if(read instanceof MConfirmacaoRecepcao){
			gestorPedidos.addRecepcaoDeCor( ((MConfirmacaoRecepcao) read).getCodigoPedido(), ((MConfirmacaoRecepcao) read).getDestinatario(), ((MConfirmacaoRecepcao) read).getRecebida());
		}
		else if(read instanceof MLogin){
			String userName = ((MLogin) read).getNome();
			if(((MLogin) read).loginState()){
				memberShip.login( userName, ((MLogin) read).getPass());
			}
			else{
				memberShip.logout(userName);
			}
		}
	}

	public void responder(Mensagem escrever, int porto, InetAddress ip){
		try {
			byte[] obj = UDPservidor.transformObjectToByte(escrever);
			
			socket.write(obj, porto, ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized Servidor getInstance(){
    	if(Servidor_INSTANCE == null){
    		Servidor_INSTANCE = new Servidor();
    	}
    	return Servidor_INSTANCE;
    }
}
