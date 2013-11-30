// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 



package pt.isec.gps.grupo12.mensagens;


public class MReencaminharCor extends Mensagem {
    private long codPedido;
    private String remetente;
    private String rgb;
    
    public MReencaminharCor(long CodPedido, String remetente, String rgb) {
        this.codPedido = CodPedido;
        this.remetente = remetente;
        this.rgb = rgb;
    }

    public long getCodPedido() {
        return codPedido;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getRgb() {
        return rgb;
    }
}
