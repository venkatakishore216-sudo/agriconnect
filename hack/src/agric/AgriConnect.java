package agric;

/**
 * Class representing individual crop details entered by a farmer.
 */
public class AgriConnect {
    String farmerName;
    String cropName;
    int quantity;
    double price;

    public AgriConnect(String farmerName, String cropName, int quantity, double price) {
        this.farmerName = farmerName;
        this.cropName = cropName;
        this.quantity = quantity;
        this.price = price;
    }
}
