package com.globalr.DemoUnitTests.Cuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Integer id) {
        return cuentaService.getCuentaById(id)
                .map(cuenta -> ResponseEntity.ok().body(cuenta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.createCuenta(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Integer id, @RequestBody Cuenta cuentaDetails) {
        return cuentaService.getCuentaById(id)
                .map(cuenta -> {
                    Cuenta updatedCuenta = cuentaService.updateCuenta(id, cuentaDetails);
                    return ResponseEntity.ok().body(updatedCuenta);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
