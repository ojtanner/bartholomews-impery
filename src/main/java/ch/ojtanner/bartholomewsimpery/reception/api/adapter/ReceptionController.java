package ch.ojtanner.bartholomewsimpery.reception.api.adapter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reception")
public class ReceptionController {

    @PostMapping("/order")
    public void createOrder() {

    }

}
