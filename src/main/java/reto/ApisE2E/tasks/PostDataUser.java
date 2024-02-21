package reto.ApisE2E.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class PostDataUser implements Task {
    private String body;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/user")
                .with(request -> request.header("Content-Type", "application/json")
                        .body(body)
                )
        );
        actor.should(
                seeThatResponse("the login was successful with a valid username and password",
                        response -> response.statusCode(200)
                )
        );
    }
    public static PostDataUser with() {return Tasks.instrumented(PostDataUser.class);}
    public PostDataUser body(String body) {
        this.body = body;
        return this;
    }
}
