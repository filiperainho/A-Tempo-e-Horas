// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.mensagens;


public class MLogin extends Mensagem {
	private boolean login;
    private String nome;
    private String pass;
    public MLogin(String nome, String pass, boolean login) {
        this.nome = nome;
        this.pass = pass;
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public String getPass() {
        return pass;
    }
    public boolean loginState(){
    	return login;
    }
}
