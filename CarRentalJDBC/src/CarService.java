import java.util.List;

public class CarService {

    private CarDAO dao = new CarDAO();

    public void addCar(CarDTO car) {
        dao.addCar(car);
    }

    public CarDTO getCar(int id) {
        return dao.getCar(id);
    }

    public List<CarDTO> getAllCars() {
        return dao.getAllCars();
    }

    public void updateCar(CarDTO car) {
        dao.updateCar(car);
    }

    public void deleteCar(int id) {
        dao.deleteCar(id);
    }

    public void rentCar(int id) {
        dao.rentCar(id);
    }

    public void returnCar(int id) {
        dao.returnCar(id);
    }
}
