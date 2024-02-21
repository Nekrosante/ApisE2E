package reto.ApisE2E.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetCreateUser implements Task {
    private String username;
    private String password;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/user/login?username={username}&password={password}").with(request -> request.pathParam("username", username).pathParam("password",password))
        );
        actor.should(
                seeThatResponse("the login was successful with a valid username and password",
                        response -> response.statusCode(200)
                )
        );
    }
    public static GetCreateUser with() {return Tasks.instrumented(GetCreateUser.class);}
    public GetCreateUser username (String username) {
        this.username = username;
        return this;
    }
    public GetCreateUser password (String password) {
        this.password = password;
        return this;
    }
}
