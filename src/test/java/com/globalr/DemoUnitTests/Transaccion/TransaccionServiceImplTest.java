package com.globalr.DemoUnitTests.Transaccion;

import com.globalr.DemoUnitTests.Cuenta.Cuenta;
import com.globalr.DemoUnitTests.Cuenta.CuentaDAO;
import com.globalr.DemoUnitTests.Cuenta.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransaccionServiceImplTest {

    @InjectMocks
    TransaccionServiceImpl service;

    @Mock
    CuentaRepository cuentaRepository;

    @Test
    public void crearDeposito_Exitoso ()
    {
        // Preparacion de los datos de prueba
        TransaccionRequestDTO request = new TransaccionRequestDTO();
        request.setIdCuenta(1);
        request.setMontoTransaccion(100.0);

        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(1);
        cuenta.setSaldo(200.0);

        when(cuentaRepository.findById(String.valueOf(1))).thenReturn(Optional.of(cuenta));

        // Ejecutar prueba
        TransaccionResposeDTO response = service.crearDeposito(request);

        // REsultados
        assertEquals(1, response.getIdCuenta());
        assertEquals(200.0, response.getSaldoAnterior());
        assertEquals(300.0, response.getSaldoNuevo());
    }

    @Test
    public void crearDeposito_Exception ()
    {
        // Preparacion de los datos de prueba
        TransaccionRequestDTO depositoRequestDTO = new TransaccionRequestDTO();
        depositoRequestDTO.setIdDeposito("12345");
        depositoRequestDTO.setIdCuenta(1);
        depositoRequestDTO.setNroCuenta("09876");
        depositoRequestDTO.setMontoTransaccion(30.0);

        Optional<Cuenta> cuentaMock = Optional.empty();

        // Ejecutar prueba
        when(cuentaRepository.findById(depositoRequestDTO.getNroCuenta())).thenReturn(cuentaMock);
        assertThrows(RuntimeException.class, () -> {
            service.crearDeposito(depositoRequestDTO);
        });

    }

    @Test
    public void testCrearRetiro_CuentaExistenteConSaldoSuficiente() {
        // Preparacion de datos
        TransaccionRequestDTO request = new TransaccionRequestDTO();
        request.setIdCuenta(1);
        request.setMontoTransaccion(100.0);

        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(1);
        cuenta.setSaldo(200.0);

        when(cuentaRepository.findById(String.valueOf(1))).thenReturn(Optional.of(cuenta));

        // ejecucion
        TransaccionResposeDTO response = service.crearRetiro(request);

        // REsultados
        assertEquals(1, response.getIdCuenta());
        assertEquals(200.0, response.getSaldoAnterior());
        assertEquals(100.0, response.getSaldoNuevo());
    }

    @Test
    public void testCrearRetiro_CuentaExistenteSinSaldoSuficiente() {
        // Preparacion de datos
        TransaccionRequestDTO request = new TransaccionRequestDTO();
        request.setIdCuenta(1);
        request.setMontoTransaccion(300.0);

        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(1);
        cuenta.setSaldo(200.0);

        when(cuentaRepository.findById(String.valueOf(1))).thenReturn(Optional.of(cuenta));

        // ejecucion
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.crearRetiro(request);
        });
        //REsultados
        assertEquals("Saldo no disponible para retiro", exception.getMessage());
    }

    @Test
    public void testCrearRetiro_CuentaNoExistente() {
        // Preparacion de datos
        TransaccionRequestDTO request = new TransaccionRequestDTO();
        request.setIdCuenta(1);
        request.setMontoTransaccion(100.0);

        when(cuentaRepository.findById(String.valueOf(1))).thenReturn(Optional.empty());

        // Ejecucion
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.crearRetiro(request);
        });
        //REsultados
        assertEquals("Cuenta no encontrada", exception.getMessage());
    }

}
