package pt.isec.gps.grupo12.mensagens;

public class MRespostaAdicaoContato extends Mensagem {
	private String nome;
	private String userName;
	private boolean resposta;
	
	public MRespostaAdicaoContato(boolean resposta, String nome, String userName){
		this.resposta = resposta;
		this.nome = nome;
		this.userName = userName;
	}
	
	public boolean getResposta(){
		return resposta;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getUserName(){
		return userName;
	}
	
}
