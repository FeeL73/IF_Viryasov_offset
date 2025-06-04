
package ifellow.runner;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ifellow.hooks, " +
        "ifellow.steps.reqRes, " +
        "ifellow.steps.rickAndMorty")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, summary, html:target/cucumber-report.html, " +
                "json:target/cucumber.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@ifellow")
public class CucumberRunner {}