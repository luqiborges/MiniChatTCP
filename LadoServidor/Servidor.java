package LadoServidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {

  static ArrayList<String> listaNomesConectados = new ArrayList<String>();
  static ArrayList<Socket> clientesConectados = new ArrayList<Socket>();

  public static void gerenciarUsuarios(String nome, Socket novoCliente) {
    boolean jaConectou = false;

    for (int i = 0; i < listaNomesConectados.size(); i++) {
      if (listaNomesConectados.get(i) == nome) {
        jaConectou = true;
      }
    }

    if (!jaConectou) {
      listaNomesConectados.add(nome);
      clientesConectados.add(novoCliente);
    }
  }

  public static int retornaDestinatario(String nome) {
    int indexDestinatario = -1;

    System.out.println("Nome: " + nome);
    for (int i = 0; i < listaNomesConectados.size(); i++) {
      if (listaNomesConectados.get(i).equals(nome)) {
        indexDestinatario = i;
      }
    }

    return indexDestinatario;
  }

  public static void main(String[] args) throws IOException {

    // Criar Serviço-servidor
    ServerSocket servidor = new ServerSocket(11111);
    System.out.println("Servidor On!");

    while (true) {

      // Aceitar Conexão com o usuário-cliente
      Socket cliente = servidor.accept();

      new Thread(new AtendeCliente(cliente)).start();

    }

  }
}