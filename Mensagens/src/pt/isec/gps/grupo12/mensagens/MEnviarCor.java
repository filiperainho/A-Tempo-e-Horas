// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 



package pt.isec.gps.grupo12.mensagens;

import java.util.List;


public class MEnviarCor extends Mensagem {
    private String rgb;
    private String remetente;
    private List<String> destinatarios;
    public MEnviarCor(String rgb, String remetente, List<String> destinatarios) {
        this.remetente = remetente;
        this.rgb = rgb;
        this.destinatarios = destinatarios;
    }

    public String getRgb() {
        return rgb;
    }

    public String getRemetente() {
        return remetente;
    }

    public List<String> getDestinatarios() {
        return destinatarios;
    }
}
