public class Car {



    String manufacturer;
    String model;
    int productionYear;
    int horsepower;
    float consumption;
    String consumptionType;
    float price;

    public Car(String manufacturer, String model, int productionYear, int horsepower,
               float consumption, String consumptionType, float price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.productionYear = productionYear;
        this.horsepower = horsepower;
        this.consumption = consumption;
        this.consumptionType = consumptionType;
        this.price = price;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public String getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(String consumptionType) {
        this.consumptionType = consumptionType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        System.out.println();                                    
        return
                "Manufacturer: " + manufacturer + "\n" +
                "Model: " + model + "\n" +
                "Year: " + productionYear + "\n" +
                "Horsepower: " + horsepower + "\n" +
                "Consumption (" + consumptionType + "): " + consumption + "\n" +
                "Price: $" + price + "\n";
    }








}
