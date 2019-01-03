package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Invoice {
  private VDMSet payments = SetUtil.set();
  private Number totalAmount = 0L;
  private Number date;
  private Number hour;
  private String type;
  private Client client;

  public void cg_init_Invoice_1(
      final VDMSet newPayments,
      final Number newDate,
      final Number newHour,
      final String newType,
      final Boolean allActivePayments,
      final Client newClient) {

    payments = Utils.copy(newPayments);
    date = newDate;
    hour = newHour;
    type = newType;
    client = newClient;
    if (Utils.equals(allActivePayments, true)) {
      String stringPattern_4 = type;
      Boolean success_2 = Utils.equals(stringPattern_4, "product");

      if (!(success_2)) {
        String stringPattern_5 = type;
        success_2 = Utils.equals(stringPattern_5, "gymFee");

        if (!(success_2)) {
          String stringPattern_6 = type;
          success_2 = Utils.equals(stringPattern_6, "personalTraining");

          if (success_2) {
            client.moveAllPersonalTrainingPaymentsToHistory();
          }

        } else {
          client.moveAllProductPaymentsToHistory();
        }

      } else {
        client.moveAllGymFeePaymentsToHistory();
      }

      for (Iterator iterator_27 = payments.iterator(); iterator_27.hasNext(); ) {
        Payment p = (Payment) iterator_27.next();
        totalAmount = totalAmount.doubleValue() + p.getAmount().doubleValue();
      }

    } else {
      for (Iterator iterator_28 = payments.iterator(); iterator_28.hasNext(); ) {
        Payment p = (Payment) iterator_28.next();
        totalAmount = totalAmount.doubleValue() + p.getAmount().doubleValue();
        String stringPattern_7 = type;
        Boolean success_3 = Utils.equals(stringPattern_7, "product");

        if (!(success_3)) {
          String stringPattern_8 = type;
          success_3 = Utils.equals(stringPattern_8, "gymFee");

          if (!(success_3)) {
            String stringPattern_9 = type;
            success_3 = Utils.equals(stringPattern_9, "personalTraining");

            if (success_3) {
              client.removePersonalTrainingPayment(p);
            }

          } else {
            client.removeGymFeePayment(p);
          }

        } else {
          client.removeProductPayment(p);
        }
      }
    }

    return;
  }

  public Invoice(
      final VDMSet newPayments,
      final Number newDate,
      final Number newHour,
      final String newType,
      final Boolean allActivePayments,
      final Client newClient) {

    cg_init_Invoice_1(
        Utils.copy(newPayments), newDate, newHour, newType, allActivePayments, newClient);
  }

  public void addPayment(final VDMSet newPayments) {

    payments = SetUtil.union(Utils.copy(payments), Utils.copy(newPayments));
    for (Iterator iterator_29 = newPayments.iterator(); iterator_29.hasNext(); ) {
      Payment p = (Payment) iterator_29.next();
      totalAmount = totalAmount.doubleValue() + p.getAmount().doubleValue();
    }
  }

  public void removePayment(final VDMSet newPayments) {

    payments = SetUtil.diff(Utils.copy(payments), Utils.copy(newPayments));
    for (Iterator iterator_30 = newPayments.iterator(); iterator_30.hasNext(); ) {
      Payment p = (Payment) iterator_30.next();
      totalAmount = totalAmount.doubleValue() - p.getAmount().doubleValue();
    }
  }

  public VDMSet getPayments() {

    return Utils.copy(payments);
  }

  public Number getTotalAmount() {

    return totalAmount;
  }

  public Number getDate() {

    return date;
  }

  public Number getHour() {

    return hour;
  }

  public String getType() {

    return type;
  }

  public Client getClient() {

    return client;
  }

  public Invoice() {}

  public String toString() {

    return "Invoice{"
        + "payments := "
        + Utils.toString(payments)
        + ", totalAmount := "
        + Utils.toString(totalAmount)
        + ", date := "
        + Utils.toString(date)
        + ", hour := "
        + Utils.toString(hour)
        + ", type := "
        + Utils.toString(type)
        + ", client := "
        + Utils.toString(client)
        + "}";
  }
}
