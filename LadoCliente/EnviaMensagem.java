package LadoCliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EnviaMensagem implements Runnable {
  @Override
  public void run() {
    try {

      Scanner leitor = new Scanner(System.in);

      // Usuario digita seu nome
      System.out.println("Informe qual o seu nome: ");
      String nomeRemetente = leitor.next();

      // Conectar com o servidor
      Socket conectaServidor = new Socket("127.0.0.1", 11111);
      new Thread(new RecebeMensagem(conectaServidor)).start();

      // Enviar nome do remetente ao servidor
      PrintStream enviaMsgServidor = new PrintStream(conectaServidor.getOutputStream());
      enviaMsgServidor.println(nomeRemetente);

      String nomeMsgDestinatario = "";
      while (nomeMsgDestinatario != "-1") {
        // Enviar nome e mensagem do destinatario
        System.out.println("Informe qual o nome do destinatario e a mensagem desejada:");
        nomeMsgDestinatario = leitor.next();
        PrintStream enviaNomeMsgDestinatario = new PrintStream(conectaServidor.getOutputStream());
        enviaNomeMsgDestinatario.println(nomeMsgDestinatario);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
