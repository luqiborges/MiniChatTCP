package LadoCliente;

import java.net.Socket;
import java.util.Scanner;

public class RecebeMensagem implements Runnable {

  Socket conexaoServidor;

  public RecebeMensagem(Socket conexaoServidor) {
    this.conexaoServidor = conexaoServidor;
  }

  @Override
  public void run() {

    try {
      System.out.println("To recebendo mensagens!");

      while (true) {
        // Receber mensagens
        Scanner ouvirServidor = new Scanner(conexaoServidor.getInputStream());
        String respostaRecebida = ouvirServidor.nextLine();

        // Imprimir a resposta do servidor na tela do user
        System.out.println("Mensagem recebida: " + respostaRecebida);

      }

    } catch (Exception e) {
      e.printStackTrace();

    }
  }

}
