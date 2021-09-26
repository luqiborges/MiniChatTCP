package LadoCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

  public static void main(String[] args) throws IOException {

    Scanner leitor = new Scanner(System.in);

    // Usuario digita seu nome
    System.out.println("Informe qual o seu nome: ");
    String nomeRemetente = leitor.next();

    // Conectar com o servidor
    Socket conectaServidor = new Socket("127.0.0.1", 11111);

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

      // Receber mensagens
      Scanner ouvirServidor = new Scanner(conectaServidor.getInputStream());
      String respostaRecebida = ouvirServidor.nextLine();

      // Imprimir a resposta do servidor na tela do user
      System.out.println("Mensagem recebida: " + respostaRecebida);
    }

  }
}
