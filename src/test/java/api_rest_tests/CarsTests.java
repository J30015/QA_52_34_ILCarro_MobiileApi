package api_rest_tests;

import dto.Car;
import dto.CitiesDto;
import dto.CityDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import controllers.CarController;
import utils.CarFactory;
import utils.CityFactory;

import static io.restassured.RestAssured.given;

public class CarsTests extends CarController {
    @Test
    public void addNewCarPositiveTest() {
        Car car = CarFactory.positiveCar();
        Response response = addNewCar(car);

        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void getCarPositiveTest() {
        Car car = new Car();
        Response response = getCar(car);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(getCar(car).getStatusCode(), 200);
    }

    @Test
    public void getCarNegativeTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + GET_USER_CARS)
                .thenReturn();

        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void getAllCities() {
        Response response = getCities();
        System.out.println("STATUS = " + response.getStatusCode());
        System.out.println("BODY = " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}



