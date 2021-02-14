import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Billing {

  public static int getHours(double minutes) {
    double hours = (minutes / 60);
    if (hours != Math.floor(hours)) {
      return (int) Math.floor(hours) + 1;
    }
    return (int) hours;
  }

  public static double calculateBill(int hours, String packageChoice) {
    switch (packageChoice) {
      case "A":
        return 0.95 * hours;
      case "B":
        return 2.95 * hours;
      case "C":
        return 4.95 * hours;
      default:
        return 0;
    }
  }

  public static boolean isValidPackageChoice(String packageChoice) {
    Pattern pattern = Pattern.compile("[ABC]", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(packageChoice);
    return matcher.matches();
  }

  public static void main(String[] args) {
    String[] packages = new String[3];
    int[] time = new int[3];
    double[] charges = new double[3];
    Scanner userInput = new Scanner(System.in);
    boolean invalidInput = true;
    int index = 0;
    do {

      do {
        System.out.print("WHAT PACKAGE DID CUSTOMER #" + Integer.toString(index + 1) + " avail: ");
        String packageChoice = userInput.nextLine();
        if (isValidPackageChoice(packageChoice)) {
          invalidInput = false;
          packages[index] = packageChoice.toUpperCase();
        } else {
          System.out.println("INVALID INPUT!");
        }
      } while (invalidInput);

      invalidInput = true;

      do {
        System.out.print("HOW MANY MINUTES DID CUSTOMER #" + Integer.toString(index + 1) + " CONSUME:");
        int minutes = userInput.nextInt();
        userInput.nextLine();

        if (minutes < 0) {
          System.out.println("INVALID INPUT!");
        } else {
          invalidInput = false;
          time[index] = minutes;
        }
      } while (invalidInput);

      charges[index] = calculateBill(getHours(time[index]), packages[index]); 
      index++;
      invalidInput = true;

    } while (index < 3);

    System.out.println("Customer Charges: ");
    for (var i = 1; i <= charges.length; i++) {
      System.out.println("Customer #" + Integer.toString(i) + ": $"  + charges[i-1]);
    }

  }
}