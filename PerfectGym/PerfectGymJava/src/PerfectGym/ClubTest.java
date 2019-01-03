package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;
import PerfectGym.quotes.*;

@SuppressWarnings("all")
public class ClubTest extends MyTestCase {
  private Owner owner1 =
      new Owner("Rui", 21L, MaleQuote.getInstance(), "portuguese");
  private Club club1 = new Club("Bombados", owner1, 17L);
  private Client client1 =
      new Client("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private Client client2 =
      new Client("Jorge", 25L, MaleQuote.getInstance(), "spanish");
  private Trainer trainer1 =
      new Trainer("Vasco", 25L, MaleQuote.getInstance(), "brazilian");
  private Trainer trainer2 =
      new Trainer("Alex", 33L, MaleQuote.getInstance(), "english");
  private SalesRepresentative salesRepresentative1 =
      new SalesRepresentative(
          "Joana", 21L, FemaleQuote.getInstance(), "portuguese");
  private SalesRepresentative salesRepresentative2 =
      new SalesRepresentative("Manuel", 33L, MaleQuote.getInstance(), "french");

  public void Run() {

    IO.println("\nClub Tests");
    club1.addClient(client1, owner1);
    club1.addClient(client2, owner1);
    club1.addTrainer(trainer1, owner1);
    club1.addTrainer(trainer2, owner1);
    club1.addSalesRepresentative(salesRepresentative1, owner1);
    club1.addSalesRepresentative(salesRepresentative2, owner1);
    club1.addGroup("PuxadoresDeFerro", SetUtil.set(client1), owner1);
    club1.addPersonalTraining(trainer1, client1, 40L, owner1);
    club1.addNewsletter("Sales", owner1);
    club1.sendMessageClient("Bom Ano", client1, owner1);
    club1.sendMessageEmployee("Tas despedido bro!", trainer1, owner1);
    club1.sendMessageAllClients("Feliz Natal", owner1);
    club1.sendMessageAllTrainers("Feliz Natal", owner1);
    club1.sendMessageAllSalesRepresentatives("Feliz Natal", owner1);
    club1.sendMessageToGroup("Feliz Natal", "PuxadoresDeFerro", owner1);
    club1.sendOfferToGroup(
        "Prota com 10% de desconto esta semana!! :O", "PuxadoresDeFerro", owner1);
    club1.addGroupClient("PuxadoresDeFerro", client2, owner1);
    club1.removeGroupClient("PuxadoresDeFerro", client2, owner1);
    assertEqual(SetUtil.set(client1, client2), club1.getClients());
    assertEqual(SetUtil.set(trainer1, trainer2), club1.getTrainers());
    assertEqual(
        SetUtil.set(salesRepresentative1, salesRepresentative2), club1.getSalesRepresentatives());
    assertEqual(owner1, club1.getOwner());
    assertEqual("Bombados", club1.getName());
    assertEqual(club1, owner1.getClub());
    IO.println("Finalizing Club Tests");
  }

  public ClubTest() {}

  public String toString() {

    return "ClubTest{"
        + "owner1 := "
        + Utils.toString(owner1)
        + ", club1 := "
        + Utils.toString(club1)
        + ", client1 := "
        + Utils.toString(client1)
        + ", client2 := "
        + Utils.toString(client2)
        + ", trainer1 := "
        + Utils.toString(trainer1)
        + ", trainer2 := "
        + Utils.toString(trainer2)
        + ", salesRepresentative1 := "
        + Utils.toString(salesRepresentative1)
        + ", salesRepresentative2 := "
        + Utils.toString(salesRepresentative2)
        + "}";
  }
}
