package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
		glue = "steps",
		tags = "@LoginCheck",
		plugin = { "pretty", "html:target/cucumber-reports.html" })
public class LoginTestRunner extends AbstractTestNGCucumberTests {
}
