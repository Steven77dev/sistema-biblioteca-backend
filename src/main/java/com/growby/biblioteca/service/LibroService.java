package com.growby.biblioteca.service;

import com.growby.biblioteca.model.entity.Autor;
import com.growby.biblioteca.model.entity.Libro;
import com.growby.biblioteca.repository.AutorRepository;
import com.growby.biblioteca.repository.LibroRepository;
import com.growby.biblioteca.utils.Response;
import com.growby.biblioteca.utils.Constante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    public LibroService(LibroRepository libroRepository,
                        AutorRepository autorRepository){
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }


    public Response crearLibro(Libro libro) {
        if(libro.getIdAutor()==null)
            return Response.error("No ha especificado autor");

        Libro registro = libroRepository.save(libro);

        if( registro.getId()>0)
            return Response.exito(Constante.MENSAJE_EXITO, registro.getId());
        else
            return Response.error();
    }

    public Response listarLibros() {
        List<Libro> listado = libroRepository.findAll();
        if (listado.isEmpty()) {
            return Response.noHayResultados(null);
        }
        return Response.exito(Constante.MENSAJE_EXITO, listado);

    }

    public Response listarLibrosPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Libro> librosPage = libroRepository.findAll(pageable);

        Map<String, Object> librosPaginado = new HashMap<>();
        librosPaginado.put("libros", librosPage.getContent());
        librosPaginado.put("currentPage", librosPage.getNumber());
        librosPaginado.put("totalItems", librosPage.getTotalElements());
        librosPaginado.put("totalPages", librosPage.getTotalPages());

        return Response.exito("Listado obtenido correctamente", librosPaginado);
    }

    public Response obtenerLibro(Long id) {
        return libroRepository.findById(id)
                .map(libro -> Response.exito(Constante.MENSAJE_EXITO, libro))
                .orElse(Response.error(Constante.MENSAJE_LIBRO_NO_ENCONTRADO));
    }

    public Response actualizarLibro(Long id, Libro libroActualizado) {
        Optional<Autor> autor = autorRepository.findById(libroActualizado.getIdAutor());
        if(!autor.isPresent())
            return Response.error("No existe el autor");

        return libroRepository.findById(id)
                .map(libro -> {
                    libro.setTitulo(libroActualizado.getTitulo());
                    libro.setIdAutor(libroActualizado.getIdAutor());
                    libro.setIsbn(libroActualizado.getIsbn());
                    libro.setFechaPublicacion(libroActualizado.getFechaPublicacion());
                    libro.setEstado(libroActualizado.getEstado());
                    libroRepository.save(libro);
                    return Response.exito(Constante.MENSAJE_EXITO, null);
                })
                .orElse(Response.error(Constante.MENSAJE_LIBRO_NO_ENCONTRADO));
    }

    public Response eliminarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return Response.exito(Constante.MENSAJE_EXITO, null);
        }
        return Response.error(Constante.MENSAJE_LIBRO_NO_ENCONTRADO);
    }

    public boolean verificarDisponibilidad(Long id) {
        return libroRepository.findById(id)
                .map(libro -> "Disponible".equals(libro.getEstado()))
                .orElse(false);
    }

    public Response disponibilidadLibro(Long id){
        Response existeLibro = obtenerLibro(id);
        if(existeLibro.getCodigo()==200){
            boolean disponible = verificarDisponibilidad(id);
            return Response.exito(Constante.MENSAJE_EXITO, disponible);
        }
        return existeLibro;

    }
}