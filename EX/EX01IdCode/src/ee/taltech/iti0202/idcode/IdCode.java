
  package ee.taltech.iti0202.idcode;

  import org.w3c.dom.ranges.Range;

  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.List;
  import java.util.stream.IntStream;

  public class IdCode {
  
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
          return false;
      }

      /**
       * Get all information about id code.
       * 
       * @return String containing information.
       */
      public String getInformation() {
          return null;
      }

      /**
       * Get gender enum.
       * 
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
       * 
       * @return String with the person's birth place.
       */
      public String getBirthPlace() {
          int birthnr = Integer.parseInt(idCodeValue.substring(8, 11));
          if (1 <= birthnr && birthnr <= 10) {
              return "Kuressaare";
          }
          if (11 <= birthnr && birthnr <= 20) {
              return "Tartu";
          }
          if (21 <= birthnr && birthnr <= 220) {
              return "Tallinn";
          }
          if (221 <= birthnr && birthnr <= 270) {
              return "Kohtla-Järve";
          }
          if (271 <= birthnr && birthnr <= 370) {
              return "Tartu";
          }
          if (371 <= birthnr && birthnr <= 420) {
              return "Narva";
          }
          if (421 <= birthnr && birthnr <= 470) {
              return "Pärnu";
          }
          if (471 <= birthnr && birthnr <= 490) {
              return "Tallinn";
          }
          if (491 <= birthnr && birthnr <= 520) {
              return "Paide";
          }
          if (521 <= birthnr && birthnr <= 570) {
              return "Rakvere";
          }
          if (571 <= birthnr && birthnr <= 600) {
              return "Valga";
          }
          if (601 <= birthnr && birthnr <= 650) {
              return "Viljandi";
          }
          if (651 <= birthnr && birthnr <= 710) {
              return "Võru";
          }
          if (711 <= birthnr && birthnr <= 999 || birthnr == 0) {
              return "unknown";
          }
          return null;
      }

      /**
       * Get the year that the person was born in.
       * 
       * @return int with person's birth year.
       */
      public int getFullYear() {
          int year_nr = Integer.parseInt(idCodeValue.substring(1,3));
          int genderNumber = Character.getNumericValue(idCodeValue.charAt(0));
          if (genderNumber == 1 || genderNumber == 2){
              return Integer.parseInt("18" + year_nr);
          }
          if (genderNumber == 3 || genderNumber == 4) {
              return Integer.parseInt("19" + year_nr);
          }
          if (genderNumber == 5 || genderNumber == 6) {
              return Integer.parseInt("20" + year_nr);
          }
          return 0;
      }
    
      /**
       * Check if gender number is correct.
       * 
       * @return boolean describing whether the gender number is correct.
       */
      private boolean isGenderNumberCorrect() {
          return false;
      }

      /**
       * Check if the year number is correct.
       * 
       * @return boolean describing whether the year number is correct.
       */
      private boolean isYearNumberCorrect() {
          return false;
      }

      /**
       * Check if the month number is correct.
       * 
       * @return boolean describing whether the month number is correct.
       */
      private boolean isMonthNumberCorrect() {
          return false;
      }

      /**
       * Check if the day number is correct.
       * 
       * @return boolean describing whether the day number is correct.
       */
      private boolean isDayNumberCorrect() {
          return false;
      }

      /**
       * Check if the control number is correct.
       * 
       * @return boolean describing whether the control number is correct.
       */
      private boolean isControlNumberCorrect() {
          return false;
      }

      /**
       * Check if the given year is a leap year.
       * 
       * @param fullYear
       * @return boolean describing whether the given year is a leap year.
       */
      private boolean isLeapYear(int fullYear) {
          return false;
      }

      /**
       * Run tests.
       * @param args info.
       */
      public static void main(String[] args) {
          IdCode validMaleIdCode = new IdCode("37605030299");
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
