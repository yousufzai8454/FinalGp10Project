package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashBoardSteps extends CommonMethods {
    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(DataTable dataTable) {
        //This data come from feature file
        List<String> expectedTabs = dataTable.asList();

        List<String> actualTabs = new ArrayList<>();
        for (WebElement ele:dash.dashboardTabs
        ){
            actualTabs.add(ele.getText());

        }
        System.out.print(actualTabs);//coming from my execution
        System.out.print(expectedTabs);//coming from my feature file

        //Assert.assertEquals(actualTabs,expectedTabs);

        //If assertion is passed it will not give yoy any information and will execute our code
        //
        Assert.assertTrue(expectedTabs.equals(actualTabs));
    }
}