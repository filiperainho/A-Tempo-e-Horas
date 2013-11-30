// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.mensagens;


public class MConfirmacaoRecepcao extends Mensagem {
    private long codigoPedido;
    private String destinatario;
    private boolean recebida;
    public MConfirmacaoRecepcao(long codPedido, String destinatario, boolean recebida) {
        this.codigoPedido = codPedido;
        this.destinatario = destinatario;
        this.recebida = recebida;
    }

    public long getCodigoPedido() {
        return codigoPedido;
    }

    public boolean getRecebida() {
        return recebida;
    }
    
    public String getDestinatario() {
        return destinatario;
    }
}
