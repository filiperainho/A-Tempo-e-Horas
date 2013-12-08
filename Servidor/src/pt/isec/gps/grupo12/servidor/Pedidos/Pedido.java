// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Pedidos;

import java.util.ArrayList;
import java.util.List;

import pt.isec.gps.grupo12.mensagens.Constantes;


public class Pedido {
    private long idPedido;
    private String corRGB;
    private String remetente;
    private ArrayList<String> naoResponderam;
    private ArrayList<String> responderam;
    private ArrayList<String> offline;
    private ArrayList<String> ignoraramMensagem;
    private int sinalizacoes;
    private boolean isDone;
    
    public void addToResponderam(String username, boolean resposta) {
    	if(username == null)
    		return;
    	if(resposta){
	    	if(naoResponderam.remove(username)){
	    		responderam.add(username);
	    	}
    	}
    	else{
    		addToIgnoraram(username);
    	}
    }

    public void addToOffline(String username) {
    	if(username == null)
    		return;
    	if(naoResponderam.remove(username)){
    		offline.add(username);
    	}
    }

    public void addToIgnoraram(String username) {
    	if(username == null)
    		return;
    	
    	ignoraramMensagem.add(username);
    }
    
    public void incrementeSinalizacoes() {
        ++sinalizacoes;
        if(sinalizacoes == Constantes.MAXIMO_SINALIZACOES_SERVIDOR){
        	this.isDone = true;
        }
        System.out.println(" ::" + sinalizacoes + " " + isDone);
    }
    
    public Pedido(long IdPedido, String remetente, List<String> naoResponderam, String corRGB) {
    	this.idPedido = IdPedido;
    	this.remetente = remetente;
    	this.naoResponderam = new ArrayList<>(naoResponderam);
    	this.corRGB = corRGB;
    	this.naoResponderam = new ArrayList<>();
    	this.offline = new ArrayList<>();
    	this.ignoraramMensagem = new ArrayList<>();
    	this.isDone = false;
    }
    
    public boolean getIsDone(){
    	return isDone;
    }
    
    public long getIdPedido() {
        return idPedido;
    }

    public String getRemetente() {
        return remetente;
    }

    public List<String> getResponderam() {
        return responderam;
    }

    public List<String> getNaoResponderam() {
        return naoResponderam;
    }

    public List<String> getOffline() {
        return offline;
    }

    public List<String> getIgnoraramMensagem() {
        return ignoraramMensagem;
    }

    public long getSinalizacoes() {
        return sinalizacoes;
    }
    
    public String getCorRGB(){
    	return corRGB;
    }
}
