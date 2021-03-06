// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import pt.isec.gps.grupo12.mensagens.Constantes;
import pt.isec.gps.grupo12.mensagens.MAdicionarContato;
import pt.isec.gps.grupo12.mensagens.MConfirmacaoRecepcao;
import pt.isec.gps.grupo12.mensagens.MEnviarCor;
import pt.isec.gps.grupo12.mensagens.MEstadoLogin;
import pt.isec.gps.grupo12.mensagens.MLogin;
import pt.isec.gps.grupo12.mensagens.MReencaminharCor;
import pt.isec.gps.grupo12.mensagens.MRelatorio;
import pt.isec.gps.grupo12.mensagens.MRespostaAdicaoContato;
import pt.isec.gps.grupo12.mensagens.Mensagem;


public class TrataMensagens extends Thread implements EnviarMensagem{
    private RecebeMensagem recebeMensagem;
    private UDPcliente socket;
    private String userLogged;
    private boolean isLogged;
    private boolean sair;
    private Temporizador temporizador;
    private FonteDadosCliente fonteDadosCliente;
    
    public TrataMensagens(RecebeMensagem recebeMensagem) throws SocketException, UnknownHostException{
        this.recebeMensagem = recebeMensagem;
        this.userLogged = null;
        this.isLogged = false;
        this.socket = new UDPcliente(new DatagramSocket(), InetAddress.getByName(Constantes.IP_SERVIDOR), Constantes.PORTO_SERVIDOR);
        this.fonteDadosCliente = new BaseDadosCliente();
        this.temporizador = new Temporizador();
        System.out.println("TrataMensagens criado com sucesso!");
        start();
        temporizador.start();
    }
    
    @Override
    public void run(){
    	sair = false;
    	try {
			socket.setSoTimeout(Constantes.CLIENTE_SOCKET_TIMEOUT);
		} catch (SocketException e1) {
			recebeMensagem.erroComunicacao();
			sair = true;
		}
    	System.out.println("Tratamento de mensagens iniciado!");
    	while(!sair){
    		try {
				DatagramPacket readPacket = socket.read();
				Object obj = UDPcliente.transformByteToObject(readPacket);
				if(obj instanceof Mensagem){
					temporizador.recebeu();
					reencaminharMensagem((Mensagem)obj);
				}
    		} catch (SocketTimeoutException ex){
    			continue;
			} catch (IOException e) {
				if(!sair){
					recebeMensagem.erroComunicacao();
					sair = true;
				}
			} catch (ClassNotFoundException e) {
				continue;
			}
    	}
    }
    
    public void reencaminharMensagem(Mensagem m){
    	
    	if(m instanceof MEstadoLogin)
    	{
    		int tipoRespostaLogin = ((MEstadoLogin)m).getTipoResposta();
    		isLogged = ((MEstadoLogin)m).getEstadoLogin();
    		if(tipoRespostaLogin == MEstadoLogin.RESPOSTA_LOGIN)
    		{
    			recebeMensagem.respostaDeLoginChegou();
    		}
    		else if(tipoRespostaLogin == MEstadoLogin.RESPOSTA_LOGOUT)
    		{
    			userLogged = null;
    			recebeMensagem.respostaDeLogoutChegou();
    		}
    		else  if(tipoRespostaLogin == MEstadoLogin.RESPOSTA_SERVIDOR_ENCERRAR)
    		{
    			recebeMensagem.servidorNecessitaDeEncerrar();
    		}
    	}
    	
    	else if(m instanceof MReencaminharCor)
    	{
    		String remetente = ((MReencaminharCor) m).getRemetente();
    		if(fonteDadosCliente.contatoExiste(userLogged, remetente)){
    			MConfirmacaoRecepcao confirmacaoRecepcao = new MConfirmacaoRecepcao(((MReencaminharCor) m).getCodPedido(), userLogged, true);
				try {
					byte[] enviar = UDPcliente.transformObjectToByte(confirmacaoRecepcao);
					socket.write(enviar);
					recebeMensagem.corRecebida(remetente, ((MReencaminharCor) m).getRgb());
				} catch (IOException e) {
					recebeMensagem.erroComunicacao();
					terminarServico();
				}
    		} else {
    			MConfirmacaoRecepcao confirmacaoRecepcao = new MConfirmacaoRecepcao(((MReencaminharCor) m).getCodPedido(), userLogged, false);
				try {
					byte[] enviar = UDPcliente.transformObjectToByte(confirmacaoRecepcao);
					socket.write(enviar);
				} catch (IOException e) {
					recebeMensagem.erroComunicacao();
					terminarServico();
				}
    		}
    	}
    	else if(m instanceof MRelatorio) {
    		recebeMensagem.relatorioRecebido(((MRelatorio) m).getReceberam(),
    				((MRelatorio) m).getNaoReceberam(),
    				((MRelatorio) m).getOffiline(), 
    				((MRelatorio) m).getIgnoraram());
    	}
    	
    	else if(m instanceof MRespostaAdicaoContato){
    		if(((MRespostaAdicaoContato)m).getResposta()){
    			fonteDadosCliente.adicionarContato(userLogged, ((MRespostaAdicaoContato)m).getNome(), ((MRespostaAdicaoContato)m).getUserName());
    			recebeMensagem.adicaoDeContato(true); 
    		}
    		else{
    			recebeMensagem.adicaoDeContato(false);
    		}
    	}
    }
    
    
    
    // M�todos para a parte gr�fica
    @Override
    public void terminarServico(){
    	this.sair = true;
    	this.socket.close();
    }
    @Override
    public void adicionarContato(String userName) throws IOException{
    	MAdicionarContato adicionarContato = new MAdicionarContato(userLogged, userName);
    	byte[] enviar = UDPcliente.transformObjectToByte(adicionarContato);
    	socket.write(enviar);
    	temporizador.enviou();
    }
    @Override
    public boolean removeContato(String userName){
    	return fonteDadosCliente.removeContato(userLogged, userName);
    }
    @Override
    public List<Contato> getContatos(){
    	return fonteDadosCliente.getContatos(userLogged);
    }
    @Override
    public boolean isLogged(){
    	return this.isLogged;
    }
    @Override
    public String getUserName(){
    	return this.userLogged;
    }
    @Override
    public void efectuarLogin(String userName, String password) throws IOException{
    	this.userLogged = userName;
    	MLogin mensagem = new MLogin(userName, password, true);
    	byte[] enviar = UDPcliente.transformObjectToByte(mensagem);
    	socket.write(enviar);
    	temporizador.enviou();
    }
    
    @Override
    public void efectuarLogout() throws IOException{
    	MLogin mensagem = new MLogin(userLogged, null, false);
    	byte[] enviar = UDPcliente.transformObjectToByte(mensagem);
    	socket.write(enviar);
    	temporizador.enviou();
    }
    
    @Override
    public void enviarCor(String rgb, List<String> destinatarios) throws IOException{
    	MEnviarCor mensagem = new MEnviarCor(rgb, userLogged, destinatarios);
    	byte[] enviar = UDPcliente.transformObjectToByte(mensagem);
    	socket.write(enviar);
    	temporizador.enviou();
    }


    
    class Temporizador extends Thread{
    	private Integer contaMensagens;
    	private Integer tempo;
    	
    	Temporizador(){
    		contaMensagens = 0;
    		tempo = Constantes.MAXIMO_SINALIZACOES_ESPERA_CLIENTE;
    		setDaemon(true);
    	}
    	
    	void recebeu(){
    		synchronized (contaMensagens) {
				--contaMensagens;
				synchronized (tempo) {
					tempo = Constantes.MAXIMO_SINALIZACOES_ESPERA_CLIENTE;
				}
			}
    	}
    	
    	void enviou(){
    		synchronized (contaMensagens) {
				++contaMensagens;
				synchronized (tempo) {
					tempo = Constantes.MAXIMO_SINALIZACOES_ESPERA_CLIENTE;
				}
			}
    	}
    	
    	@Override
    	public void run() {
    		while(true){
    			try {
					Thread.sleep(Constantes.THREAD_SLEEP);
				} catch (InterruptedException e) {}
    			if(tempo > 0){
    				--tempo;
    				continue;
    			}
    			synchronized (contaMensagens) {
    				if(contaMensagens != 0){
    					terminarServico();
    					recebeMensagem.servidorEstaADemorarMuitoTempoAResponder();
    					break;
    				}
    				tempo = Constantes.MAXIMO_SINALIZACOES_ESPERA_CLIENTE;
    			}
    		}
    	}
    }

}
