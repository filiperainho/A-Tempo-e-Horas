// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import pt.isec.gps.grupo12.mensagens.MEstadoLogin;
import pt.isec.gps.grupo12.mensagens.MReencaminharCor;
import pt.isec.gps.grupo12.mensagens.MRelatorio;



public interface RecebeMensagem {
    public abstract void receberRelatorio(MRelatorio relatorio);
    public abstract void receberEstadoLogin(MEstadoLogin login);
    public abstract void receberCor(MReencaminharCor cor);
}
