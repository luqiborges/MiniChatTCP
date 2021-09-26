package LadoServidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AtendeCliente implements Runnable {

  Socket cliente;

  public AtendeCliente(Socket cliente) {
    this.cliente = cliente;
  }

  @Override
  public void run() {
    try {
      // Estabelecer canal com o cliente e pegar seu nome
      Scanner ouvirCliente = new Scanner(cliente.getInputStream());
      String nomeRemetente = ouvirCliente.next();

      // Printar no servidor o nome do remetente recebido
      System.out.println("Remetente: " + nomeRemetente);
      Servidor.gerenciarUsuarios(nomeRemetente, cliente);

      System.out.println("Usuarios conectados: ");
      for (int i = 0; i < Servidor.listaNomesConectados.size(); i++) {
        System.out.println(Servidor.listaNomesConectados.get(i));
      }

      // Pegar nome e mensagem destinatario indefinidamente
      String nomeMensagemDestinatario = ouvirCliente.next();

      while (nomeMensagemDestinatario != "-1") {
        String nomeDestinatario = nomeMensagemDestinatario.split(":")[0];
        String mensagem = nomeMensagemDestinatario.split(":")[1];

        // Pegar retorno com destinatario e enviar mensagem
        int idDestinatario = Servidor.retornaDestinatario(nomeDestinatario);
        if (idDestinatario == -1) {
          PrintStream enviarMsgDestinatario = new PrintStream(cliente.getOutputStream());
          enviarMsgDestinatario.println("Destinatario nao conectado!");
          nomeMensagemDestinatario = ouvirCliente.next();

        } else {
          System.out.println("Opa");
          PrintStream enviarMsgDestinatario = new PrintStream(
              Servidor.clientesConectados.get(idDestinatario).getOutputStream());
          enviarMsgDestinatario.println(mensagem);
          nomeMensagemDestinatario = ouvirCliente.next();
        }

      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
