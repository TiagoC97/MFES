package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Payment {
  protected Number date;
  protected Number hour;
  protected Number amount = 0L;
  public static Number curPaymentID = 0L;
  protected Number id = Payment.curPaymentID;
  protected Client client;

  public void cg_init_Payment_1(
      final Client newClient, final Number newDate, final Number newHour, final Number newAmount) {

    client = newClient;
    date = newDate;
    hour = newHour;
    amount = newAmount;
    curPaymentID = Payment.curPaymentID.longValue() + 1L;
    return;
  }

  public Payment(
      final Client newClient, final Number newDate, final Number newHour, final Number newAmount) {

    cg_init_Payment_1(newClient, newDate, newHour, newAmount);
  }

  public Number getDate() {

    return date;
  }

  public Number getHour() {

    return hour;
  }

  public Number getAmount() {

    return amount;
  }

  public Client getClient() {

    return client;
  }

  public Number getID() {

    return id;
  }

  public Payment() {}

  public String toString() {

    return "Payment{"
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
        + "}";
  }
}
