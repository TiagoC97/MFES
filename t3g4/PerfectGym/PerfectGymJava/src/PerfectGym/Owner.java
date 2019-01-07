package PerfectGym;

import java.util.*;

import PerfectGym.quotes.OwnerQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Owner extends User {
  public void cg_init_Owner_1(
          final String newName,
          final Number newAge,
          final Object newGender,
          final String newNationality) {

    cg_init_User_1(
            newName,
            OwnerQuote.getInstance(),
            newAge,
            ((Object) newGender),
            newNationality);
  }

  public Owner(
          final String newName,
          final Number newAge,
          final Object newGender,
          final String newNationality) {

    cg_init_Owner_1(newName, newAge, newGender, newNationality);
  }

  public Owner() {}

  public String toString() {

    return "Owner{"
            + "userID := "
            + Utils.toString(id)
            + ", name := "
            + Utils.toString(name)
            + ", age := "
            + Utils.toString(age)
            + ", gender := "
            + Utils.toString(gender)
            + ", nationality := "
            + Utils.toString(nationality)
            + "}";
  }
}
