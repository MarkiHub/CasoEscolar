INSERT INTO Alumnos (nombreCompleto) VALUES 
('Juan Perez'),
('María García'),
('Pedro López'),
('Ana Martínez'),
('Luisa Hernández');

-- Insertar algunos profesores de prueba
INSERT INTO Profesores (nombreCompleto) VALUES 
('Carlos González'),
('Laura Rodríguez'),
('Javier Ramírez'),
('Sofía Díaz'),
('Roberto Sánchez');

-- Insertar algunas materias de prueba
INSERT INTO Materias (nombre) VALUES 
('Matemáticas'),
('Lengua y Literatura'),
('Ciencias Naturales'),
('Historia'),
('Educación Física');

-- Insertar algunos cursos de prueba
INSERT INTO Cursos (idProfesor, idMateria) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insertar algunos alumnos inscritos en cursos de prueba
INSERT INTO AlumnosInscritos (idAlumno, idCurso) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insertar algunas asignaciones de prueba
INSERT INTO Asignaciones (nombre, idCurso) VALUES 
('Tarea 1', 1),
('Examen parcial', 2),
('Proyecto final', 3),
('Tarea 2', 4),
('Examen final', 5);