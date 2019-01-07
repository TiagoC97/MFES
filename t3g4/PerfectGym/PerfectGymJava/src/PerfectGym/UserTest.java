package PerfectGym;

import java.util.*;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserTest extends MyTestCase {
  private Client user1 =
      new Client("Rui", 21L, MaleQuote.getInstance(), "portuguese");
  private Client client1 =
      new Client("Rui", 21L, MaleQuote.getInstance(), "portuguese");
  private Client client2 =
      new Client("Tiago", 22L, MaleQuote.getInstance(), "portuguese");
  private Owner owner1 =
      new Owner("Maria", 21L, FemaleQuote.getInstance(), "portuguese");
  private Club club1 = new Club("Bombados", owner1, 17L);

  public void Run() {

    IO.println("\nUser Tests");
    club1.addClient(client1, owner1);
    club1.addClient(client2, owner1);
    assertEqual(21L, client1.getAge());
    assertEqual(MaleQuote.getInstance(), ((Object) client1.getGender()));
    assertEqual("portuguese", client1.getNationality());
    client2.sendMessage(client1, "ola");
    assertEqual(MapUtil.map(new Maplet("Tiago", SeqUtil.seq("ola"))), client1.checkInbox());
    assertEqual(SeqUtil.seq("ola"), client1.readMessagesFromUser(client2));
    assertEqual("ola", client1.readLastMessageFromUser(client2));
    assertEqual("ola", client1.readMessageNFromUser(1L, client2));
    client1.deleteLastMessageFromUser("Tiago");
    client2.sendMessage(client1, "ola");
    client1.deleteMessageNFromUser(1L, "Tiago");
    user1.getActivity();
    IO.println("Finalizing User Tests");
  }

  public UserTest() {}

  public String toString() {

    return "UserTest{"
        + "user1 := "
        + Utils.toString(user1)
        + ", client1 := "
        + Utils.toString(client1)
        + ", client2 := "
        + Utils.toString(client2)
        + ", owner1 := "
        + Utils.toString(owner1)
        + ", club1 := "
        + Utils.toString(club1)
        + "}";
  }
}
