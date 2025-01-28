package com.growby.biblioteca.service;

import com.growby.biblioteca.model.entity.Autor;
import com.growby.biblioteca.repository.AutorRepository;
import com.growby.biblioteca.utils.Constante;
import com.growby.biblioteca.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Response crearAutor(Autor autor) {
        if (StringUtils.isEmpty(autor.getNombre())) {
            return Response.error("El nombre del autor es obligatorio.");
        }

        Autor autorGuardado = autorRepository.save(autor);

        if (autorGuardado.getId() != null) {
            return Response.exito(Constante.MENSAJE_EXITO, autorGuardado.getId());
        } else {
            return Response.error(Constante.MENSAJE_ERROR);
        }
    }

    public Response listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            return Response.noHayResultados(null);
        }
        return Response.exito(Constante.MENSAJE_EXITO, autores);
    }

    public Response obtenerAutor(Long id) {
        return autorRepository.findById(id)
                .map(autor -> Response.exito(Constante.MENSAJE_EXITO, autor))
                .orElse(Response.error(Constante.MENSAJE_AUTOR_NO_ENCONTRADO));
    }

    public Response actualizarAutor(Long id, Autor autorActualizado) {
        return autorRepository.findById(id)
                .map(autor -> {
                    autor.setNombre(autorActualizado.getNombre());
                    autor.setFechaNacimiento(autorActualizado.getFechaNacimiento());
                    autor.setNacionalidad(autorActualizado.getNacionalidad());
                    autorRepository.save(autor);
                    return Response.exito(Constante.MENSAJE_EXITO, null);
                })
                .orElse(Response.error(Constante.MENSAJE_AUTOR_NO_ENCONTRADO));
    }

    public Response eliminarAutor(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return Response.exito(Constante.MENSAJE_EXITO, null);
        }
        return Response.error(Constante.MENSAJE_AUTOR_NO_ENCONTRADO);
    }
}