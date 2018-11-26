package bluebomb.urlshortener.controller;

import bluebomb.urlshortener.model.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Esta clase es solo de ejemplo del funcionamiento de websockets
 */

@Controller
public class PruebasWebSockets {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public void greeting(String message) throws Exception {
        Browser prueba = new Browser("pruee2333");
        System.out.println("Hello ha llegadddoo hello");
        //Thread.sleep(1000); // simulated delay
        simpMessagingTemplate.convertAndSend("/app/greetings", prueba);
    }


    @SubscribeMapping("/greetings")
    public Browser getBrowsers() {
        System.out.println("Hello ha llegadddoo susbscripcion");
        Browser prueba = new Browser("pruee");
        return prueba;
    }
}
