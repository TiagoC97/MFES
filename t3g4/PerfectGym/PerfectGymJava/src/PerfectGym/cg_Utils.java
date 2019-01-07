package PerfectGym;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class cg_Utils {
  public cg_Utils() {}

  public static Boolean IsValidDate(final Number y, final Number m, final Number d) {

    Boolean andResult_177 = false;

    if (y.longValue() >= 1L) {
      Boolean andResult_178 = false;

      if (m.longValue() >= 1L) {
        Boolean andResult_179 = false;

        if (m.longValue() <= 12L) {
          Boolean andResult_180 = false;

          if (d.longValue() >= 1L) {
            if (d.longValue() <= DaysOfMonth(y, m).longValue()) {
              andResult_180 = true;
            }
          }

          if (andResult_180) {
            andResult_179 = true;
          }
        }

        if (andResult_179) {
          andResult_178 = true;
        }
      }

      if (andResult_178) {
        andResult_177 = true;
      }
    }

    return andResult_177;
  }

  public static Boolean IsValidHour(final Number h, final Number m) {

    Boolean andResult_181 = false;

    if (h.longValue() >= 1L) {
      Boolean andResult_182 = false;

      if (h.longValue() <= 24L) {
        Boolean andResult_183 = false;

        if (m.longValue() >= 0L) {
          if (m.longValue() <= 60L) {
            andResult_183 = true;
          }
        }

        if (andResult_183) {
          andResult_182 = true;
        }
      }

      if (andResult_182) {
        andResult_181 = true;
      }
    }

    return andResult_181;
  }

  public static Boolean IsLeapYear(final Number year) {

    Boolean orResult_7 = false;

    Boolean andResult_184 = false;

    if (Utils.equals(Utils.mod(year.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(year.longValue(), 100L), 0L))) {
        andResult_184 = true;
      }
    }

    if (andResult_184) {
      orResult_7 = true;
    } else {
      orResult_7 = Utils.equals(Utils.mod(year.longValue(), 400L), 0L);
    }

    return orResult_7;
  }

  public static Number DaysOfMonth(final Number y, final Number m) {

    Number casesExpResult_1 = null;

    Number intPattern_1 = m;
    Boolean success_4 = Utils.equals(intPattern_1, 1L);

    if (!(success_4)) {
      Number intPattern_2 = m;
      success_4 = Utils.equals(intPattern_2, 3L);

      if (!(success_4)) {
        Number intPattern_3 = m;
        success_4 = Utils.equals(intPattern_3, 5L);

        if (!(success_4)) {
          Number intPattern_4 = m;
          success_4 = Utils.equals(intPattern_4, 7L);

          if (!(success_4)) {
            Number intPattern_5 = m;
            success_4 = Utils.equals(intPattern_5, 8L);

            if (!(success_4)) {
              Number intPattern_6 = m;
              success_4 = Utils.equals(intPattern_6, 10L);

              if (!(success_4)) {
                Number intPattern_7 = m;
                success_4 = Utils.equals(intPattern_7, 12L);

                if (!(success_4)) {
                  Number intPattern_8 = m;
                  success_4 = Utils.equals(intPattern_8, 4L);

                  if (!(success_4)) {
                    Number intPattern_9 = m;
                    success_4 = Utils.equals(intPattern_9, 6L);

                    if (!(success_4)) {
                      Number intPattern_10 = m;
                      success_4 = Utils.equals(intPattern_10, 9L);

                      if (!(success_4)) {
                        Number intPattern_11 = m;
                        success_4 = Utils.equals(intPattern_11, 11L);

                        if (!(success_4)) {
                          Number intPattern_12 = m;
                          success_4 = Utils.equals(intPattern_12, 2L);

                          if (success_4) {
                            Number ternaryIfExp_1 = null;

                            if (IsLeapYear(y)) {
                              ternaryIfExp_1 = 29L;
                            } else {
                              ternaryIfExp_1 = 28L;
                            }

                            casesExpResult_1 = ternaryIfExp_1;
                          }

                        } else {
                          casesExpResult_1 = 30L;
                        }

                      } else {
                        casesExpResult_1 = 30L;
                      }

                    } else {
                      casesExpResult_1 = 30L;
                    }

                  } else {
                    casesExpResult_1 = 30L;
                  }

                } else {
                  casesExpResult_1 = 31L;
                }

              } else {
                casesExpResult_1 = 31L;
              }

            } else {
              casesExpResult_1 = 31L;
            }

          } else {
            casesExpResult_1 = 31L;
          }

        } else {
          casesExpResult_1 = 31L;
        }

      } else {
        casesExpResult_1 = 31L;
      }

    } else {
      casesExpResult_1 = 31L;
    }

    return casesExpResult_1;
  }

  public static Number CreateDate(final Number y, final Number m, final Number d) {

    return y.longValue() * 10000L + m.longValue() * 100L + d.longValue();
  }

  public static Number CreateHour(final Number h, final Number m) {

    return h.longValue() * 100L + m.longValue();
  }

  public static Number Year(final Number d) {

    return Utils.div(d.longValue(), 10000L);
  }

  public static Number Month(final Number d) {

    return Utils.mod(Utils.div(d.longValue(), 100L), 100L);
  }

  public static Number Day(final Number d) {

    return Utils.mod(d.longValue(), 100L);
  }

  public static Number Hours(final Number h) {

    return Utils.div(h.longValue(), 100L);
  }

  public static Number Minutes(final Number h) {

    return Utils.mod(h.longValue(), 100L);
  }

  public static Boolean overlaps(
      final Number startHour1,
      final Number endHour1,
      final Number startHour2,
      final Number endHour2) {

    Boolean orResult_8 = false;

    Boolean andResult_186 = false;

    if (startHour1.longValue() >= startHour2.longValue()) {
      if (startHour1.longValue() < endHour2.longValue()) {
        andResult_186 = true;
      }
    }

    if (andResult_186) {
      orResult_8 = true;
    } else {
      Boolean orResult_9 = false;

      Boolean andResult_187 = false;

      if (endHour1.longValue() > startHour2.longValue()) {
        if (endHour1.longValue() <= endHour2.longValue()) {
          andResult_187 = true;
        }
      }

      if (andResult_187) {
        orResult_9 = true;
      } else {
        Boolean andResult_188 = false;

        if (startHour1.longValue() <= startHour2.longValue()) {
          if (endHour1.longValue() >= endHour2.longValue()) {
            andResult_188 = true;
          }
        }

        orResult_9 = andResult_188;
      }

      orResult_8 = orResult_9;
    }

    return orResult_8;
  }

  public String toString() {

    return "cg_Utils{}";
  }
}
