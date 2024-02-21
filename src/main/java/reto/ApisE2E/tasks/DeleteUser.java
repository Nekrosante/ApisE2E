package reto.ApisE2E.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUser implements Task {
    private String username;
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/user/{username}").with(request -> request.pathParam("username", username))
        );
        actor.should(
                seeThatResponse("Successful deletion of the new user",
                        response -> response.statusCode(200)
                                .body("message",equalTo(username))
                )
        );
    }
    public static DeleteUser with() {return Tasks.instrumented(DeleteUser.class);}
    public DeleteUser username (String username) {
        this.username = username;
        return this;
    }
}
