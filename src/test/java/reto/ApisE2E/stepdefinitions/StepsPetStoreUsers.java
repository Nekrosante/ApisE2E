package reto.ApisE2E.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import reto.ApisE2E.interactions.CheckHealth;
import reto.ApisE2E.tasks.*;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.equalTo;

public class StepsPetStoreUsers {
    private EnvironmentVariables environmentVariables;
    private Actor actor;

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        actor = Actor.named("Regular user").whoCan(CallAnApi.at(baseURI));
    }
    @Given("that the services of the pet store are available")
    public void thatTheServicesOfThePetStoreAreAvailable() {
        actor.attemptsTo(
                        CheckHealth.petStore()
                );
    }

    @When("submit the data of a valid user to the Create User method")
    public void submitTheDataOfAValidUserToTheMethod() {
        actor.attemptsTo(
                GetCreateUser.with()
                        .username("Arcia")
                        .password("123456"),
                PostDataUser.with()
                        .body("{\"id\": 552211,\"username\": \"Nekros\",\"firstName\": \"Luis\",\"lastName\": \"Arcia\",\"email\": \"larcia@arcia.com\",\"password\": \"123789\",\"phone\": \"32165478\",\"userStatus\": 0}")
        );
    }
    @Then("validate the response from the user creation service")
    public void validateTheResponseFromTheUserCreationService() {
        actor.should(
                seeThatResponse("Status code 200 in the response from the user creation service",
                        response -> response.statusCode(200)
                )
        );
    }
    @When("execute the search method with a valid username")
    public void executeTheSearchMethodWithAValidUsername() {
        actor.attemptsTo(
                GetFindUser.with()
                        .username("Nekros")
        );
    }
    @Then("validate that the search is successful")
    public void validateThatTheSearchIsSuccessful() {
        actor.should(
                seeThatResponse("The service response contains information consistent with the searched user",
                        response ->response.statusCode(200).body("id",equalTo(552211),"firstName",equalTo("Luis"))
                ));
    }
    @When("execute the user update method with valid data")
    public void executeTheUserUpdateMethodWithValidData() {
        actor.attemptsTo(
                PutUpdateUser.with()
                        .body("{\"id\": 552211,\"username\": \"Nekros\",\"firstName\": \"Miguel\",\"lastName\": \"Arcia\",\"email\": \"miguel@arcia.com\",\"password\": \"123789\",\"phone\": \"32165478\",\"userStatus\": 0}")
        );
    }
    @Then("validate the user update using the search method")
    public void validateTheUserUpdateUsingTheSearchMethod() {
        actor.attemptsTo(
                GetFindUser.with()
                        .username("Nekros")
        );
        actor.should(
                seeThatResponse("The editing of the name and email was successful",
                        response -> response.statusCode(200)
                                .body("id",equalTo(552211),"firstName",equalTo("Miguel"),"email",equalTo("miguel@arcia.com"))
                )
        );
    }

    @When("create a new user using the corresponding method")
    public void createANewUserUsingTheCorrespondingMethod() {
        actor.attemptsTo(
                PostDataUser.with()
                        .body("{\"id\": 852561,\"username\": \"Batman\",\"firstName\": \"Bruce\",\"lastName\": \"Wayne\",\"email\": \"huerfano@gotica.com\",\"password\": \"123789\",\"phone\": \"32165478\",\"userStatus\": 0}")
        );
    }

    @And("delete the user from the pet store")
    public void deleteTheUserFromThePetStore() {
        actor.attemptsTo(
                DeleteUser.with()
                        .username("Batman")
        );

    }

    @Then("verify with the search method that the user is deleted")
    public void verifyWithTheSearchMethodThatTheUserIsDeleted() {
        actor.attemptsTo(
                GetFindUser.with()
                        .username("Batman")
        );
        actor.should(
                seeThatResponse("The search method did not find the deleted user",
                        response -> response.statusCode(404)
                                .body("message",equalTo("User not found"))
                )
        );
    }
}
