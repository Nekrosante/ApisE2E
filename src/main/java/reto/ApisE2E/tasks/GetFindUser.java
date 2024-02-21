package reto.ApisE2E.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetFindUser implements Task {
    private String username;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/user/{username}").with(request -> request.pathParam("username", username))
        );
        actor.should(
                seeThatResponse("Status code 200 in the response from the user search method",
                        response -> response.statusCode(200)
                )
        );
    }
    public static GetFindUser with() {return Tasks.instrumented(GetFindUser.class);}
    public GetFindUser username (String username) {
        this.username = username;
        return this;
    }
}
