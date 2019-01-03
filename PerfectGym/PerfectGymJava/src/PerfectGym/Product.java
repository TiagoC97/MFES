package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Product {
  private String name;
  private Number value;
  private Number quantity;

  public void cg_init_Product_1(
      final String prod_name, final Number prod_value, final Number prod_quantity) {

    name = prod_name;
    value = prod_value;
    quantity = prod_quantity;
    return;
  }

  public Product(final String prod_name, final Number prod_value, final Number prod_quantity) {

    cg_init_Product_1(prod_name, prod_value, prod_quantity);
  }

  public void addQuantity(final Number qtt) {

    quantity = quantity.longValue() + qtt.longValue();
  }

  public void removeQuantity(final Number qtt) {

    quantity = quantity.longValue() - qtt.longValue();
  }

  public String getName() {

    return name;
  }

  public Number getValue() {

    return value;
  }

  public Number getQuantity() {

    return quantity;
  }

  public Product() {}

  public String toString() {

    return "Product{"
        + "name := "
        + Utils.toString(name)
        + ", value := "
        + Utils.toString(value)
        + ", quantity := "
        + Utils.toString(quantity)
        + "}";
  }
}
