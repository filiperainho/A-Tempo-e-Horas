package pt.isec.gps.grupo12.servidor.Utilizadores;

import java.util.ArrayList;

public class BaseDadosServidor implements FonteDadosServidor {
	
	class User{
		String nome;
		String userName;
		String password;
		User(String n, String nn, String p){
			nome = n;
			userName = nn;
			password = p;
		}
	}
	
	ArrayList<User> lista;

	public BaseDadosServidor(){
		lista = new ArrayList<>();
		lista.add(new User("Filipe Adduncao", "assuncao", "p"));
		lista.add(new User("Filipe Rainho", "rainho", "p"));
		lista.add(new User("David Goncalves", "david", "p"));
		lista.add(new User("Erbi Silva", "erbi", "p"));
		lista.add(new User("Samuel Cadima", "cadima", "p"));
		System.out.println("BaseDados criada!");
	}
	
	@Override
	public String getPassword(String username) {
		for(User s : lista){
			if(s.userName.equals(username)){
				return s.password;
			}
		}
		return null;
	}

	@Override
	public boolean userExiste(String username) {
		for(User s : lista){
			if(s.userName.equals(username)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getNome(String username) {
		for(User s : lista){
			if(s.userName.equals(username)){
				return s.nome;
			}
		}
		return null;
	}

}
