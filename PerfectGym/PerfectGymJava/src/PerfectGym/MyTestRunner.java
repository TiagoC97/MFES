package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MyTestRunner {
  public static void main() {

    IO.println("Initializing Test Runner");
    new PerfectGymTest().Run();
    new ClubTest().Run();
    new ProductTest().Run();
    new TaskTest().Run();
    new GroupTest().Run();
    IO.println("\nFinalizing Test Runner");
  }

  public MyTestRunner() {}

  public String toString() {

    return "MyTestRunner{}";
  }
}
