package com.globalr.DemoUnitTests.Clientes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.globalr.DemoUnitTests.Cliente.Cliente;
import com.globalr.DemoUnitTests.Cliente.ClienteRepository;
import com.globalr.DemoUnitTests.Cliente.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClientesServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Preparacion de datos
        Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(1);
        Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(2);

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Ejecucion
        List<Cliente> result = clienteService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdCliente());
        assertEquals(2, result.get(1).getIdCliente());
    }

    @Test
    public void testFindById() {
        // Preparacion de datos
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        // Ejecucion
        Optional<Cliente> result = clienteService.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdCliente());
    }

    @Test
    public void testFindById_NotFound() {
        // Preparacion de datos
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        // Ejecucion
        Optional<Cliente> result = clienteService.findById(1);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        // Preparacion de datos
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Ejecucion
        Cliente result = clienteService.save(cliente);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdCliente());
    }

    @Test
    public void testUpdate() {
        // Preparacion de datos
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setStatus("Eliminado");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Ejecucion
        Cliente result = clienteService.update(cliente);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdCliente());
        assertEquals("Eliminado", result.getStatus());
    }
}

