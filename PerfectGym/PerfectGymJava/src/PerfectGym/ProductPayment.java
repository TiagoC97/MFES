package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ProductPayment extends Payment {
  private VDMSeq products = SeqUtil.seq();

  public void cg_init_ProductPayment_1(
      final Client newClient,
      final Product newProduct,
      final Number qtt,
      final Number newDate,
      final Number newHour) {

    Number moneySpent = newProduct.getValue().doubleValue() * qtt.longValue();
    products = SeqUtil.seq(newProduct);
    newProduct.removeQuantity(qtt);
    newClient.addProductPayment(this);
    newClient.addProductBought(newProduct, qtt, moneySpent);
    cg_init_Payment_1(newClient, newDate, newHour, moneySpent);
  }

  public ProductPayment(
      final Client newClient,
      final Product newProduct,
      final Number qtt,
      final Number newDate,
      final Number newHour) {

    cg_init_ProductPayment_1(newClient, newProduct, qtt, newDate, newHour);
  }

  public void addProduct(final Product product, final Number qtt) {

    Number moneySpent = product.getValue().doubleValue() * qtt.longValue();
    products = SeqUtil.conc(Utils.copy(products), SeqUtil.seq(product));
    amount = amount.doubleValue() + moneySpent.doubleValue();
    product.removeQuantity(qtt);
    client.addProductBought(product, qtt, moneySpent);
  }

  public VDMSeq getProducts() {

    return Utils.copy(products);
  }

  public ProductPayment() {}

  public String toString() {

    return "ProductPayment{"
            + "date := "
            + Utils.toString(date)
            + ", hour := "
            + Utils.toString(hour)
            + ", amount := "
            + Utils.toString(amount)
            + ", curPaymentID := "
            + Utils.toString(curPaymentID)
            + ", id := "
            + Utils.toString(id)
            + ", products := " + Utils.toString(products) + "}";
  }
}
