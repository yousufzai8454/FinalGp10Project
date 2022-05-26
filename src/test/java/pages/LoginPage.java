package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    @FindBy(id="txtUsername")
    public WebElement usernameBox;

   @FindBy(name="txtPassword")
    public WebElement passwordBox;

   @FindBy(id="btnLogin")
    public WebElement loginBtn;

   @FindBy(id="spanMessage")
   public WebElement errorMessage;

   public LoginPage() {
       PageFactory.initElements(driver, this);
   }





}
