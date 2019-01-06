package PerfectGym;

import java.util.*;

import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ClientTest extends MyTestCase {
  private Client client1 =
          new Client("Vasco", 25L, MaleQuote.getInstance(), "brazilian");

  public void Run() {

    IO.println("\nClient Tests");
    IO.println("Finalizing Client Tests");
  }

  public ClientTest() {}

  public String toString() {

    return "ClientTest{" + "client1 := " + Utils.toString(client1) + "}";
  }
}
