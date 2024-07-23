package com.globalr.DemoUnitTests.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {
    public List<Cuenta> getAllCuentas();

    public Optional<Cuenta> getCuentaById(Integer id);

    public Cuenta createCuenta(Cuenta cuenta);

    public Cuenta updateCuenta(Integer id, Cuenta cuentaDetails);

}
