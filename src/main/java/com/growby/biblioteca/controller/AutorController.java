package com.growby.biblioteca.controller;

import com.growby.biblioteca.model.entity.Autor;
import com.growby.biblioteca.service.AutorService;
import com.growby.biblioteca.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @Operation(
            summary = "Crear autor",
            description = "Endpoint para la creación de un autor de libro",

            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response crearAutor(@RequestBody Autor autor) {
        return autorService.crearAutor(autor);
    }

    @Operation(summary = "Listar autores", description = "Obtener la lista de todos los autores registrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Response listarAutores() {
        return autorService.listarAutores();
    }

    @Operation(summary = "Obtener autor por ID", description = "Devuelve los detalles de un autor según id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response obtenerAutor(@PathVariable Long id) {
        return autorService.obtenerAutor(id);
    }

    @Operation(summary = "Actualizar autor", description = "Actualiza los detalles de un autor existente según id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response actualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        return autorService.actualizarAutor(id, autor);
    }

    @Operation(summary = "Eliminar autor", description = "Elimina un autor de la base de datos según id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response eliminarAutor(@PathVariable Long id) {
        return autorService.eliminarAutor(id);
    }
}