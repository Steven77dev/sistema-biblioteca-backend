CREATE TABLE Autor (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    nombre VARCHAR(255),
    nacionalidad VARCHAR(255),
    fecha_nacimiento DATE
);

CREATE TABLE Libro (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    titulo VARCHAR(255),
    id_autor BIGINT,
    isbn VARCHAR(40),
    fecha_publicacion DATE,
    estado VARCHAR(50),
    CONSTRAINT LIBRO_FK FOREIGN KEY (id_autor) REFERENCES Autor(id)
);

CREATE TABLE Prestamo (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    id_libro BIGINT,
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    estado VARCHAR(50),
    CONSTRAINT PRESTAMO_FK FOREIGN KEY (id_libro) REFERENCES Libro(id)
);


INSERT INTO autor (id, nombre, nacionalidad, fecha_nacimiento) VALUES
(1, 'Gabriel García Márquez', 'Colombiana', '1927-03-06'),
(2, 'Jorge Luis Borges', 'Argentina', '1899-08-24');

INSERT INTO libro (id, titulo, id_autor, isbn, fecha_publicacion, estado) VALUES
(1, 'El Quijote', 2, '978-1234567890', '1605-01-16', 'Disponible'),
(2, 'Cien Años de Soledad', 1, '978-9876543210', '1967-05-30', 'Disponible');

--INSERT INTO prestamo (id, id_libro, fecha_prestamo, fecha_devolucion, estado) VALUES
--(1, 1, '2024-01-20', '2024-01-30', 'ACTIVO'),
--(2, 2, '2024-01-15', '2024-01-25', 'FINALIZADO');