package pt.isec.gps.grupo12.cliente;

import java.util.List;

public interface FonteDadosCliente {
	public abstract List<Contato> getContatos(String userOnline);
    public abstract void adicionarContato(String userOnline, String userName, String nome);
    public abstract boolean removeContato(String userOnline, String userName);
}
