package io.ikka.ispp.controller;

import io.ikka.ispp.config.IsppClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axetta.ecafe.processor.web.partner.integra.soap.GetSummaryByGuardMobile;
import ru.axetta.ecafe.processor.web.partner.integra.soap.GetSummaryByGuardMobileResponse;
import ru.axetta.ecafe.processor.web.partner.integra.soap.ObjectFactory;

@RequiredArgsConstructor
@RestController
public class MyController {
    private final IsppClient isppClient;

    @GetMapping(value = "/test")
    public ResponseEntity<?> get(){
        ObjectFactory objectFactory = new ObjectFactory();
        GetSummaryByGuardMobile getSummaryByGuardMobile = new GetSummaryByGuardMobile();
        getSummaryByGuardMobile.setGuardMobile("79854907995");
        GetSummaryByGuardMobileResponse country = isppClient.getSummaryByGuardMobile(getSummaryByGuardMobile);


        return ResponseEntity.ok("OK");
    }
}
