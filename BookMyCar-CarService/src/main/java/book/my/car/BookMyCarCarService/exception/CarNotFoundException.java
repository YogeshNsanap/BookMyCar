package book.my.car.BookMyCarCarService.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(String carId){
        super("Car not found with id: "+carId);
    }
}
