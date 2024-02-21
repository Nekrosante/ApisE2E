package reto.ApisE2E.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@PetStoreUsers",
        glue = "reto/ApisE2E/stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class RunnerPetStoreUsers {}
