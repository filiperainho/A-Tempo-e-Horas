// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.mensagens;

import java.util.List;


public class MRelatorio extends Mensagem {
    private List<String> receberam;
    private List<String> naoReceberam;
    private List<String> offline;
    private List<String> ignoraram;
    public void MRelatorio(List<String> receberam, List<String> naoReceberam, List<String> offline, List<String> ignoraram) {
        this.receberam = receberam;
        this.naoReceberam = naoReceberam;
        this.offline = offline;
        this.ignoraram = ignoraram;
    }

    public List<String> getReceberam() {
        return receberam;
    }

    public List<String> getNaoReceberam() {
        return naoReceberam;
    }

    public List<String> getOffiline() {
        return offline;
    }

    public List<String> getIgnoraram() {
        return ignoraram;
    }
}
