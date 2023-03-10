
  package ee.taltech.iti0202.idcode;


  import java.util.Arrays;
  import java.util.List;

  public class IdCode {

      public static final int YEAR_BEGIN_INDEX = 7;
      public static final int YEAR_END_INDEX = 10;
      public static final int KURESSAARE_ALGUS = 1;
      public static final int KURESSAARE_LOPP = 10;
      public static final int TARTU_ALGUS = 11;
      public static final int TARTU_LOPP = 20;
      public static final int TALLINNA_ALGUS = 21;
      public static final int TALLINNA_LOPP = 220;
      public static final int KOHTLA_ALGUS = 221;
      public static final int KOHTLA_LOPP = 270;
      public static final int TARTU2_ALGUS = 271;
      public static final int TARTU2_LOPP = 370;
      public static final int NARVA_ALGUS = 371;
      public static final int NARVA_LOPP = 420;
      public static final int PARNU_ALGUS = 421;
      public static final int PARNU_LOPP = 470;
      public static final int TALLIN2_ALGUS = 471;
      public static final int TALLINN2_LOPP = 490;
      public static final int PAIDE_ALGUS = 491;
      public static final int PAIDE_LOPP = 520;
      public static final int RAKVERE_ALGUS = 521;
      public static final int RAKVERE_LOPP = 570;
      public static final int VALGA_ALGUS = 571;
      public static final int VALGA_LOPP = 600;
      public static final int VILJANDI_ALGUS = 601;
      public static final int VILJANDI_LOPP = 650;
      public static final int VORU_ALGUS = 651;
      public static final int VORU_LOPP = 710;
      public static final int UNKNOWN_ALGUS = 711;
      public static final int UNKNOWN_LOPP = 999;
      public static final int ZERO = 0;
      public static final int BYEAR_BEGIN_INDEX = 1;
      public static final int BYEAR_END_INDEX = 3;
      public static final int NEXT_YEAR = 2023;
      public static final int MONTHS = 12;
      public static final int ELEVEN = 11;
      public static final int FOURHUNDRED = 400;
      public static final int HUNDRED = 100;

      private final String idCodeValue;
      enum Gender {
          MALE, FEMALE
      }
      /**
       * Method returns the id code.
       *
       * @return id code.
       */
      public String getIdCodeValue() {
          return idCodeValue;
      }
      public IdCode(String idCodeValue) {
          this.idCodeValue = idCodeValue;
      }

      /**
       * Check if the id code is valid or not.
       *
       * @return boolean describing whether or not the id code was correct.
       */
      public boolean isCorrect() {
          if (isGenderNumberCorrect()) {
              if (isControlNumberCorrect()) {
                  if (isDayNumberCorrect()) {
                      if (isMonthNumberCorrect()) {
                          if (isYearNumberCorrect()) {
                              return true;
                          }
                      }
                  }
              }
          }
          if (!isGenderNumberCorrect()) {
              if (!isControlNumberCorrect()) {
                  if (!isDayNumberCorrect()) {
                      if (!isMonthNumberCorrect()) {
                          if (!isYearNumberCorrect()) {
                              return false;
                          }
                      }
                  }
              }
          }
          return false;
      }
      /**
       * Get all information about id code.
       * @return String containing information.
       */
      public String getInformation() {
          String birtday = idCodeValue.substring(5, 7) + "." + idCodeValue.substring(3, 5) + "." + getFullYear();
          return "This is a " + getGender() + " born on " + birtday + " in " + getBirthPlace();
      }
      /**
       * Get gender enum.
       * @return enum describing person's gender
       */
      public Gender getGender() {
          List<Integer> women = Arrays.asList(2, 4, 6);
          List<Integer> men = Arrays.asList(1, 3, 5);
          int genderNumber = Character.getNumericValue(idCodeValue.charAt(0));
          if (women.contains(genderNumber)) {
              return Gender.FEMALE;

          }
          if (men.contains(genderNumber)) {
              return Gender.MALE;
          }
          return null;
      }

      /**
       * Get person's birth location.
       * @return String with the person's birth place.
       */
      public String getBirthPlace() {
          int birthnr = Integer.parseInt(idCodeValue.substring(YEAR_BEGIN_INDEX, YEAR_END_INDEX)
                  .replaceFirst("^0*", ""));
          if (KURESSAARE_ALGUS <= birthnr && birthnr <= KURESSAARE_LOPP) {
              return "Kuressaare";
          }
          if (TARTU_ALGUS <= birthnr && birthnr <= TARTU_LOPP) {
              return "Tartu";
          }
          if (TALLINNA_ALGUS <= birthnr && birthnr <= TALLINNA_LOPP) {
              return "Tallinn";
          }
          if (KOHTLA_ALGUS <= birthnr && birthnr <= KOHTLA_LOPP) {
              return "Kohtla-J??rve";
          }
          if (TARTU2_ALGUS <= birthnr && birthnr <= TARTU2_LOPP) {
              return "Tartu";
          }
          if (NARVA_ALGUS <= birthnr && birthnr <= NARVA_LOPP) {
              return "Narva";
          }
          if (PARNU_ALGUS <= birthnr && birthnr <= PARNU_LOPP) {
              return "P??rnu";
          }
          if (TALLIN2_ALGUS <= birthnr && birthnr <= TALLINN2_LOPP) {
              return "Tallinn";
          }
          if (PAIDE_ALGUS <= birthnr && birthnr <= PAIDE_LOPP) {
              return "Paide";
          }
          if (RAKVERE_ALGUS <= birthnr && birthnr <= RAKVERE_LOPP) {
              return "Rakvere";
          }
          if (VALGA_ALGUS <= birthnr && birthnr <= VALGA_LOPP) {
              return "Valga";
          }
          if (VILJANDI_ALGUS <= birthnr && birthnr <= VILJANDI_LOPP) {
              return "Viljandi";
          }
          if (VORU_ALGUS <= birthnr && birthnr <= VORU_LOPP) {
              return "V??ru";
          }
          if (UNKNOWN_ALGUS <= birthnr && birthnr <= UNKNOWN_LOPP || birthnr == ZERO) {
              return "unknown";
          }
          return null;
      }
      /**
       * Get the year that the person was born in.
       * @return int with person's birth year.
       */
      public int getFullYear() {
          String yearNR = idCodeValue.substring(BYEAR_BEGIN_INDEX, BYEAR_END_INDEX);
          int genderNumber = Character.getNumericValue(idCodeValue.charAt(0));
          if (genderNumber == 1 || genderNumber == 2) {
              return Integer.parseInt("18" + yearNR);
          }
          if (genderNumber == 3 || genderNumber == 4) {
              return Integer.parseInt("19" + yearNR);
          }
          if (genderNumber == 5 || genderNumber == 6) {
              return Integer.parseInt("20" + yearNR);
          }
          return 0;
      }
      /**
       * Check if gender number is correct.
       * @return boolean describing whether the gender number is correct.
       */
      private boolean isGenderNumberCorrect() {
          int genderNumber = Integer.parseInt(idCodeValue.substring(0, 1));
          return 0 < genderNumber && genderNumber <= 6;
      }
      /**
       * Check if the year number is correct.
       * @return boolean describing whether the year number is correct.
       */
      private boolean isYearNumberCorrect() {
          return getFullYear() < NEXT_YEAR;
      }
      /**
       * Check if the month number is correct.
       * @return boolean describing whether the month number is correct.
       */
      private boolean isMonthNumberCorrect() {
          int monthNR = Integer.parseInt(idCodeValue.substring(3, 5));
          return 1 <= monthNR && monthNR <= MONTHS;
      }
      /**
       * Check if the day number is correct.
       * @return boolean describing whether the day number is correct.
       */
      private boolean isDayNumberCorrect() {
          return false;
      }
      /**
       * Check if the control number is correct.
       * @return boolean describing whether the control number is correct.
       */
      private boolean isControlNumberCorrect() {
          List<Integer> firstBase = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 1);
          List<Integer> secondBase = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 1, 2, 3);
          int sumNr1 = 0;
          int sumNr2 = 0;
          for (int i: firstBase) {
              sumNr1 += firstBase.indexOf(i) * Integer.parseInt(idCodeValue.substring(i));
          }
          int controlNr = sumNr1 % ELEVEN;
          if (controlNr == Integer.parseInt(idCodeValue.substring(idCodeValue.length() - 1))) {
              return true;
          }
          if (controlNr != Integer.parseInt(idCodeValue.substring(idCodeValue.length() - 1))) {
              return false;
          }
          if (controlNr == 10) {
              for (int i: secondBase) {
                  sumNr2 += secondBase.indexOf(i) * Integer.parseInt(idCodeValue.substring(i));
              }
              int controlNr2 = sumNr2 % ELEVEN;
              if (controlNr2 == Integer.parseInt(idCodeValue.substring(idCodeValue.length() - 1))) {
                  return true;
              }
              if (controlNr2 != Integer.parseInt(idCodeValue.substring(idCodeValue.length() - 1))) {
                  return false;
              }
              if (controlNr2 == 10) {
                  return controlNr2 == Integer.parseInt(idCodeValue.substring(idCodeValue.length() - 1));
              }
          }
          return false;
      }

      /**
       * Check if the given year is a leap year.
       * @param fullYear
       * @return boolean describing whether the given year is a leap year.
       */
      private boolean isLeapYear(int fullYear) {
          return fullYear % 4 == 0 && fullYear % HUNDRED != 0 || fullYear % FOURHUNDRED == 0;
      }
      /**
       * Run tests.
       * @param args info.
       */
      public static void main(String[] args) {
          IdCode validMaleIdCode = new IdCode("30205030299");
          System.out.println(validMaleIdCode.isCorrect());
          System.out.println(validMaleIdCode.getInformation());
          System.out.println(validMaleIdCode.getGender());
          System.out.println(validMaleIdCode.getBirthPlace());
          System.out.println(validMaleIdCode.getFullYear());
          System.out.println(validMaleIdCode.isGenderNumberCorrect());
          System.out.println(validMaleIdCode.isYearNumberCorrect());
          System.out.println(validMaleIdCode.isMonthNumberCorrect());
          System.out.println(validMaleIdCode.isDayNumberCorrect());
          System.out.println(validMaleIdCode.isControlNumberCorrect());
          System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
      }

  }
