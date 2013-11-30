// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Pedidos;

import java.util.ArrayList;
import java.util.List;

import pt.isec.gps.grupo12.mensagens.MReencaminharCor;
import pt.isec.gps.grupo12.mensagens.MRelatorio;
import pt.isec.gps.grupo12.servidor.Servidor;
import pt.isec.gps.grupo12.servidor.Utilizadores.MemberShip;
import pt.isec.gps.grupo12.servidor.Utilizadores.UtilizadorOnline;


public class GestorPedidos extends Thread{
	private static GestorPedidos GESTOR_PEDIDOS_INSTANCE = null;
	private static long ID_GENERATOR = 0;
    private ArrayList<Pedido> pedidos;
    public GestorPedidos() {
        pedidos = new ArrayList<>();
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
    		pedidos.add(new Pedido(++ID_GENERATOR, remetente, destinatarios, corRGB));
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
    				return;
    			}
    		}
    	}
    }

    public void startLoop() {
    	start();
    }
    
    @Override
    public void run(){
    	while(true){
    		sinalizar();
    		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    
    
    private void sinalizar(){
    	synchronized (pedidos) {
    		for(Pedido p : pedidos){
    			p.incrementeSinalizacoes();
    			if(p.getIsDone()){
    				responderAoPedido(p);
    			}
    		}
    	}
    }
    
    private void responderAoPedido(Pedido p){
    	MRelatorio relatorio = new MRelatorio(
    			p.getResponderam(),
    			p.getNaoResponderam(),
    			p.getOffline(),
    			p.getIgnoraramMensagem());
    	
    	MemberShip memberShip = MemberShip.getInstance();
    	if(!memberShip.isUserOnline(p.getRemetente())){
    		removerPedido(p);
    		return;
    	}
    	
    	
    	UtilizadorOnline user = memberShip.getUser(p.getRemetente());
    	Servidor.getInstance().responder(relatorio, user.getPorto(), user.getIP());
    	
    	removerPedido(p);
    }
    
    private void removerPedido(Pedido p){
    	synchronized (pedidos) {
    		pedidos.remove(p);
    	}
    }
    
    
    public static synchronized GestorPedidos getInstance(){
    	if(GESTOR_PEDIDOS_INSTANCE == null){
    		GESTOR_PEDIDOS_INSTANCE = new GestorPedidos();
    	}
    	return GESTOR_PEDIDOS_INSTANCE;
    }
}
