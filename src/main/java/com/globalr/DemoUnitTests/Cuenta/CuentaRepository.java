package com.globalr.DemoUnitTests.Cuenta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

   Optional<Cuenta> findCuentaByNroCuenta(String nroCuenta);
}
