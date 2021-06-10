package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.DashBoardPage;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {
    @Then("verify the following tabs on dashboard")
    public void verify_the_following_tabs_on_dashboard(DataTable dataTable) {
        DashBoardPage dashBoardPage=new DashBoardPage();
        List<String> expectedText=dataTable.asList();
        List<String>actualTabElements=new ArrayList<>();
        dashBoardPage.dashboardTabMenu.forEach(x->actualTabElements.add(x.getText()));
        System.out.println(actualTabElements);
        System.out.println(expectedText);
        Assert.assertTrue("**** Values do not match -> ",actualTabElements.equals(expectedText));

        System.out.println("Test case passed!!");

    }
}
