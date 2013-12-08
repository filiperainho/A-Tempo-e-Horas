// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Pedidos;

import java.util.ArrayList;
import java.util.List;

import pt.isec.gps.grupo12.mensagens.Constantes;
import pt.isec.gps.grupo12.mensagens.MRelatorio;
import pt.isec.gps.grupo12.servidor.Servidor;
import pt.isec.gps.grupo12.servidor.Utilizadores.MemberShip;
import pt.isec.gps.grupo12.servidor.Utilizadores.UtilizadorOnline;


public class GestorPedidos extends Thread{
	private static GestorPedidos GESTOR_PEDIDOS_INSTANCE = null;
	private static long ID_GENERATOR = 0;
    private ArrayList<Pedido> pedidos;
    
    private GestorPedidos() {
        pedidos = new ArrayList<>();
        System.out.println("GestorPedidos criado!");
    }

    public void addPedido(String remetente, List<String> destinatarios, String corRGB) {
    	MemberShip memberShip = MemberShip.getInstance();
    	
    	if(!memberShip.userExiste(remetente)){
    		return;
    	}
    	if(!memberShip.isUserOnline(remetente)){
    		return;
    	}
    	
    	synchronized (pedidos) {
    		Pedido p = new Pedido(++ID_GENERATOR, remetente, destinatarios, corRGB);
    		for(String user : destinatarios){
    			boolean online = MemberShip.getInstance().isUserOnline(user);
    			if(!online){
    				p.addToOffline(user);
    			}
    		}
    		pedidos.add(p);
    		System.out.println("Adicionado pedido " + p.getIdPedido());
		}
    }
    
    public void addRecepcaoDeCor(long idPedido, String destinatario, boolean recebida){
    	if(!MemberShip.getInstance().isUserOnline(destinatario)){
    		return;
    	}
    	
    	synchronized (pedidos) {
    		for(Pedido p : pedidos){
    			if(p.getIdPedido() == idPedido){
    				p.addToResponderam(destinatario, recebida);
    				System.out.println("Recebida confirmacao de "+ destinatario + " do pedido " + idPedido + " com valor " + recebida);
    				return;
    			}
    		}
    	}
    }
    
    @Override
    public void run(){
    	while(true){
    		sinalizar();
    		try {
				Thread.sleep(Constantes.THREAD_SLEEP);
			} catch (InterruptedException e) {
			}
    	}
    }
    
    
    
    private void sinalizar(){
    	ArrayList<Pedido> ped = new ArrayList<>();
    	synchronized (pedidos) {
    		for(Pedido p : pedidos){
    			p.incrementeSinalizacoes();
    			if(p.getIsDone()){
    				responderAoPedido(p);
    				ped.add(p);
    			}
    		}
    	}
    	removerPedidos(ped);
    }
    
    private void responderAoPedido(Pedido p){
    	MRelatorio relatorio = new MRelatorio(
    			p.getResponderam(),
    			p.getNaoResponderam(),
    			p.getOffline(),
    			p.getIgnoraramMensagem());
    	
    	MemberShip memberShip = MemberShip.getInstance();
    	if(!memberShip.isUserOnline(p.getRemetente())){
    		System.out.println("User offline");
    		return;
    	}
    	System.out.println("entrei");
    	UtilizadorOnline user = memberShip.getUser(p.getRemetente());
    	Servidor.getInstance().responder(relatorio, user.getPorto(), user.getIP(), user.getUsername());
    	System.out.println("Resposta para o pedido " + p.getIdPedido() + " enviada!");
    }
    
    private void removerPedidos(ArrayList<Pedido> ped){
    	synchronized (pedidos) {
    		for(Pedido p : ped){
    			pedidos.remove(p);
    			System.out.println("Removido pedido " + p.getIdPedido());
    		}
    	}
    }
    
    
    public static synchronized GestorPedidos getInstance(){
    	if(GESTOR_PEDIDOS_INSTANCE == null){
    		GESTOR_PEDIDOS_INSTANCE = new GestorPedidos();
    		GESTOR_PEDIDOS_INSTANCE.start();
    	}
    	return GESTOR_PEDIDOS_INSTANCE;
    }
}
