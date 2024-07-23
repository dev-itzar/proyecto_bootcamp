package com.globalr.DemoUnitTests.Cuenta;

import com.globalr.DemoUnitTests.Cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService{
    private final CuentaRepository cuentaRepository;
    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }
    @Override
    public Optional<Cuenta> getCuentaById(Integer id) {
        return cuentaRepository.findById(String.valueOf(id));
    }
    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
    @Override
    public Cuenta updateCuenta(Integer id, Cuenta cuentaDetails) {
        return cuentaRepository.findById(String.valueOf(id))
                .map(cuenta -> {
                    cuenta.setNroCuenta(cuentaDetails.getNroCuenta());
                    cuenta.setSaldoApertura(cuentaDetails.getSaldoApertura());
                    cuenta.setSaldo(cuentaDetails.getSaldo());
                    cuenta.setStatus(cuentaDetails.getStatus());
                    cuenta.setCliente(cuentaDetails.getCliente());
                    return cuentaRepository.save(cuenta);
                })
                .orElseGet(() -> {
                    cuentaDetails.setIdCuenta(id);
                    return cuentaRepository.save(cuentaDetails);
                });
    }

}
