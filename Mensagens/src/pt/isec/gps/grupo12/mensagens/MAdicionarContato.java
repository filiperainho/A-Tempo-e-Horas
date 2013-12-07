package pt.isec.gps.grupo12.mensagens;

public class MAdicionarContato extends Mensagem {
	private String quemAdiciona;
	private String userNameAdicionar;
	
	public MAdicionarContato(String quemAdiciona, String userNameAdicionar){
		this.quemAdiciona = quemAdiciona;
		this.userNameAdicionar = userNameAdicionar;
	}
	
	public String getQueAdiciona(){
		return quemAdiciona;
	}
	
	public String getUserNameAdicionar(){
		return userNameAdicionar;
	}
}
