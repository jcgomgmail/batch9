package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginPage extends CommonMethods{

    @FindBy(id = "txtUsername")
    public WebElement usernamebox;

    @FindBy(id = "txtPassword")
    public WebElement passwordbox;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;

    @FindBy(id="spanMessage")
    public WebElement errormessage;

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password){
        sendText(usernamebox, username);
        sendText(passwordbox, password);
        click(loginBtn);
    }

}
