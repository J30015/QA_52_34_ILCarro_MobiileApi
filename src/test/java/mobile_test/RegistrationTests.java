package mobile_test;

import data_providers.UserDataProvider;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import utils.UserFactory;

public class RegistrationTests extends TestBase {
    SearchScreen searchScreen;
    RegistrationScreen registrationScreen;
    User user;


    @BeforeMethod
    public void goToRegistrationScreen() {
        new SplashScreen(driver).goToSearchScreen();
        searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnRegistration();
        registrationScreen = new RegistrationScreen(driver);
        user = UserFactory.positiveUser();
    }

    @Test
    public void registrationPositiveTest() {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(searchScreen.validatePopUpRegistrationSuccess("Registration success!"));
    }

    @Test
    public void registrationEmptyFieldNameNegativeTest() {
        user.setFirstName("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("All fields must be filled and agree terms"));

    }

    @Test
    public void registrationEmptyFieldUserNameNegativeTest() {
        user.setUsername("");
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("All fields must be filled and agree terms"));

    }

    @Test(dataProvider = "dataProviderForRegistrationWrongPassword", dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(User user) {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage
                ("{password= At least 8 characters;" +
                        "Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number;" +
                        "Can contain special characters [@$#^&*!]}"));

    }

    @Test(dataProvider = "dataProviderForRegistrationWrongEmail", dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongEmailTest(User user) {
        registrationScreen.typeRegistrationForm(user);
        registrationScreen.clickCheckBox();
        registrationScreen.clickBtnYalla();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage
                ("{username=must be a well-formed email address}"));

    }
}
