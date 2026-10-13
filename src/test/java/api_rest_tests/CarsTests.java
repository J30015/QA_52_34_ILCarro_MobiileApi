package api_rest_tests;

import dto.Car;
import org.testng.Assert;
import org.testng.annotations.Test;
import controllers.CarController;
import utils.CarFactory;

public class CarsTests extends CarController {
    @Test
    public void addNewCarPositiveTest(){
        Car car = CarFactory.positiveCar();
        System.out.println(car.toString());
        Assert.assertEquals(addNewCar(car).getStatusCode(),200);
    }
}
