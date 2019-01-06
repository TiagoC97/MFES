package PerfectGym;

import java.util.*;

import PerfectGym.quotes.*;
import org.overture.codegen.runtime.*;

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
  private GymClass gymClass1 =
          new GymClass(
                  "Aula de baixa intensidade",
                  "Pilates",
                  trainer1,
                  TuesdayQuote.getInstance(),
                  cg_Utils.CreateHour(9L, 0L),
                  cg_Utils.CreateHour(10L, 0L),
                  cg_Utils.CreateDate(2019L, 1L, 11L));
  private GymFeePayment gymFeePayment1 =
          new GymFeePayment(client1, 80L, cg_Utils.CreateDate(2019L, 1L, 22L), cg_Utils.CreateHour(8L, 0L));
  private GymFeePayment gymFeePayment2 =
          new GymFeePayment(client1, 80L, cg_Utils.CreateDate(2019L, 4L, 22L), cg_Utils.CreateHour(8L, 0L));
  private GymFeePayment gymFeePayment3 =
          new GymFeePayment(client1, 80L, cg_Utils.CreateDate(2019L, 3L, 22L), cg_Utils.CreateHour(18L, 0L));
  private Invoice invoice1 =
          new Invoice(
                  SetUtil.set(gymFeePayment3),
                  cg_Utils.CreateDate(2019L, 4L, 23L),
                  cg_Utils.CreateHour(8L, 0L),
                  "gymFee",
                  false,
                  client1);
  private PersonalTrainingPayment personalTrainingPayment1 =
          new PersonalTrainingPayment(
                  client1, 50L, cg_Utils.CreateDate(2019L, 3L, 13L), cg_Utils.CreateHour(8L, 0L));
  private Lead lead1 =
          new Lead("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private Lead lead2 = new Lead("Jorge", 25L, MaleQuote.getInstance(), "spanish");
  private Task task1 =
          new Task(
                  "ReuniÃ£o com o patrÃ£o",
                  cg_Utils.CreateHour(9L, 0L),
                  cg_Utils.CreateHour(10L, 0L),
                  cg_Utils.CreateDate(2019L, 1L, 12L));
  private Product product1 = new Product("Prota", 29.99, 40L);

  public void Run() {

    IO.println("\nClub Tests");
    IO.println("Initiate addClient");
    club1.addClient(client1, owner1);
    club1.addClient(client2, owner1);
    IO.println("Initiate addTrainer");
    club1.addTrainer(trainer1, owner1);
    club1.addTrainer(trainer2, owner1);
    IO.println("Initiate addSalesRepresentative");
    club1.addSalesRepresentative(salesRepresentative1, owner1);
    club1.addSalesRepresentative(salesRepresentative2, owner1);
    trainer1.addGymClass(gymClass1);
    trainer1.addTask(gymClass1);
    assertEqual(
            MapUtil.map(new Maplet(20190111L, SeqUtil.seq(gymClass1))),
            club1.getEmployeeCalendar(trainer1));
    assertEqual(SeqUtil.seq(gymClass1), club1.getEmployeeTasksForGivenDate(trainer1, 20190111L));
    IO.println("Initiate addGroup");
    club1.addGroup("PuxadoresDeFerro", SetUtil.set(client1), owner1);
    IO.println("Initiate addPersonalTraining");
    club1.addPersonalTraining(trainer1, client1, 40L, owner1);
    IO.println("Initiate addGymClass");
    club1.addGymClass(gymClass1, owner1);
    assertEqual(SetUtil.set(gymClass1), club1.getGymClasses());
    IO.println("Initiate GymClass");
    club1.addGymClass(
            "Aula de alta intensidade",
            "Zumba",
            trainer1,
            MondayQuote.getInstance(),
            cg_Utils.CreateHour(16L, 0L),
            cg_Utils.CreateHour(17L, 0L),
            cg_Utils.CreateDate(2019L, 1L, 14L),
            owner1);
    IO.println("Finalizing GymClass1");
    club1.addGymClass(
            "Aula de baixa intensidade",
            "Pilates",
            trainer2,
            TuesdayQuote.getInstance(),
            cg_Utils.CreateHour(9L, 0L),
            cg_Utils.CreateHour(10L, 0L),
            cg_Utils.CreateDate(2019L, 1L, 15L),
            owner1);
    IO.println("Finalizing GymClass2");
    club1.addAttendeeToGymClass(gymClass1, client1, owner1);
    club1.addAttendeeToGymClass(gymClass1, client2, owner1);
    club1.addTrainingSession(
            "Aula iniciante",
            client1,
            MondayQuote.getInstance(),
            cg_Utils.CreateHour(14L, 0L),
            cg_Utils.CreateHour(15L, 0L),
            cg_Utils.CreateDate(2019L, 1L, 14L),
            owner1);
    IO.println("Finalizing TrainingSession1");
    assertEqual(club1.getTrainingSessions(), trainer1.getTrainingSessions());
    club1.removeTraineeFromTrainer(trainer1, client1, owner1);
    club1.setUserAccess(owner1, trainer1, OwnerQuote.getInstance());
    IO.println("Initiate addNewsletter");
    club1.addNewsletter("Sales", owner1);
    IO.println("Initiate sendMessageClient");
    club1.sendMessageClient("Bom Ano", client1, owner1);
    IO.println("Initiate sendMessageEmployee");
    club1.sendMessageEmployee("Tas despedido bro!", trainer1, owner1);
    IO.println("Initiate sendMessageAllClients");
    club1.sendMessageAllClients("Feliz Natal", owner1);
    IO.println("Initiate sendMessageAllTrainers");
    club1.sendMessageAllTrainers("Feliz Natal", owner1);
    IO.println("Initiate sendMessageAllSalesRepresentatives");
    club1.sendMessageAllSalesRepresentatives("Feliz Natal", owner1);
    IO.println("Initiate sendMessageToGroup");
    club1.sendMessageToGroup("Feliz Natal", "PuxadoresDeFerro", owner1);
    club1.sendOfferToGroup(
            "Prota com 10% de desconto esta semana!! :O", "PuxadoresDeFerro", owner1);
    IO.println("Initiate AddGroupClient");
    club1.addGroupClient("PuxadoresDeFerro", client2, owner1);
    club1.removeGroupClient("PuxadoresDeFerro", client2, owner1);
    IO.println("Initiate Invoice");
    club1.addInvoice(
            client1,
            SetUtil.set(gymFeePayment1),
            cg_Utils.CreateDate(2019L, 4L, 23L),
            cg_Utils.CreateHour(8L, 0L),
            "gymFee",
            owner1);
    assertEqual(1L, club1.getInvoices().size());
    club1.addInvoiceWithAllActivePayments(
            client1, cg_Utils.CreateDate(2019L, 4L, 23L), cg_Utils.CreateHour(8L, 0L), "gymFee", owner1);
    club1.addPaymentToInvoice(
            invoice1,
            SetUtil.set(
                    new GymFeePayment(
                            client1, 80L, cg_Utils.CreateDate(2019L, 2L, 22L), cg_Utils.CreateHour(18L, 0L))),
            owner1);
    club1.removePaymentFromInvoice(invoice1, SetUtil.set(personalTrainingPayment1), owner1);
    IO.println("Initiate Clients");
    assertEqual(SetUtil.set(client1, client2), club1.getClients());
    assertEqual(
            SetUtil.set(
                    owner1,
                    client1,
                    client2,
                    trainer1,
                    trainer2,
                    salesRepresentative1,
                    salesRepresentative2),
            club1.getUsers());
    assertEqual(SetUtil.set(client1), club1.getClientByName("Maria"));
    IO.println("Initiate CRM");
    assertEqual(MapUtil.map(), club1.getCRM().getLeads());
    club1.addLeadToCRM(lead1, owner1);
    club1.setCRMLeadSR(lead1, salesRepresentative1, owner1);
    club1.addLeadSRToCRM(lead2, salesRepresentative2, owner1);
    assertEqual(SetUtil.set(lead2.getName()), salesRepresentative2.getLeads());
    assertEqual(salesRepresentative1, club1.getCRM().getLeadSR(lead1));
    IO.println("Initiate removeLeadSR");
    club1.removeLeadSR(lead1, owner1);
    IO.println("Initiate removeCRMLead");
    club1.removeCRMLead(lead1, owner1);
    club1.transformLeadIntoClient(lead2, owner1);
    IO.println("Initiate addTaskToEmployee");
    club1.addTaskToEmployee(trainer1, owner1, task1);
    IO.println("Initiate Products");
    club1.addProduct(product1, owner1);
    club1.addStockOfProduct(product1, 7L, owner1);
    assertEqual(SetUtil.set(product1), club1.getProducts());
    club1.removeProduct(product1, owner1);
    assertEqual(SetUtil.set(), club1.getProducts());
    club1.addProduct("Shaker", 3.99, 20L, owner1);
    IO.println("Initiate Getters");
    assertEqual(SetUtil.set(trainer1, trainer2), club1.getTrainers());
    assertEqual(
            SetUtil.set(salesRepresentative1, salesRepresentative2), club1.getSalesRepresentatives());
    assertEqual(
            SetUtil.set(trainer1, trainer2, salesRepresentative1, salesRepresentative2),
            club1.getEmployees());
    assertEqual(SetUtil.set(trainer1), club1.getEmployeeByName("Vasco"));
    assertEqual(SetUtil.set(trainer1), club1.getUserByName("Vasco"));
    assertEqual(owner1, club1.getOwner());
    assertEqual("Bombados", club1.getName());
    assertEqual("Sales", club1.getNewsletter());
    assertEqual(1L, MapUtil.dom(club1.getGroups()).size());
    assertEqual(17L, club1.getFee());
    assertEqual(club1, owner1.getClub());
    IO.println("Initiate Stats");
    club1.getReportOnClubStatistics(owner1);
    club1.getClientActivity(client1, owner1);
    club1.getEmployeeActivity(trainer1, true, owner1);
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
            + ", gymClass1 := "
            + Utils.toString(gymClass1)
            + ", gymFeePayment1 := "
            + Utils.toString(gymFeePayment1)
            + ", gymFeePayment2 := "
            + Utils.toString(gymFeePayment2)
            + ", gymFeePayment3 := "
            + Utils.toString(gymFeePayment3)
            + ", invoice1 := "
            + Utils.toString(invoice1)
            + ", personalTrainingPayment1 := "
            + Utils.toString(personalTrainingPayment1)
            + ", lead1 := "
            + Utils.toString(lead1)
            + ", lead2 := "
            + Utils.toString(lead2)
            + ", task1 := "
            + Utils.toString(task1)
            + ", product1 := "
            + Utils.toString(product1)
            + "}";
  }
}
