package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashBoardPage dashBoardPage = new DashBoardPage();
        click(dashBoardPage.pimOPtion);
    }

    @When("user clicks on Add Employee Button")
    public void user_clicks_on_add_employee_button() {
        DashBoardPage dashBoardPage = new DashBoardPage();
        click(dashBoardPage.addEmployeeButton);
    }
        // 1. Hardcoding
    @When("user pass firstname middlename and lastname")
    public void user_pass_firstname_middlename_and_lastname() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, "Juan");
        sendText(addEmployeePage.middleName, "C");
        sendText(addEmployeePage.lastName, "Gomez");
    }
        // 2. passing
    @When("user pass firstname {string} middlename {string} and lastname {string}")
    public void user_pass_firstname_middlename_and_lastname(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }
        // 3. Via feature file
    @When("user enters {string} {string} and {string} in the application")
    public void user_enters_and_in_the_application(String firstName, String middleName, String lastName) {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        click(addEmployeePage.saveBtn);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee saved");
    }

    @When("add multiple employees and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable userNamesFromTable) {

        List<Map<String, String>> userNamesList = userNamesFromTable.asMaps();

        for (Map<String, String> userName : userNamesList) {
            AddEmployeePage addEmployeePage=new AddEmployeePage();
            sendText(addEmployeePage.firstName,userName.get("FirstName"));
            sendText(addEmployeePage.middleName, userName.get("MiddleName"));
            sendText(addEmployeePage.lastName, userName.get("LastName"));
            click(addEmployeePage.saveBtn);
            DashBoardPage dashBoardPage=new DashBoardPage();
            click(dashBoardPage.addEmployeeButton);

            //assertion as hw
        }
    }
    @When("user adds multiple employees from excel file from {string} sheet and verify they are added")
    public void user_adds_multiple_employees_from_excel_file_from_sheet_and_verify_they_are_added(String NamesToAdd){
        List<Map<String,String>>newEmployees= ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, NamesToAdd);
        DashBoardPage dashBoardPage=new DashBoardPage();
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        Iterator<Map<String,String>>iterator=newEmployees.iterator();
        while (iterator.hasNext()){
            Map<String,String>listData=iterator.next();
               click(dashBoardPage.addEmployeeButton);
               sendText(addEmployeePage.firstName,listData.get("FirstName"));
               sendText(addEmployeePage.middleName, listData.get("MiddleName"));
               sendText(addEmployeePage.lastName, listData.get("LastName"));
               click(addEmployeePage.saveBtn);
        }

    }

}
