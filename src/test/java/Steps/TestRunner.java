package Steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
        glue={"Steps"},
        monochrome = true,

        plugin = { "pretty", "html:target/reports"}
)
public class TestRunner {
}

