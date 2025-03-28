package runner;



import org.junit.runner.RunWith;  // Import JUnit RunWith
import io.cucumber.junit.Cucumber;  // Import Cucumber
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepdefinations"},
        monochrome = true,
        tags = "@Auth",      
        plugin = {
                "pretty",
                "html:reports/cucumber-html-report",
                "json:reports/cucumber-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class CucumberTestRunner {
}
