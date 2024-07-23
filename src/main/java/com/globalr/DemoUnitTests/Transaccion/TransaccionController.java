package com.globalr.DemoUnitTests.Transaccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {
    @Autowired
    TransaccionService transaccionService;

    @PostMapping("/deposito/{idaccount}")
    public TransaccionResposeDTO realizarDeposito(@RequestBody TransaccionRequestDTO depositoRequestDTO) {

        TransaccionResposeDTO deposito = transaccionService.crearDeposito(depositoRequestDTO);

        return deposito;
    }

    @PostMapping("/retiro/{idaccount}")
    public TransaccionResposeDTO realizarRetiro(@RequestBody TransaccionRequestDTO retiroRequestDTO) {

        TransaccionResposeDTO retiro = transaccionService.crearDeposito(retiroRequestDTO);

        return retiro;
    }

}
