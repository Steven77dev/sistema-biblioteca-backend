package com.growby.biblioteca.controller;

import com.growby.biblioteca.model.entity.Libro;
import com.growby.biblioteca.service.LibroService;
import com.growby.biblioteca.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Api-Libros", description = "Servicios para libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @Operation(
            summary = "Crear libro",
            description = "Endpoint para la creación de un nuevo libro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response crearLibro(@RequestBody Libro libro) {
        return libroService.crearLibro(libro);
    }

    @Operation(
            summary = "Listar libros con paginación",
            description = "Endpoint para obtener la lista de todos los libros mediante paginación"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value = "/paginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response listarLibrosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return libroService.listarLibrosPaginado(page, size);
    }

    @Operation(
            summary = "Listar libros",
            description = "Endpoint para obtener la lista de todos los libros"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response listarLibros() {
        return libroService.listarLibros();
    }

    @Operation(
            summary = "Obtener libro",
            description = "Endpoint para obtener los detalles de un libro por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"), 
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response obtenerLibro(@PathVariable Long id) {
        return libroService.obtenerLibro(id);
    }

    @Operation(
            summary = "Actualizar libro",
            description = "Endpoint para actualizar los detalles de un libro por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"), 
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizarLibro(id, libro);
    }

    @Operation(
            summary = "Eliminar libro",
            description = "Endpoint para eliminar un libro por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"), 
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response eliminarLibro(@PathVariable Long id) {
        return libroService.eliminarLibro(id);
    }

    @Operation(
            summary = "Verificar disponibilidad del libro",
            description = "Endpoint para verificar la disponibilidad de un libro por id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"), 
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping(value = "/disponibilidadLibro/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response disponibilidadLibro(@PathVariable Long id) {
        return libroService.disponibilidadLibro(id);
    }
}