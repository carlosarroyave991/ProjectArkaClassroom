package com.arka.classroom.arka.project.controllers;

import com.arka.classroom.arka.project.Aplication.Services.ClienteService;
import com.arka.classroom.arka.project.Aplication.models.dto.CreateClienteDto;
import com.arka.classroom.arka.project.Domain.Entities.Cliente;
import com.arka.classroom.arka.project.Domain.Entities.enums.TipoUsuario;
import com.arka.classroom.arka.project.infraestructure.controllers.ClienteController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Prueba para obtener todos los clientes")
    public void testGetAllClientes() throws Exception {
        List<CreateClienteDto> clientes = Arrays.asList(new CreateClienteDto());
        Mockito.when(clienteService.getAll()).thenReturn(clientes);

        ObjectMapper objectMapper = new ObjectMapper(); // Jackson para convertir a JSON
        String clientesJson = objectMapper.writeValueAsString(clientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(clientesJson));
    }

    @Test
    @DisplayName("Prueba oara obtener todos los clientes por nombre")
    public void testFindByName() throws Exception {
        String name = "John";
        List<CreateClienteDto> clientes = Arrays.asList(new CreateClienteDto());
        Mockito.when(clienteService.findByName(name)).thenReturn(clientes);

        String clientesJson = objectMapper.writeValueAsString(clientes);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/buscar").param("name", name))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(clientesJson));
    }

    /*@Test
    @DisplayName("Prueba para guardar un cliente")
    public void testSave() throws Exception {
        CreateClienteDto clienteDto = new CreateClienteDto(1L,"juan perez","juan@gmail.com","123456789","2313131313");
        Mockito.when(clienteService.save(Mockito.any(CreateClienteDto.class))).thenReturn(clienteDto);

        String clientesJson = objectMapper.writeValueAsString(clienteDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientesJson))// Enviamos el JSON del cliente
                .andExpect(MockMvcResultMatchers.status().isCreated())// Validamos el estado HTTP 201
                .andExpect(MockMvcResultMatchers.content().json(clientesJson));//validamos que la respuesta sea igual al json enviado
    }*/

    /*@Test
    public void testUpdate() throws Exception {
        Long id = 1L;
        CreateClienteDto clienteDto = new CreateClienteDto(*//* datos *//*);
        Mockito.when(clienteService.update(id, clienteDto)).thenReturn(clienteDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/cliente/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(*//* JSON del clienteDto *//*))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(*//* JSON esperado *//*));
    }*/

    /*@Test
    public void testDelete() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Se eliminó correctamente"));

        Mockito.verify(clienteService, Mockito.times(1)).delete(id);
    }*/
}
