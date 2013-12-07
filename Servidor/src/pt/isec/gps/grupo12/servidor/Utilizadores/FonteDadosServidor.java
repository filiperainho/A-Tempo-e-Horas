// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.servidor.Utilizadores;


public interface FonteDadosServidor {
    public abstract String getPassword(String username);
    public abstract boolean userExiste(String username);
    public abstract String getNome(String username);
}
