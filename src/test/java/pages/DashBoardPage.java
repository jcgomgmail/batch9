package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class DashBoardPage extends CommonMethods {

    @FindBy(id = "welcome")
    public WebElement welcomemessage;

    @FindBy(id="menu_pim_viewPimModule")
    public WebElement pimOPtion;

    @FindBy(id="menu_pim_addEmployee")
    public WebElement addEmployeeButton;

    @FindBy(id="menu_pim_viewEmployeeList")
    public WebElement employeeListOption;

    @FindBy(xpath = "//*[@class= 'menu']/ul/li")
    public List<WebElement> dashboardTabMenu;



    public DashBoardPage(){
        PageFactory.initElements(driver, this);
    }

}
