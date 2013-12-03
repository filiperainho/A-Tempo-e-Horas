// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import java.util.List;



public interface RecebeMensagem {
    public abstract void relatorioRecebido(List<String> receberam, List<String> naoReceberam, List<String> offline, List<String> ignoraram);
    public abstract void corRecebida(String remetente, String rgb);
    public abstract void alteracaoDoEstadoDeLogin();
    public abstract void erroComunicacao();
}
