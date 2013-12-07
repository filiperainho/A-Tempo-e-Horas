// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Pedidos;

import java.util.List;


public class Pedido {
    private long idPedido;
    private String corRGB;
    private String remetente;
    private List<String> naoResponderam;
    private List<String> responderam;
    private List<String> offline;
    private List<String> ignoraramMensagem;
    private int sinalizacoes;
    private boolean isDone;
    
    public Pedido(long IdPedido, String remetente, List<String> naoResponderam, String corRGB) {

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

    public void addToResponderam(String username, boolean resposta) {
    	if(username == null)
    		return;
    	int aux = getPosicaoDoNomeNoNaoResponderam(username);
    	if(aux == -1)
    		return;
    	naoResponderam.remove(aux);
    	responderam.add(username);
    }

    public void addToOffline(String username) {
    	if(username == null)
    		return;
    	int aux = getPosicaoDoNomeNoNaoResponderam(username);
    	if(aux == -1)
    		return;
    	naoResponderam.remove(aux);
    	offline.add(username);
    }

    public void addToIgnoraram(String username) {
    	if(username == null)
    		return;
    	
    	ignoraramMensagem.add(username);
    }
    public boolean getIsDone(){
    	return isDone;
    }

    public void incrementeSinalizacoes() {
        ++sinalizacoes;
    }
    
    public int getPosicaoDoNomeNoNaoResponderam(String username){
    	for(int i = 0; i < naoResponderam.size(); i++){
    		if(naoResponderam.get(i).equals(username))
    			return i;
    	}
    	return -1;
    }
}
