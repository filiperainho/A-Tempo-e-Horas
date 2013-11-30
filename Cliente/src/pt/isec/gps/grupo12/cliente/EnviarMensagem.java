// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import java.io.IOException;

import pt.isec.gps.grupo12.mensagens.MEnviarCor;
import pt.isec.gps.grupo12.mensagens.MLogin;



public interface EnviarMensagem {
    public abstract void enviaEstadoLogin(MLogin login) throws IOException;
    public abstract void enviaCor(MEnviarCor cor) throws IOException;
}
