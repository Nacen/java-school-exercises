import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A4 {

  public static boolean isValidRange(double numberInput, int startRange, int endRange) {
    return numberInput <= endRange && numberInput >= startRange;
  }

  private static DecimalFormat df2 = new DecimalFormat("#.##");

  public static boolean isValidStudentNumber(String studentNumber) {
    Pattern pattern = Pattern.compile("^PM-\\d{2}-\\d{5}?$");
    Matcher matcher = pattern.matcher(studentNumber);
    return matcher.matches();
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String[] surNames = new String[3];
    String[] firstNames = new String[3];
    String[] middleNames = new String[3];

    String[] studentNumbers = new String[3];
    int[] yearLevels = new int[3];
    int[][] birthdays = new int[3][3];
    double[][] studentGrades = new double[3][6];

    boolean invalidInput = true;
    int index = 0;
    do {
      System.out.println("STUDENT INFORMATION ENTRY");
      System.out.println("");
      System.out.println("ENTER STUDENT NAME");
      System.out.print("SURNAME: ");
      surNames[index] = input.nextLine();
      System.out.print("FIRST NAME: ");
      firstNames[index] = input.nextLine();
      System.out.print("MIDDLE NAME: ");
      middleNames[index] = input.nextLine();
      System.out.println("");

      do {
        System.out.print("STUDENT NUMBER: ");
        String studentNumber = input.nextLine();

        if (isValidStudentNumber(studentNumber)) {
          invalidInput = false;
          studentNumbers[index] = studentNumber;
        }
      } while (invalidInput);

      invalidInput = true;
      System.out.println("");
      do {
        System.out.print("YEAR LEVEL: ");
        int yearLevel = 0;

        try {
          yearLevel = input.nextInt();
        } catch (Exception e) {
        }
        input.nextLine();

        if (isValidRange(yearLevel, 1, 4)) {
          yearLevels[index] = yearLevel;
          invalidInput = false;
          System.out.println("");
        }
      } while (invalidInput);
      System.out.println("");
      invalidInput = true;
      System.out.println("ENTER BIRTHDAY: ");

      do {
        System.out.print("BIRTHMONTH: ");
        int birthMonth = 0;
        try {
          birthMonth = input.nextInt();
        } catch (InputMismatchException e) {
        }
        input.nextLine();
        if (isValidRange(birthMonth, 1, 12)) {
          birthdays[index][0] = birthMonth;
          invalidInput = false;
        }
      } while (invalidInput);

      invalidInput = true;

      do {
        System.out.print("BIRTHDAY: ");
        int birthDay = 0;

        try {
          birthDay = input.nextInt();
        } catch (InputMismatchException e) {
        }
        input.nextLine();

        if (isValidRange(birthDay, 1, 31)) {
          birthdays[index][1] = birthDay;
          invalidInput = false;
        }
      } while (invalidInput);

      invalidInput = true;

      do {
        System.out.print("BIRTHYEAR: ");
        int birthYear = 0;
        try {
          birthYear = input.nextInt();
        } catch (InputMismatchException e) {
        }
        input.nextLine();

        if (isValidRange(birthYear, 1900, 2003)) {
          birthdays[index][2] = birthYear;
          invalidInput = false;
        }
      } while (invalidInput);

      System.out.println("");
      System.out.println("Enter Student Grades");

      for (int i = 0, grades = 1; i < studentGrades[index].length;) {
        System.out.print("ITC10" + grades + ": ");
        double studentGrade = 0;
        try {
          studentGrade = input.nextDouble();
        } catch (Exception e) {
        }
        input.nextLine();
        if (isValidRange(studentGrade, 50, 100)) {
          studentGrades[index][i] = studentGrade;
          i++;
          grades++;
        }
      }

      invalidInput = true;
      System.out.println("");
      index++;
    } while (index < 3);

    input.close();

    for (int i = 0; i < 3; i++) {
      System.out.println("STUDENT INFORMATION");
      System.out.println("NAME: " + surNames[i] + ", " + firstNames[i] + " " + middleNames[i]);
      System.out.println("STUDENT NUMBER: " + studentNumbers[i]);
      System.out.println("YEAR LEVEL: " + yearLevels[i]);
      System.out.println("BIRTHDAY: " + birthdays[i][0] + "/" + birthdays[i][1] + "/" + birthdays[i][2]);
      System.out.println("");
      System.out.println("GRADES");
      double sum = 0;
      for (int j = 0, grades = 1; j < 6; j++, grades++) {
        System.out.println("ITC10" + grades + ": " + studentGrades[i][j]);
        sum += studentGrades[i][j];
      }
      double average = sum / 6;
      System.out.println();
      System.out.println("WEIGHTED AVERAGE: " + df2.format(average));
      System.out.println();
    }

  }
}
