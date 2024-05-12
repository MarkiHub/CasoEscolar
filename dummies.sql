-- Datos para la tabla Alumnos
INSERT INTO Alumnos (nombreCompleto, idPadre) VALUES
('Juan Perez', 1),
('Maria Garcia', 2),
('Luis Ramirez', NULL),
('Ana Martinez', NULL),
('Pedro Lopez', NULL);

-- Datos para la tabla Profesores
INSERT INTO Profesores (nombreCompleto) VALUES
('Carlos Rodriguez'),
('Laura Fernandez'),
('Roberto Sanchez'),
('Sofia Gonzalez'),
('Diego Martinez');

-- Datos para la tabla Materias
INSERT INTO Materias (nombre) VALUES
('Matemáticas'),
('Historia'),
('Literatura'),
('Ciencias'),
('Inglés');

-- Datos para la tabla Cursos
INSERT INTO Cursos (idProfesor, idMateria) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Datos para la tabla AlumnosInscritos
INSERT INTO AlumnosInscritos (idAlumno, idCurso) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Datos para la tabla Asignaciones
INSERT INTO Asignaciones (nombre, idCurso) VALUES
('Tarea 1', 1),
('Ensayo', 2),
('Proyecto', 3),
('Prueba', 4),
('Presentación', 5);
