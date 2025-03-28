package com.arka.classroom.arka.project.controllers;

import com.arka.classroom.arka.project.Aplication.Services.PedidoService;
import com.arka.classroom.arka.project.Aplication.models.dto.CreatePedidoDto;
import com.arka.classroom.arka.project.infraestructure.controllers.PedidoController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PedidoService pedidoService;

    @Test
    @DisplayName("Prueba para obtener todos los pedidos")
    void testGetAllPedidos() throws Exception {
        List<CreatePedidoDto> pedidos = List.of(new CreatePedidoDto(), new CreatePedidoDto());
        when(pedidoService.getAll()).thenReturn(pedidos);

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(pedidos.size()));
    }

    /*@Test
    @DisplayName("Prueba para obtener un pedido por referencia")
    void testGetPedidoByReference() throws Exception {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setId(1L);
        nuevoPedido.setAmountValue(BigDecimal.valueOf(23.32));
        nuevoPedido.setDate(new Date());
        nuevoPedido.setEstadoPedido("pendiente");
        nuevoPedido.setMetodoPago("pago contraentrega");
        nuevoPedido.setReference(1234567L);
        nuevoPedido.setSalePrice(BigDecimal.valueOf(56.09));

        when(pedidoService.findByReference(1L)).thenReturn(Optional.of(nuevoPedido));

        mockMvc.perform(get("/api/pedido/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }*/

    /*@Test
    @DisplayName("Prueba para crear un pedido")
    void testCreatePedido() throws Exception {
        PedidoRequest nuevoPedido = new PedidoRequest();
        nuevoPedido.setId(1L);
        nuevoPedido.setAmountValue(BigDecimal.valueOf(23.32));
        nuevoPedido.setDate(new Date());
        nuevoPedido.setEstadoPedido(EstadoPedido.valueOf("pendiente"));
        nuevoPedido.setMetodoPago("pago contraentrega");
        nuevoPedido.setReference(1234567L);
        nuevoPedido.setSalePrice(BigDecimal.valueOf(56.09));
        nuevoPedido.setCarritoId(1L);


        when(pedidoService.createPedido(any(CreatePedidoDto.class))).thenReturn(nuevoPedido);

        mockMvc.perform(post("/api/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoPedido)))
                        .andExpect(status().isCreated());
    }*/
}
