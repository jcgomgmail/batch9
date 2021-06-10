package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.DashBoardPage;
import utils.CommonMethods;
import utils.ConfigReader;


public class EmployeeSearchSteps extends CommonMethods {

    @Given("user navigates to hrms")
//    public void user_navigates_to_hrms() { -> replaced by Hooks
  //      setUp();
   // }

    @Given("user is logged in with valid admin credentials")
    public void user_is_logged_in_with_valid_admin_credentials() {
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordbox, ConfigReader.getPropertyValue("password"));
        click(loginPage.loginBtn);
    }


    @Given("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
        DashBoardPage dashBoardPage=new DashBoardPage();
        click(dashBoardPage.pimOPtion);
        click(dashBoardPage.employeeListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        EmployeeListPage employeeList=new EmployeeListPage();
        sendText(employeeList.idEmployee, "15518");
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        EmployeeListPage employeeList=new EmployeeListPage();
        sendText(employeeList.employeeName, "hi");
    }

    @When("click on search button")
    public void click_on_search_button() {
        EmployeeListPage employeeList = new EmployeeListPage();
        click(employeeList.searchButton);
    }

    @Then("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() {
        System.out.println("Employee info is displayed");
        System.out.println("It should close window");
       // tearDown();
    }


}
