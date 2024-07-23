package com.globalr.DemoUnitTests.Transaccion;

import com.globalr.DemoUnitTests.Cuenta.Cuenta;
import com.globalr.DemoUnitTests.Cuenta.CuentaDAO;
import com.globalr.DemoUnitTests.Cuenta.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final CuentaRepository cuentaRepository;

    @Override
    public TransaccionResposeDTO crearDeposito(TransaccionRequestDTO transaccionRequestDTO) {
        TransaccionResposeDTO resultado = new TransaccionResposeDTO();
        Optional<Cuenta> cuenta = cuentaRepository.findById(String.valueOf(transaccionRequestDTO.getIdCuenta()));

        cuenta.ifPresentOrElse(c -> {
            double monto = transaccionRequestDTO.getMontoTransaccion();
            double nuevoSaldo = c.getSaldo() + monto;
            resultado.setIdCuenta(c.getIdCuenta());
            resultado.setNroCuenta(c.getNroCuenta());
            resultado.setSaldoAnterior(c.getSaldo());
            resultado.setSaldoNuevo(nuevoSaldo);
        }, () -> {
            throw new RuntimeException("Cuenta no encontrada");
        });

        return resultado;
    }

    @Override
    public TransaccionResposeDTO crearRetiro(TransaccionRequestDTO transaccionRequestDTO) {
        TransaccionResposeDTO resultado = new TransaccionResposeDTO();
        Optional<Cuenta> cuenta = cuentaRepository.findById(String.valueOf(transaccionRequestDTO.getIdCuenta()));

        cuenta.ifPresentOrElse(c -> {
            double monto = transaccionRequestDTO.getMontoTransaccion();
            if (cuenta.get().getSaldo() >= monto)
            {
                double nuevoSaldo = c.getSaldo() - monto;
                resultado.setIdCuenta(c.getIdCuenta());
                resultado.setSaldoAnterior(c.getSaldo());
                resultado.setSaldoNuevo(nuevoSaldo);
            }else
                throw new RuntimeException("Saldo no disponible para retiro");
        }, () -> {
            throw new RuntimeException("Cuenta no encontrada");
        });

        return resultado;
    }
}
