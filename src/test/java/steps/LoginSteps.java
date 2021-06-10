package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {
  // @Given("admin navigates to hrms")

    @When("user provides valid admin username and password")
    public void user_provides_valid_admin_username_and_password() {
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordbox, ConfigReader.getPropertyValue("password"));

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        LoginPage loginPage=new LoginPage();
        click(loginPage.loginBtn);
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        DashBoardPage dashBoardPage=new DashBoardPage();
        Assert.assertTrue(dashBoardPage.welcomemessage.isDisplayed());
    }

    @When("user enters valid username {string} and invalid password {string}")
    public void user_enters_valid_username_and_invalid_password(String string, String string2) {
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox, string);
        sendText(loginPage.passwordbox, string2);
    }

    @Then("user see invalid credentials text on login page")
    public void user_see_invalid_credentials_text_on_login_page() {
        LoginPage loginPage=new LoginPage();
        Assert.assertEquals("Error message MissMatch", "Invalid password", loginPage.errormessage.getText());
    }

    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox, username);
        sendText(loginPage.passwordbox, password);
    }
    @When("{string} is successfully logged in")
    public void is_successfully_logged_in(String firstName) {
       DashBoardPage dashBoardPage=new DashBoardPage();
       Assert.assertEquals("Login message verification error", ("Welcome "+firstName),dashBoardPage.welcomemessage.getText());
    }

    @When("user enters valid username and invalid password and verify the error")
    public void user_enters_valid_username_and_invalid_password_and_verify_the_error(DataTable dataTable) {
        List<Map<String,String>> dataTableInfo= dataTable.asMaps();
        SoftAssert softAssert=new SoftAssert();
        for (Map<String,String>tableInfo:dataTableInfo){
            LoginPage loginPage=new LoginPage();
            sendText(loginPage.usernamebox, tableInfo.get("userName"));
            sendText(loginPage.passwordbox, tableInfo.get("password"));
            click(loginPage.loginBtn);
            softAssert.assertEquals(tableInfo.get("error"), "Invalid credentials", "**** Error Message MissMatch -> ");
            loginPage.usernamebox.clear();
            loginPage.passwordbox.clear();

        }
        softAssert.assertAll();
    }
}
