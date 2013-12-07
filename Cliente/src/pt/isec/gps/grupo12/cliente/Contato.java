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
	
	@Override
	public int hashCode() {
		return 31*userName.hashCode() + 31*nome.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Contato){
			Contato n = (Contato)o;
			return n.getNome().equals(nome) && n.getUserName().equals(userName);
		}
		return false;
	}
}
