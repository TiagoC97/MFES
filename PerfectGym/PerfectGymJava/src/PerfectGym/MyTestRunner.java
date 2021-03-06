package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MyTestRunner {
  public static void main() {

    IO.println("Initializing Test Runner");
    new PerfectGymTest().Run();
    new UserTest().Run();
    new ProductTest().Run();
    new TaskTest().Run();
    new SessionTest().Run();
    new GymClassTest().Run();
    new GroupTest().Run();
    new LeadTest().Run();
    new SalesRepresentativeTest().Run();
    new PaymentTest().Run();
    new InvoiceTest().Run();
    new EmployeeTest().Run();
    new ClientTest().Run();
    new ClubTest().Run();
    IO.println("\nFinalizing Test Runner");
  }

  public MyTestRunner() {
  }

  public String toString() {

    return "MyTestRunner{}";
  }
}
