// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.mensagens;


public class MConfirmacaoRecepcao extends Mensagem {
    private long codigoPedido;
    private boolean recebida;
    public MConfirmacaoRecepcao(long codPedido, boolean recebida) {
        this.codigoPedido = codPedido;
        this.recebida = recebida;
    }

    public long getCodigoPedido() {
        return codigoPedido;
    }

    public boolean getRecebida() {
        return recebida;
    }
}
