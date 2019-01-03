package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Lead {
  private String name;
  private Number age;
  private Object gender;
  private String nationality;
  public static Number curLeadID = 0L;
  public Number id = Lead.curLeadID;

  public void cg_init_Lead_1(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    name = newName;
    age = newAge;
    gender = newGender;
    nationality = newNationality;
    curLeadID = Lead.curLeadID.longValue() + 1L;
    return;
  }

  public Lead(
      final String newName,
      final Number newAge,
      final Object newGender,
      final String newNationality) {

    cg_init_Lead_1(newName, newAge, newGender, newNationality);
  }

  public String getName() {

    return name;
  }

  public Number getAge() {

    return age;
  }

  public Object getGender() {

    return gender;
  }

  public String getNationality() {

    return nationality;
  }

  public Number getID() {

    return id;
  }

  public Lead() {}

  public String toString() {

    return "Lead{"
        + "name := "
        + Utils.toString(name)
        + ", age := "
        + Utils.toString(age)
        + ", gender := "
        + Utils.toString(gender)
        + ", nationality := "
        + Utils.toString(nationality)
        + ", curLeadID := "
        + Utils.toString(curLeadID)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
