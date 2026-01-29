public class CarDTO {
    private int id;
    private String brand;
    private String model;
    private int pricePerDay;
    private boolean available;

    public CarDTO(int id, String brand, String model, int pricePerDay, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return available; }

    public void setId(int id) { this.id = id; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setPricePerDay(int pricePerDay) { this.pricePerDay = pricePerDay; }
    public void setAvailable(boolean available) { this.available = available; }
}
