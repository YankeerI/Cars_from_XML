import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Cars {

    private static final Logger logger = Logger.getLogger(Cars.class.getName());    

    public static List<Car> loadCars(String filePath) throws Exception {
        List<Car> cars = new ArrayList<>();                                      
        FileInputStream fis = new FileInputStream(filePath);                                
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);

        String currentElement = "";                                         
        String manufacturer = "";
        String model = "";
        int productionYear = 0;
        int horsepower = 0;
        float consumption = 0;
        String consumptionType = "";
        float price = 0;

        while (reader.hasNext()) {                                               
            int event = reader.next();                                          

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    currentElement = reader.getLocalName();                          
                    if ("consumption".equals(currentElement)) {                       
                        consumptionType = reader.getAttributeValue(null, "type");
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();                            
                    if (!text.isEmpty()) {
                        switch (currentElement) {                                     
                            case "manufacturer":
                                manufacturer = text;
                                break;
                            case "model":
                                model = text;
                                break;
                            case "production-year":
                                try {
                                    productionYear = Integer.parseInt(text);
                                } catch (NumberFormatException e) {
                                    logger.warning("Warning: Invalid production-year '" + text + "'. Defaulting to 0.");
                                    productionYear = 0;
                                }
                                break;
                            case "horsepower":
                                try {
                                    horsepower = Integer.parseInt(text);
                                } catch (NumberFormatException e) {
                                    logger.warning("Warning: Invalid horsepower '" + text + "'. Defaulting to 0.");
                                    horsepower = 0;
                                }
                                break;
                            case "consumption":
                                try {
                                    consumption = Float.parseFloat(text);
                                } catch (NumberFormatException e) {
                                    logger.warning("Warning: Invalid consumption '" + text + "'. Defaulting to 0.0.");
                                    consumption = 0f;
                                }
                                break;
                            case "price":
                                try {
                                    price = Float.parseFloat(text);
                                } catch (NumberFormatException e) {
                                    logger.warning("Warning: Invalid price '" + text + "'. Defaulting to 0.0.");
                                    price = 0f;
                                }
                                break;
                        }
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:                             
                    if ("car".equals(reader.getLocalName())) {

                        if (manufacturer == null || manufacturer.isEmpty()) {
                            logger.warning("Skipping car: missing manufacturer.");
                        } else if (model == null || model.isEmpty()) {
                            logger.warning("Skipping car: missing model.");
                        } else {
                            Car car = new Car(manufacturer, model, productionYear, horsepower,
                                                consumption, consumptionType, price);
                            cars.add(car);
                        }

                        manufacturer = "";                             
                        model = "";
                        productionYear = 0;
                        horsepower = 0;
                        consumption = 0;
                        consumptionType = "";
                        price = 0;
                    }
                    break;
            }
        }

        reader.close();
        fis.close();

        return cars;                                                       
    }

//filtering methods

    public static void filterByManufacturer(List<Car> cars, String manufacturerFilter) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getManufacturer().equalsIgnoreCase(manufacturerFilter)) {
                System.out.println(car);                                                            
            }
        }
    }

    public static void filterByYear(List<Car> cars, int startYear, int endYear) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            int year = car.getProductionYear();
            if (year >= startYear && year <= endYear) {
                System.out.println(car);                                                      
            }
        }
    }

    public static void filterByFuelType(List<Car> cars, String fuelTypeFilter, float minConsumption, float maxConsumption) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getConsumptionType() == null || !car.getConsumptionType().equalsIgnoreCase(fuelTypeFilter)) {
                continue;                                                                                          
            }
            if (fuelTypeFilter.equals("fuel") || fuelTypeFilter.equals("hybrid")) {
                float consumption = car.getConsumption();
                if (consumption < minConsumption || consumption > maxConsumption) {
                    continue;                                                            
                }
            }
            System.out.println(car);                                                    
        }
    }

    //input methods

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine();                                                   
                return val;                                                          
            } else {
                logger.warning("Invalid input. Please enter a valid integer.");
                System.out.println("Invalid input. Please enter a valid integer.");              
                scanner.nextLine();                                                   
            }
        }

    }

    public static float readFloat(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextFloat()) {
                float val = scanner.nextFloat();
                scanner.nextLine();                                                         
                return val;                                                                
            } else {
                logger.warning("Invalid input. Please enter a valid decimal number.");
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.nextLine();                                                          
            }
        }
    }

    public static String readManufacturer(Scanner scanner, List<Car> cars) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                logger.warning("Manufacturer cannot be empty. Please try again.");
                System.out.println("Manufacturer cannot be empty. Please try again.");
                System.out.print("Enter manufacturer: ");                                          
                continue;                                                                          
            }


            for (int i = 0; i < cars.size(); i++) {                                                
                Car car = cars.get(i);
                if (car.getManufacturer().equalsIgnoreCase(input)) {
                    return input;                                                      
                }
            }

            logger.warning("Manufacturer not found.");                  
            System.out.println("Manufacturer not found.");
            System.out.print("Enter manufacturer: ");                                      
        }
    }


}
