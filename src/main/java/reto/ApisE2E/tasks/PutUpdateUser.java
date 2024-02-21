package reto.ApisE2E.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class PutUpdateUser implements Task {
    private String body;
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/user/{username}")
                        .with(request -> request.pathParam("username", "Nekros")
                                .body(body)
                        ));
    }
    public static PutUpdateUser with() {return Tasks.instrumented(PutUpdateUser.class);}
    public PutUpdateUser body(String body) {
        this.body = body;
        return this;
    }
}
