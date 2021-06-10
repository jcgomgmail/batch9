package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = "steps",   //  glue provides the link between Features and Step Classes
        dryRun = false,  // to check if all steps are implemented - no real run
        monochrome = true, // provides organized output
        //"@smoke"
        tags = "@simpletag",// or @regression or @production or @smoke"
        //tags = "@regression and @smoke"
        //tags = "@regression or @smoke"
        plugin = {"pretty","html:target//cucumber.html"}

)

public class TestRunner{
}
