package api_rest_tests;

import controllers.AuthController;
import data_providers.UserDataProvider;
import dto.TokenDto;
import dto.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesReader;

import static utils.UserFactory.*;

public class RegistrationLoginTests extends AuthController {
    @Test
    public void registrationPositiveTest() {
        User user = positiveUser();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 200);
    }

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
        System.out.println(response.getBody().asString());
        TokenDto tokenDto = response.getBody().as(TokenDto.class);
        System.out.println(tokenDto.getAccessToken());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(dataProvider = "dataProviderForRegistrationWrongPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordOrEmailTest(User user) {
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 400);
    }

    @Test(dataProvider = "dataProviderForLoginWrongPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void loginNegativeWrongPasswordOrEmailTest(User user) {
        Assert.assertEquals(registrationLogin(user, LOGIN_URL).getStatusCode(), 401);
    }

    @Test
    public void registrationWithEmptyEmailNegativeTest() {
        User user = positiveUser();
        user.setUsername("");
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 400);
    }

    @Test
    public void registrationWithEmptyPasswordNegativeTest() {
        User user = positiveUser();
        user.setPassword("");
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 400);
    }

    @Test
    public void loginWithEmptyEmailNegativeTest() {
        User user = User.builder()
                .username("")
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Assert.assertEquals(registrationLogin(user, LOGIN_URL).getStatusCode(), 401);

    }

    @Test
    public void loginWithEmptyPasswordNegativeTest() {
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password("")
                .build();
        Assert.assertEquals(registrationLogin(user, LOGIN_URL).getStatusCode(), 401);

    }

    @Test
    public void loginWithWrongEndPointNegativeTest() {
        User user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Assert.assertEquals(registrationLogin(user, ADD_CAR_URL).getStatusCode(), 403);

    }
}
