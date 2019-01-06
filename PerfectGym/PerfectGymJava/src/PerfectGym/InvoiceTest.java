package PerfectGym;

import java.util.*;

import PerfectGym.quotes.FemaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class InvoiceTest extends MyTestCase {
  private Client client1 =
      new Client("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private Product product1 = new Product("Prota", 29.99, 40L);
  private Product product2 = new Product("Shaker", 3.99, 60L);
  private GymFeePayment gymFeePayment1 =
      new GymFeePayment(client1, 80L, cg_Utils.CreateDate(2019L, 1L, 22L), cg_Utils.CreateHour(8L, 0L));
  private GymFeePayment gymFeePayment2 =
      new GymFeePayment(client1, 80L, cg_Utils.CreateDate(2019L, 2L, 22L), cg_Utils.CreateHour(8L, 0L));
  private Invoice invoice1 =
      new Invoice(
          SetUtil.set(gymFeePayment1),
          cg_Utils.CreateDate(2019L, 4L, 23L),
          cg_Utils.CreateHour(8L, 0L),
          "gymFee",
          false,
          client1);
  private PersonalTrainingPayment personalTrainingPayment1 =
      new PersonalTrainingPayment(
          client1, 50L, cg_Utils.CreateDate(2019L, 3L, 13L), cg_Utils.CreateHour(8L, 0L));
  private PersonalTrainingPayment personalTrainingPayment2 =
      new PersonalTrainingPayment(
          client1, 50L, cg_Utils.CreateDate(2019L, 4L, 13L), cg_Utils.CreateHour(8L, 0L));
  private Invoice invoice2 =
      new Invoice(
          SetUtil.set(personalTrainingPayment1),
          cg_Utils.CreateDate(2019L, 4L, 23L),
          cg_Utils.CreateHour(8L, 0L),
          "personalTraining",
          false,
          client1);

  public void Run() {

    IO.println("\nInvoice Tests");
    invoice2.addPayment(SetUtil.set(personalTrainingPayment2));
    assertEqual(
        SetUtil.set(personalTrainingPayment1, personalTrainingPayment2), invoice2.getPayments());
    assertEqual(100L, invoice2.getTotalAmount());
    invoice2.removePayment(SetUtil.set(personalTrainingPayment1));
    assertEqual(SetUtil.set(personalTrainingPayment2), invoice2.getPayments());
    assertEqual(50L, invoice2.getTotalAmount());
    invoice1.addPayment(SetUtil.set(gymFeePayment2));
    assertEqual(SetUtil.set(gymFeePayment1, gymFeePayment2), invoice1.getPayments());
    assertEqual(160L, invoice1.getTotalAmount());
    invoice1.removePayment(SetUtil.set(gymFeePayment1));
    assertEqual(SetUtil.set(gymFeePayment2), invoice1.getPayments());
    assertEqual(80L, invoice1.getTotalAmount());
    assertEqual(20190423L, invoice1.getDate());
    assertEqual(800L, invoice1.getHour());
    assertEqual("gymFee", invoice1.getType());
    assertEqual(client1, invoice1.getClient());
    assertEqual(20190423L, invoice2.getDate());
    assertEqual(800L, invoice2.getHour());
    assertEqual("personalTraining", invoice2.getType());
    assertEqual(client1, invoice2.getClient());
    IO.println("Finalizing Invoice Tests");
  }

  public InvoiceTest() {}

  public String toString() {

    return "InvoiceTest{"
        + "client1 := "
        + Utils.toString(client1)
        + ", product1 := "
        + Utils.toString(product1)
        + ", product2 := "
        + Utils.toString(product2)
        + ", gymFeePayment1 := "
        + Utils.toString(gymFeePayment1)
        + ", gymFeePayment2 := "
        + Utils.toString(gymFeePayment2)
        + ", invoice1 := "
        + Utils.toString(invoice1)
        + ", personalTrainingPayment1 := "
        + Utils.toString(personalTrainingPayment1)
        + ", personalTrainingPayment2 := "
        + Utils.toString(personalTrainingPayment2)
        + ", invoice2 := "
        + Utils.toString(invoice2)
        + "}";
  }
}
