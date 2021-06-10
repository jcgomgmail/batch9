package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test(groups = "smoke")
    public void addEmployee() {

        LoginPage loginpage = new LoginPage();
        loginpage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        DashBoardPage dash = new DashBoardPage();
        click(dash.pimOPtion);
        click(dash.addEmployeeButton);

        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName, "test123");
        sendText(add.lastName, "test12345");
        click(add.saveBtn);
    }

    @Test(groups = "regression")
    public void addMultipleEmployees() throws InterruptedException {
        //login operation
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        //navigating to add employee page
        DashBoardPage dash = new DashBoardPage();
        EmployeeListPage emplist = new EmployeeListPage();
        AddEmployeePage addEmployeePagePage = new AddEmployeePage();
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, "EmployeeData");

        SoftAssert softAssert = new SoftAssert();

        Iterator<Map<String, String>> it = newEmployees.iterator();
        while (it.hasNext()) {
            click(dash.pimOPtion);
            click(dash.addEmployeeButton);
            Map<String, String> mapNewEmployee = it.next();
            sendText(addEmployeePagePage.firstName, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePagePage.middleName, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePagePage.lastName, mapNewEmployee.get("LastName"));
            String employeeIDValue = addEmployeePagePage.employeeId.getAttribute("value");
            sendText(addEmployeePagePage.photograph, mapNewEmployee.get("Photograph"));

            //select checkbox
            if (!addEmployeePagePage.createLoginCheckBox.isSelected()) {
                click(addEmployeePagePage.createLoginCheckBox);
            }

            //add login credentials for user
            sendText(addEmployeePagePage.usernamecreate, mapNewEmployee.get("Username"));
            sendText(addEmployeePagePage.userpassword, mapNewEmployee.get("Password"));
            sendText(addEmployeePagePage.repassword, mapNewEmployee.get("Password"));
            click(addEmployeePagePage.saveBtn);

            //navigate  to the employee list
            click(dash.pimOPtion);
            click(dash.employeeListOption);

            //enter employee id
            //waitForClickability(emplist.idEmployee);
            sendText(emplist.idEmployee, employeeIDValue);
            click(emplist.searchButton);

            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop");
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                Thread.sleep(10000);
                String expectedEmployeeDetails = employeeIDValue + " " + mapNewEmployee.get("FirstName") + " " + mapNewEmployee.get("MiddleName") + " " + mapNewEmployee.get("LastName");
                softAssert.assertEquals(rowText, expectedEmployeeDetails);
            }
        }
        softAssert.assertAll();
    }
}