package PerfectGym;

import java.util.*;

import PerfectGym.quotes.FemaleQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PaymentTest extends MyTestCase {
  private Client client1 =
      new Client("Maria", 23L, FemaleQuote.getInstance(), "portuguese");
  private Product product1 = new Product("Prota", 29.99, 40L);
  private Product product2 = new Product("Shaker", 3.99, 60L);
  private GymFeePayment gymFeePayment1 =
      new GymFeePayment(client1, 80L, cg_Utils.CreateDate(2019L, 1L, 22L), cg_Utils.CreateHour(8L, 0L));
  private PersonalTrainingPayment personalTrainingPayment1 =
      new PersonalTrainingPayment(
          client1, 50L, cg_Utils.CreateDate(2019L, 1L, 23L), cg_Utils.CreateHour(8L, 0L));
  private ProductPayment productPayment1 =
      new ProductPayment(
          client1, product1, 2L, cg_Utils.CreateDate(2019L, 1L, 30L), cg_Utils.CreateHour(18L, 29L));

  public void Run() {

    IO.println("\nPayment Tests");
    assertEqual(80L, gymFeePayment1.getFee());
    assertEqual(50L, personalTrainingPayment1.getFee());
    assertEqual(SeqUtil.seq(product1), productPayment1.getProducts());
    productPayment1.addProduct(product2, 1L);
    assertEqual(SeqUtil.seq(product1, product2), productPayment1.getProducts());
    assertEqual(20190122L, gymFeePayment1.getDate());
    assertEqual(800L, gymFeePayment1.getHour());
    assertEqual(80L, gymFeePayment1.getAmount());
    assertEqual(client1, gymFeePayment1.getClient());
    assertEqual(0L, gymFeePayment1.getID());
    IO.println("Finalizing Payment Tests");
  }

  public PaymentTest() {}

  public String toString() {

    return "PaymentTest{"
        + "client1 := "
        + Utils.toString(client1)
        + ", product1 := "
        + Utils.toString(product1)
        + ", product2 := "
        + Utils.toString(product2)
        + ", gymFeePayment1 := "
        + Utils.toString(gymFeePayment1)
        + ", personalTrainingPayment1 := "
        + Utils.toString(personalTrainingPayment1)
        + ", productPayment1 := "
        + Utils.toString(productPayment1)
        + "}";
  }
}
