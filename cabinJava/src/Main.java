import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);
        Cabin[]cabinList = new Cabin[12];
        String option;
        String firstname;
        String surname;
        double expenses;
        int cabinNum;
        initialise(cabinList);

        do {
            System.out.println("\nChoose from following options.");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Enter 'A' to add customer to a cabin");
            System.out.println("Enter 'V' to view all cabins");
            System.out.println("Enter 'E' to display empty cabins");
            System.out.println("Enter 'D' to delete customer from cabin");
            System.out.println("Enter 'F' to find cabin from customer name");
            System.out.println("Enter 'S' to Store program data into file");
            System.out.println("Enter 'L' to load program data from file");
            System.out.println("Enter 'O' to view passengers ordered alphabetically by name");
            System.out.println("Enter 'T' to print out expenses of passengers");
            System.out.println("Enter 'Q' to quit the program");

            option = input.nextLine().toUpperCase();
            try {
                switch (option) {
                    case "A" -> { //option to add customer to cabin
                        System.out.println("Enter cabin number(0-11)");
                        cabinNum = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter the first name of the customer for cabin " + cabinNum);
                        firstname = input.nextLine();
                        System.out.println("Enter the surname of the customer for cabin " + cabinNum);
                        surname = input.nextLine();
                        while(!firstname.matches("[a-zA-Z]+") || !surname.matches("[a-zA-Z]+") ){   //to validate input as alphabet character
                            System.out.println("Please enter a valid name : ");
                            firstname = input.nextLine();
                            surname = input.nextLine();
                        }
                        System.out.println("Enter expenses for customer: ");
                        expenses = input.nextInt();
                        input.nextLine();
                        add(cabinList, cabinNum, firstname, surname, expenses);
                    }
                    case "V" -> view(cabinList);
                    case "E" -> allEmpty(cabinList);
                    case "D" -> delete(cabinList);
                    case "F" ->{
                        String customerName;
                        System.out.println("Enter customers' first name or surname to find relevant cabin.");
                        customerName = input.nextLine();
                        System.out.println("Customers' cabin number is : "+cabinList[find(cabinList, customerName)].getCabinNum());
                    }
                    case "S" -> {   //option to save program data to file
                        BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", false));
                        writer.write(Arrays.toString(cabinList));
                        writer.newLine();
                        writer.flush();
                        System.out.println("Data stored successfully");
                    }
                    case "L" -> {   //option to load program data from file
                        BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
                        String cruiseData;   //declaring string variable for data in file
                        while ((cruiseData = reader.readLine()) != null) {   //condition is true till character is in the string
                            System.out.println(cruiseData);
                        }
                    }
/*                   case "O" -> {
                        bubbleSort(cabinList);
                        for(Cabin i : cabinList){
                            System.out.println(i);
                        }
                    }*/
                    case "T" -> {
                        price2(cabinList);
                        price1(cabinList);
                    }
                    case "Q" ->
                            System.out.println("\nThank you!");
                    default ->
                            System.out.println("Invalid option.");
                }
            }
            catch (InputMismatchException e) {   //to validate input type is integer
                System.out.println("You must enter cabin numbers between 0-11");
                input.nextLine();
            }
            catch (ArrayIndexOutOfBoundsException e){ //to validate only integers 0-11 entered
                System.out.println("You must enter cain numbers between 0-11");
            }
        }while(!option.equals("Q"));

    }

    private static void initialise(Cabin[] cabinList){
        for(int x = 0; x < 12; x++) {
            Cabin cabin1 = new Cabin("empty", x);
            cabin1.setQ(new CircularQueue(12));
            cabinList[x] = cabin1;
        }
        System.out.println("                 Welcome to Voyager Cruise Ship!! ");
        System.out.println("=====================================================================");
    }

    private static void view(Cabin[] cabinList){  //method to view all cabins
        for(Cabin cabin1: cabinList){
            if (cabin1.getCabinName().equals("empty")) {
                System.out.println("cabin " + cabin1.getCabinNum() + " is empty");
            }
            else {
                System.out.println("cabin " + cabin1.getCabinNum() + " is occupied by "+ cabin1.getPassengers().getFirstName()+" "+cabin1.getPassengers().getSurname());
            }
        }
    }

    public static void add(Cabin[] cabinList, int cabinNum, String fName, String lName, double exp) { //method to add passenger to cabins
        for (Cabin cabin1 : cabinList) {
            if (cabin1.getCabinNum() == cabinNum){
                Passenger p1 = new Passenger(fName, lName, exp);
                if(cabin1.getCabinName().equals("empty")) {
                    cabin1.setPassengers(p1);
                    cabin1.setCabinName("occupied");
                    System.out.println("cabin " + cabin1.getCabinNum() + " is occupied by " + cabin1.getPassengers().getFirstName() + " " + cabin1.getPassengers().getSurname());
                }
                else if(cabin1.getCabinName().equals("occupied")){
                    cabin1.getQ().enQueue(p1);
                    System.out.println(p1.getFirstName()+" "+ p1.getSurname()+" is added to the waiting list");
                }
            }
        }
    }

    public static void allEmpty(Cabin[] cabinList){  // method to view all empty cabins
        for (Cabin cabin1 : cabinList) {
            if (cabin1.getCabinName().equals("empty")) {
                System.out.println("cabin " + cabin1.getCabinNum() + " is empty");
            }
        }
    }

    public static void delete(Cabin[] cabinList){   // method to delete passenger from cabin
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the cabin number to remove customer from it: ");
        int cabinNum = input.nextInt();
        for (Cabin cabin1 : cabinList) {
            if (cabin1.getCabinNum() == cabinNum){
                if(cabin1.getQ().isEmpty()){
                    cabin1.setCabinName("empty");
                }
                else{
                    cabin1.setPassengers(cabin1.getQ().deQueue());
                    System.out.println("cabin " + cabin1.getCabinNum() + " is occupied by " + cabin1.getPassengers().getFirstName() + " " + cabin1.getPassengers().getSurname());
                }

            }
        }
    }

    public static int find(Cabin[] cabinList, String customerName) { //method to find cabin by passenger name
        String firstName;
        String surname;
        for(int x = 0; x < 12; x++) {
            firstName= cabinList[x].getPassengers().getFirstName();
            surname = cabinList[x].getPassengers().getSurname();
            if(firstName == null || surname == null) {
                continue;
            }
            if(firstName.equals(customerName)){
                return x;
            }
            else if(surname.equals(customerName)){
                return x;
            }
        }
        System.out.println("There is no customer by that name.");
        return -1;
    }

/*    private void bubbleSort(Cabin[] cabinList, String fName) {
        String temp;
        for (int j = 0; j < cabinList.length; j++) {
           for (int i = j+1; i < cabinList.length; i++) { //Apply the bubble Sort
               if ( < 0) { // comparing adjacent strings
                    temp = cabinList[j];
                    cabinList[j] = cabinList[i];
                   cabinList[i] = temp;
                }
            }
        }
    }*/

    public static void price1(Cabin[] cabinList){   // to print out total expenses
        double totalExpenses = 0;
        for (Cabin cabin1 : cabinList) {
            if(cabin1 != null){
                totalExpenses += cabin1.getPassengers().getExpenses();
            }
        }
        System.out.println("\nTotal expenses of passengers are : "+totalExpenses);
    }

    public static void price2(Cabin[] cabinList){  // to print out individual passengers expenses
        for (Cabin cabin1 : cabinList) {
            if(cabin1 != null ) {
                if (cabin1.getCabinName().equals("occupied")) {
                    System.out.println("\nExpenses for " + cabin1.getPassengers().getFirstName() + " " + cabin1.getPassengers().getSurname() + " are : " + cabin1.getPassengers().getExpenses());
                }
            }
        }
    }
}





