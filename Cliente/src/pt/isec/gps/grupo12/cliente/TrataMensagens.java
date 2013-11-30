// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import pt.isec.gps.grupo12.mensagens.MConfirmacaoRecepcao;
import pt.isec.gps.grupo12.mensagens.MEnviarCor;
import pt.isec.gps.grupo12.mensagens.MLogin;
import pt.isec.gps.grupo12.mensagens.Mensagem;


public class TrataMensagens implements EnviarMensagem{
    private RecebeMensagem recebeMensagem;
    
    public TrataMensagens(RecebeMensagem recebeMensagem){
        this.recebeMensagem = recebeMensagem;
    }
    
    public void startLoop(){
        
    }
    
    public void reencaminharMensagem(Mensagem m){
    	
    }
    
    void enviaRecepcaoDeCor(MConfirmacaoRecepcao relatorio){
    	
    }
    
    @Override
    public void enviaEstadoLogin(MLogin login){
    	
    }
    
    @Override
    public void enviaCor(MEnviarCor cor){
    	
    }
}
