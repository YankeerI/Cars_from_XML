
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String filePath = "cars.xml";
        List<Car> cars = Cars.loadCars(filePath);

        int choice;

        do {
            System.out.println("Choose filter option:");
            System.out.println("1) By Manufacturer");
            System.out.println("2) By Production Year");
            System.out.println("3) By Fuel Type and Consumption");
            System.out.println("0) Exit");


            choice = Cars.readInt(scanner, "Your choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter manufacturer: ");
                    String manufacturer = Cars.readManufacturer(scanner, cars);
                    Cars.filterByManufacturer(cars, manufacturer);
                    break;
                case 2:
                    int firstYear = Cars.readInt(scanner, "Enter first year: ");
                    int secondYear = Cars.readInt(scanner, "Enter second year: ");
                    Cars.filterByYear(cars, firstYear, secondYear);
                    break;
                case 3:
                    System.out.print("Enter fuel type (electric, fuel, hybrid): ");
                    String fuelType = scanner.nextLine().toLowerCase();

                    float minConsumption=0;
                    float maxConsumption=0;
                    if (fuelType.equals("fuel") || fuelType.equals("hybrid")) {
                        minConsumption = Cars.readFloat(scanner, "Enter min consumption: ");
                        maxConsumption = Cars.readFloat(scanner, "Enter max consumption: ");
                    }
                    Cars.filterByFuelType(cars, fuelType, minConsumption, maxConsumption);
                    break;
                case 0:
                    System.out.println("Exiting.");
                    break;
                default:
                    System.out.println("Invalid choice.");}

            System.out.println();
        } while (choice != 0);
        scanner.close();














    }



}
