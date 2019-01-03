package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ProductTest extends MyTestCase {
  private Product product1 = new Product("Prota", 29.99, 40L);

  public void Run() {

    IO.println("\nProduct Tests");
    assertEqual("Prota", product1.getName());
    assertEqual(29.99, product1.getValue());
    assertEqual(40L, product1.getQuantity());
    product1.addQuantity(10L);
    assertEqual(50L, product1.getQuantity());
    product1.removeQuantity(30L);
    assertEqual(20L, product1.getQuantity());
    IO.println("Finalizing Product Tests");
  }

  public ProductTest() {}

  public String toString() {

    return "ProductTest{" + "product1 := " + Utils.toString(product1) + "}";
  }
}
