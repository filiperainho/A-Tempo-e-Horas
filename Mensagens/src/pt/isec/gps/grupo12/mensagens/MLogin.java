// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.mensagens;


public class MLogin extends Mensagem {
    private String nome;
    private String pass;
    public void MLogin(String nome, String pass) {
        this.nome = nome;
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public String getPass() {
        return pass;
    }
}
