import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CarService service = new CarService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CAR RENTAL SYSTEM =====");
            System.out.println("1. Add Car");
            System.out.println("2. View Car By ID");
            System.out.println("3. View All Cars");
            System.out.println("4. Update Car");
            System.out.println("5. Delete Car");
            System.out.println("6. Rent Car");
            System.out.println("7. Return Car");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Brand: ");
                    String brand = sc.next();
                    System.out.print("Enter Model: ");
                    String model = sc.next();
                    System.out.print("Price per Day: ");
                    int price = sc.nextInt();
                    service.addCar(new CarDTO(0, brand, model, price, true));
                    break;

                case 2:
                    System.out.print("Enter Car ID: ");
                    int id = sc.nextInt();
                    CarDTO car = service.getCar(id);
                    if (car != null)
                        System.out.println(car.getId() + " " + car.getBrand() + " " + car.getModel() + " " + car.getPricePerDay() + " " + car.isAvailable());
                    else
                        System.out.println("Car not found");
                    break;

                case 3:
                    List<CarDTO> list = service.getAllCars();
                    if (list.isEmpty()) System.out.println("No cars found!");
                    else
                        list.forEach(c ->
                                System.out.println(c.getId() + " " + c.getBrand() + " " + c.getModel() + " " + c.getPricePerDay() + " " + c.isAvailable())
                        );
                    break;

                case 4:
                    System.out.print("Enter Car ID to Update: ");
                    int uid = sc.nextInt();
                    System.out.print("New Brand: ");
                    String nBrand = sc.next();
                    System.out.print("New Model: ");
                    String nModel = sc.next();
                    System.out.print("New Price: ");
                    int nPrice = sc.nextInt();
                    System.out.print("Available (true/false): ");
                    boolean avail = sc.nextBoolean();
                    service.updateCar(new CarDTO(uid, nBrand, nModel, nPrice, avail));
                    break;

                case 5:
                    System.out.print("Enter Car ID to Delete: ");
                    int did = sc.nextInt();
                    service.deleteCar(did);
                    break;

                case 6:
                    System.out.print("Enter Car ID to Rent: ");
                    int rid = sc.nextInt();
                    service.rentCar(rid);
                    break;

                case 7:
                    System.out.print("Enter Car ID to Return: ");
                    int retid = sc.nextInt();
                    service.returnCar(retid);
                    break;

                case 8:
                    System.out.println("Thank you for using Car Rental System!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
