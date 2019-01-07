package PerfectGym;

import java.util.*;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class GroupTest extends MyTestCase {
  private Client client1 =
          new Client("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private Client client2 =
          new Client("Jorge", 25L, MaleQuote.getInstance(), "spanish");
  private Group group1 = new Group(SetUtil.set(client1));

  public void Run() {

    IO.println("\nGroup Tests");
    group1.addClient(client2);
    assertEqual(SetUtil.set(client1, client2), group1.getClients());
    group1.removeClient(client2);
    assertEqual(SetUtil.set(client1), group1.getClients());
    group1.sendOffer("Descontos muito bonitos");
    group1.sendMessage(client1, "Feliz Natal Amigos");
    assertEqual(
            MapUtil.map(new Maplet("Maria", SeqUtil.seq("Feliz Natal Amigos"))),
            group1.checkInbox(client1));
    assertEqual("Feliz Natal Amigos", group1.getLastMessageFromUser("Maria", client1));
    assertEqual(SeqUtil.seq("Feliz Natal Amigos"), group1.getMessagesFromUser("Maria", client1));
    assertEqual(SeqUtil.seq("Descontos muito bonitos"), group1.checkOffers(client1));
    IO.println("Finalizing Group Tests");
  }

  public GroupTest() {}

  public String toString() {

    return "GroupTest{"
            + "client1 := "
            + Utils.toString(client1)
            + ", client2 := "
            + Utils.toString(client2)
            + ", group1 := "
            + Utils.toString(group1)
            + "}";
  }
}
