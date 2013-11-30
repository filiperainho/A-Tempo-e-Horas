// "A tempo e Horas"
// Grupo 12 - GPS 2013
//
// https://sites.google.com/site/gpstp1314/
// 


package pt.isec.gps.grupo12.cliente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import pt.isec.gps.grupo12.mensagens.Constantes;


public class UDPcliente {
    private DatagramSocket socket;
    private InetAddress ip;
    private int porto;
    
    public UDPcliente(DatagramSocket socket, InetAddress ip, int porto) {
        this.socket = socket;
        this.ip = ip;
        this.porto = porto;
    }

    public DatagramPacket read() throws IOException{
        byte[] recbuf = new byte[Constantes.BUFFER_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(recbuf, recbuf.length);
        socket.receive(receivePacket);
        return receivePacket;
    }

    public void write(byte[] mensagemBytes) throws IOException{
        DatagramPacket packet = new DatagramPacket(mensagemBytes, mensagemBytes.length, ip, porto);
        socket.send(packet);
    }
    
    

    public static byte[] transformObjectToByte(Serializable obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray();
    }

    public static Object transformByteToObject(DatagramPacket packet) throws IOException, ClassNotFoundException {
        byte[] read = packet.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(read);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
}