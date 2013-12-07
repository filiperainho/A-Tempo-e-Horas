package pt.isec.gps.grupo12.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseDadosCliente implements FonteDadosCliente{
	
	private HashMap<String, ArrayList<Contato>> contatos;
	
	public BaseDadosCliente(){
		contatos = new HashMap<String, ArrayList<Contato>>();
	}
	
	@Override
	public List<Contato> getContatos(String userOnline){
		if(contatos.containsKey(userOnline)){
			return contatos.get(userOnline);
		}
		return null;
	}
	
	@Override
    public void adicionarContato(String userOnline, String userName, String nome){
    	if(contatos.containsKey(userOnline)){
    		Contato novo = new Contato(userName, nome);
    		if(!contatos.get(userOnline).contains(novo)){
    			contatos.get(userOnline).add(novo);
    		}
    	}
    	else{
    		ArrayList<Contato> novos = new ArrayList<Contato>();
    		novos.add(new Contato(userName, nome));
    		contatos.put(userOnline, novos);
    	}
    }
	
	@Override
    public boolean removeContato(String userOnline, String userName){
		if(contatos.containsKey(userOnline)){
			ArrayList<Contato> aux = contatos.get(userOnline);
			for(int i=0; i<aux.size(); ++i){
				if(aux.equals(userName)){
					aux.remove(i);
					return true;
				}
			}
		}
		return false;
    }
	
	@Override
	public boolean contatoExiste(String userOnline, String userName){
		if(contatos.containsKey(userOnline)){
			ArrayList<Contato> aux = contatos.get(userOnline);
			for(Contato contato : aux){
				if(contato.getUserName().equals(userName)){
					return true;
				}
			}
		}
		return false;
	}
}
