package bluebomb.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PruebasWebSockets {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public void greeting(String message) throws Exception {
        System.out.println("Hello ha llegadddoo");
        Thread.sleep(1000); // simulated delay
        simpMessagingTemplate.convertAndSend("/topic/greetings", message);
    }
}
