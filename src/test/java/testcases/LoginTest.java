package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {

    @Test(groups = "sanity")
    public void adminLogin(){

        //login to hrms application
        LoginPage loginpage = new LoginPage();
        sendText(loginpage.usernamebox, ConfigReader.getPropertyValue("username"));
        sendText(loginpage.passwordbox, ConfigReader.getPropertyValue("password"));
        click(loginpage.loginBtn);

        //validation
        //assertion

        DashBoardPage dashboard = new DashBoardPage();
        Assert.assertTrue(dashboard.welcomemessage.isDisplayed(), "welcome message is not displayed");
    }
    @Test(dataProvider = "invalidData", groups = "smoke")
    public void invalidLoginErrorMessageValidation(String username, String password, String message){
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernamebox, username);
        sendText(loginPage.passwordbox, password);
        click(loginPage.loginBtn);

        String actualError = loginPage.errormessage.getText();

        Assert.assertEquals(actualError, message, "Error message is not matched");

    }
    @DataProvider
    public Object[][] invalidData() {
        Object[][] data = {
                {"James", "123!", "Invalid credentials"},
                {"Admin1", "Syntax123!", "Invalid credentials"},
                {"James", "", "Password cannot be empty"},
                {"", "Syntax123!", "Username cannot be empty"}
        };
        return data;
    }
}
