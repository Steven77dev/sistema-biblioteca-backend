package com.growby.biblioteca.controller;

import com.growby.biblioteca.model.entity.Prestamo;
import com.growby.biblioteca.service.PrestamoService;
import com.growby.biblioteca.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamos")
@Tag(name = "Api-Prestamos", description = "Gestión de préstamos de libros")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @Autowired
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @Operation(
            summary = "Crear préstamo",
            description = "Permite registrar un nuevo préstamo de un libro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response crearPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.crearPrestamo(prestamo);
    }

    @Operation(
            summary = "Listar préstamos",
            description = "Obtiene un listado de todos los préstamos registrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response listarPrestamos() {
        return prestamoService.listarPrestamos();
    }

    @Operation(
            summary = "Listar préstamos de un libro",
            description = "Permite obtener los préstamos realizados para un libro específico"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value = "/libro/{libroId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response listarPrestamosDeLibro(@PathVariable Long libroId) {
        return prestamoService.listarPrestamosDeLibro(libroId);
    }

    @Operation(
            summary = "Finalizar préstamo",
            description = "Permite marcar un préstamo como finalizado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping(value = "/finalizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response finalizarPrestamo(@PathVariable Long id) {
        return prestamoService.finalizarPrestamo(id);
    }
}
