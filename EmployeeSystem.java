import java.util.ArrayList;
import java.util.Scanner;

/*
 
- Julio Alva

*/

abstract class Person { // BEGIN
    private String name;
    private String iD;

    // Default constructor
    public Person() {
        this.name = null;
        this.iD = null;
    }

    // Constructor
    public Person(Scanner scnr) {
        setName(scnr);
        System.out.println("ID: ");
    }

    // Setters
    public void setName(Scanner scnr) {
        this.name = scnr.nextLine();
        this.name = scnr.nextLine();

    }

    public void setID(Scanner scnr) {
        boolean invalidId = true;
        while (invalidId) {
            try {

                this.iD = scnr.next();
                boolean firstChar = Character.isLetter((this.iD).charAt(0));
                boolean secondChar = Character.isLetter((this.iD).charAt(1));
                boolean thridChar = Character.isDigit((this.iD).charAt(2));
                boolean fourthChar = Character.isDigit((this.iD).charAt(3));
                boolean fithChar = Character.isDigit((this.iD).charAt(4));
                boolean sixthChar = Character.isDigit((this.iD).charAt(5));

                if ((this.iD).length() < 6 || (this.iD).length() > 6) {
                    throw new Exception();
                }

                else if (firstChar == false || secondChar == false) {
                    throw new Exception();
                }

                else if (thridChar == false || fourthChar == false || fithChar == false || sixthChar == false) {
                    throw new Exception();
                }
                invalidId = false;

            } catch (Exception IdException) {
                System.out.println("  Invalid id format-Needs to be LetterLetterDigitDigitDigitDigit");
                System.out.println("  Please try again");
                System.out.print("\tID: ");

            }

        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getID() {
        return iD;
    }

    public String toString1() {
        return name + "                   " + iD;
    }

}// END

// Interface to implement printInfo
interface PrintInformation {

    public void printInfo();
}

class Crew extends Person implements PrintInformation { // BEGIN

    private double hourlyRate;
    private int scheduledHours;
    final int biweekly = 2;
    final double federalIncomeTax = 0.0515;
    final double ficaTax = 0.0765;

    // Default constructor
    public Crew() {
        super();
        this.hourlyRate = -1;
        this.scheduledHours = -1;
    }

    // Constructor unique to student
    public Crew(Scanner scnr) {
        System.out.print("Name of Crew: ");
        super.setName(scnr);

        System.out.print("ID: ");
        super.setID(scnr);

        System.out.print("Hourly Rate: ");
        this.hourlyRate = scnr.nextDouble();

        System.out.print("Schedule Hours (biweekly): ");
        this.scheduledHours = scnr.nextInt();
    }

    // Setters
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setCreditHours(int scheduledHours) {
        this.scheduledHours = scheduledHours;
    }

    // Getters
    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getScheduledHours() {
        return scheduledHours;
    }

    // Total pay biweekly
    private double totalPayBiweekly() {
        return (getHourlyRate() * getScheduledHours());
    }

    // From interface
    @Override
    public void printInfo() {
        System.out.println("\t----------------------------------------------------------------------");
        System.out.println("\t-----");
        System.out.println("\t" + super.getName());
        System.out.println("\tHours this pay period: " + getScheduledHours());
        System.out.println("\tHourly Pay Rate: " + getHourlyRate());

        // System.out.println("\t----------------------------------------------------------------------");
        System.out.println("\t-----");

    }

    // Calculate paycheck
    void generatePayStub() {
        System.out.println("\t----------------------------------------------------------------------");
        System.out.println("\t-----");
        printInfo();

        System.out.printf("\tTotal payment net: %.2f", totalPayBiweekly());

        System.out.println("\t----------------------------------------------------------------------");
        System.out.println("\t-----");
    }

}// END

class Manager extends Person implements PrintInformation {// BEGIN
    private String department;
    private String rank;

    // Default constructor
    public Manager() {
        super();
        this.department = null;
        this.rank = null;
    }

    // Constructor unique to faculty
    public Manager(Scanner scnr) {

        System.out.print("Name of manager: ");
        super.setName(scnr);

        System.out.print("ID: ");
        super.setID(scnr);

        // Loop to make sure valid rank is entered
        while (true) {
            System.out.print("Management Position(hourly or salary?): ");
            this.rank = scnr.next();
            if (checkRank(this.rank))
                break;
            else
                System.out.println("\t\tSorry entered position " + "(" + this.rank + ")" + " is invalid");
        }

        // loop to make sure valid department is entered
        while (true) {
            System.out.print("Job Position: ");
            this.department = scnr.next();
            if (checkDepartment(this.department))
                break;
            else
                System.out.println("\t\tSorry entered title " + "(" + this.department + ")" + " is invalid");
        }

    }

    // Setters
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // Getters
    public String getDepartment() {
        return department;
    }

    public String getRank() {
        return rank;
    }

    // Enherints from Superclass
    @Override

    public void printInfo() {
        System.out.println("\t----------------------------------------------------------------------");
        System.out.println("\t-----");
        System.out.println("\t" + super.getName());
        System.out.println("\t" + getDepartment() + " Manager, " + getRank());
        // System.out.println("\t----------------------------------------------------------------------");
        System.out.println("\t-----");
    }

    // Method that returns true or false for department
    private static boolean checkDepartment(String Department) {
        if (Department.compareToIgnoreCase("Service") == 0 || Department.compareToIgnoreCase("kitchen") == 0
                || Department.compareToIgnoreCase("assistant") == 0 || Department.compareToIgnoreCase("general") == 0)
            return true;
        return false;
    }

    // Method that returns true or false for rank
    private static boolean checkRank(String rank) {
        if (rank.compareToIgnoreCase("Salary") == 0 || rank.compareToIgnoreCase("Hourly") == 0)
            return true;
        return false;
    }

}// END

class EmployeeSystem {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<Person> employees = new ArrayList<Person>();
        Person temp;
        String id;

        welcome();
        space();

        char option;
        // While loop for the options
        while (true) {

            printMenu();
            System.out.print("\tEnter your selection: ");
            option = scnr.next().charAt(0);
            space();

            switch (option) {

                case '1':
                    // Add manager info
                    System.out.println("Enter the Manager's info: ");
                    temp = new Manager(scnr);
                    employees.add(temp);
                    System.out.println("Thanks!");

                    space();
                    break;

                case '2':
                    // Add crew info
                    System.out.println("Enter the crew member's info: ");
                    temp = new Crew(scnr);
                    employees.add(temp);
                    System.out.println("Thanks!");

                    space();
                    break;

                case '3':
                    // Print crew info
                    temp = new Crew();
                    System.out.print("\tEnter the crew's id: ");
                    id = scnr.next();

                    if (searchCrewID(employees, id)) {

                    } else {
                        System.out.println("tCrew member not found!");
                    }

                    space();
                    break;

                case '4':

                    // Print manager's info
                    temp = new Manager();
                    System.out.print("\tEnter the managers's id: ");
                    id = scnr.next();

                    if (searchManagerID(employees, id)) {

                    } else {
                        System.out.print("\tSorry " + id + " doesn't exist");
                    }

                    space();
                    break;

                case '5':

                    System.out.println("\tDone!");
                    System.exit(0);
                    space();
                    break;

                default:

                    System.out.println("\tInvalid entry-try again");
                    space();
                    break;

            }

        }
    }

    // Method for welcome message
    public static void welcome() {
        System.out.println("\t\t\t\tEmployee Management Program");
        space();
    }

    // Method to add space
    public static void space() {
        System.out.println("");
    }

    // Method to print Menu
    public static void printMenu() {
        System.out.println("What do you need to do?:");
        space();
        System.out.println("1-Add a new manager");
        System.out.println("2-Add a new employee");
        System.out.println("3-Print informantion of an employee");
        System.out.println("4-Print information of a manager");
        System.out.println("5-Exit");

    }

    // Method returns true if crew ID is found in ArrayList
    public static boolean searchCrewID(ArrayList<Person> array, String ID) {
        Person temp;
        int i;

        for (i = 0; i < array.size(); i++) {
            temp = array.get(i);
            if (temp.getID().equalsIgnoreCase(ID)) {

                if (temp.getClass().toString().equals("class Crew") == true) {
                    System.out.println("\tCrew Found:  ");
                    ((Crew) temp).printInfo();
                    return true;

                }

            }

        }
        return false;
    }

    // Method returns true if manager ID is found in ArrayList and prints info
    public static boolean searchManagerID(ArrayList<Person> array, String ID) {
        Person temp;
        int i;

        for (i = 0; i < array.size(); i++) {
            temp = array.get(i);
            if (temp.getID().equalsIgnoreCase(ID)) {
                if (temp.getClass().toString().equals("class Manager") == true) {
                    System.out.println("\tManager found:");
                    ((Manager) temp).printInfo();
                    return true;
                }
            }
        }

        return false;
    }

}