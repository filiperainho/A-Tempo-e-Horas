package pt.isec.gps.grupo12.cliente;

public class Contato {
	private String userName;
	private String nome;
	
	public Contato(String userName, String nome){
		this.userName = userName;
		this.nome = nome;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getNome(){
		return nome;
	}
}
