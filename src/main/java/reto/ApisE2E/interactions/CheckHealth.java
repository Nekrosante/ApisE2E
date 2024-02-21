package reto.ApisE2E.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CheckHealth implements Interaction {

    public static CheckHealth petStore() {return Tasks.instrumented(CheckHealth.class);}

    @Override
    @Step("{0} check the availability of pet store services")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/swagger.json")
        );
        actor.should(
                seeThatResponse("all the services of the pet store are available",
                        response -> response.statusCode(200)
                )
        );
    }
}
