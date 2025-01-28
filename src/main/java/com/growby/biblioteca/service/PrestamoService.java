package com.growby.biblioteca.service;

import com.growby.biblioteca.model.entity.Libro;
import com.growby.biblioteca.model.entity.Prestamo;
import com.growby.biblioteca.repository.LibroRepository;
import com.growby.biblioteca.repository.PrestamoRepository;
import com.growby.biblioteca.utils.Constante;
import com.growby.biblioteca.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final LibroService libroService;

    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository,
                           LibroRepository libroRepository,
                           LibroService libroService) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.libroService = libroService;
    }

    public Response crearPrestamo(Prestamo prestamo) {
        if (!libroService.verificarDisponibilidad(prestamo.getIdLibro())) {
            return Response.error("El libro no está disponible para préstamo.");
        }

        libroNoDisponible(prestamo.getIdLibro());
        Prestamo registro = generarPrestamo(prestamo);
        if( registro.getId()>0)
            return Response.exito(Constante.MENSAJE_EXITO, registro.getId());
        else
            return Response.error();

    }

    private void libroNoDisponible(long idLibro) {
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setEstado("No disponible");
        libroRepository.save(libro);
    }

    private Prestamo generarPrestamo(Prestamo prestamo) {
        prestamo.setEstado("Activo");
        return prestamoRepository.save(prestamo);
    }

    public Response listarPrestamos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        if (prestamos.isEmpty()) {
            return Response.noHayResultados(null);
        }
        return Response.exito(Constante.MENSAJE_EXITO, prestamos);
    }

    public Response listarPrestamosDeLibro(Long libroId) {
        List<Prestamo> prestamos = prestamoRepository.findByIdLibro(libroId);
        if (prestamos.isEmpty()) {
            return Response.noHayResultados(null);
        }
        return Response.exito(Constante.MENSAJE_EXITO, prestamos);
    }

    public Response finalizarPrestamo(Long id) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(!prestamo.isPresent())
            Response.error("Préstamo no encontrado");

        prestamo.get().setEstado("Finalizado");
        prestamo.get().setFechaDevolucion(new Date());
        libroDisponible(prestamo.get().getIdLibro());
        prestamoRepository.save(prestamo.get());

        return Response.exito(Constante.MENSAJE_EXITO, prestamo);
    }

    private void libroDisponible(long idLibro) {
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setEstado("Disponible");
        libroRepository.save(libro);
    }
}