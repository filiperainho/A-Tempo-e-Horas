package pt.isec.gps.grupo12.servidor;

import java.net.DatagramSocket;
import java.net.SocketException;

import pt.isec.gps.grupo12.servidor.Pedidos.GestorPedidos;
import pt.isec.gps.grupo12.servidor.Utilizadores.MemberShip;

public class Servidor {
	private GestorPedidos gestorPedidos;
	private MemberShip memberShip;
	private UDPservidor socket;
	
	public Servidor(int porto) throws SocketException{
		this.socket = new UDPservidor(new DatagramSocket(porto));
		this.memberShip = new MemberShip();
		this.gestorPedidos = new GestorPedidos();
	}
	
	public void startLoop(){
		
		while(true){
			
		}
		
		
	}

}
