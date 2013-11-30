// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Pedidos;

import java.util.List;


public class Pedido {
    private long idPedido;
    private String remetente;
    private List<String> naoResponderam;
    private List<String> responderam;
    private List<String> offline;
    private List<String> ignoraramMensagem;
    private int sinalizacoes;
    public void Pedido(long IdPedido, String remetente, List<String> naoResponderam) {

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

    public void addToResponderam(String username) {
    }

    public void addToOffline(String username) {
    }

    public void addToIgnoraram(String username) {
    }

    public void incrementeSinalizacoes() {
        ++sinalizacoes;
    }
}
