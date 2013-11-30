// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import pt.isec.gps.grupo12.mensagens.MEnviarCor;
import pt.isec.gps.grupo12.mensagens.MLogin;
import pt.isec.gps.grupo12.mensagens.MRelatorio;



public interface EnviarMensagem {
    public abstract void receberRelatorio(MRelatorio relatorio);
    public abstract void receberEstadoLogin(MLogin login);
    public abstract void receberCor(MEnviarCor cor);
}
