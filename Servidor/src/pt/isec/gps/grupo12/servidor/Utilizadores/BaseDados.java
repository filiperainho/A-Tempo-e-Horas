package pt.isec.gps.grupo12.servidor.Utilizadores;

import java.util.ArrayList;

public class BaseDados implements FonteDados {
	
	class User{
		String nome;
		String password;
		User(String n, String p){
			nome = n;
			password = p;
		}
	}
	
	ArrayList<User> lista;

	public BaseDados(){
		lista = new ArrayList<>();
		lista.add(new User("assuncao", "p"));
		lista.add(new User("rainho", "p"));
		lista.add(new User("david", "p"));
		lista.add(new User("erbi", "p"));
		lista.add(new User("cadima", "p"));
		System.out.println("BaseDados criada!");
	}
	
	@Override
	public String getPassword(String username) {
		for(User s : lista){
			if(s.nome.equals(username)){
				return s.password;
			}
		}
		return null;
	}

	@Override
	public boolean userExiste(String username) {
		for(User s : lista){
			if(s.nome.equals(username)){
				return true;
			}
		}
		return false;
	}

}
