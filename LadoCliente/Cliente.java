package LadoCliente;

import java.io.IOException;

public class Cliente {

  public static void main(String[] args) throws IOException {

    new Thread(new EnviaMensagem()).start();

  }
}
