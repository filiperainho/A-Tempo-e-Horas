// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.mensagens;


public class MEstadoLogin extends Mensagem {
	public static final int RESPOSTA_LOGIN = 0;
	public static final int RESPOSTA_LOGOUT = 1;
	public static final int RESPOSTA_SERVIDOR_ENCERRAR = 2;
	private int tipoResposta;
    private boolean estadoLogin;
    public MEstadoLogin(boolean estadoLogin, int tipoResposta){
        this.estadoLogin = estadoLogin;
        this.tipoResposta = tipoResposta;
    }
    
    public boolean getEstadoLogin(){
        return estadoLogin;
    }
    
    public int getTipoResposta(){
    	return tipoResposta;
    }
}
