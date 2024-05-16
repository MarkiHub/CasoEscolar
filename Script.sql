CREATE DATABASE sistemaescolar;

USE sistemaescolar;

CREATE TABLE Alumnos(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombreCompleto VARCHAR(255) NOT NULL,
    idPadre INT
);

CREATE TABLE Profesores(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombreCompleto VARCHAR(255) NOT NULL
);

CREATE TABLE Padres(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombreCompleto VARCHAR(255) NOT NULL
);

CREATE TABLE Materias(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE Cursos(
	id INT PRIMARY KEY AUTO_INCREMENT,
    idProfesor INT,
    idMateria INT NOT NULL,
    FOREIGN KEY (idProfesor) REFERENCES Profesores(id),
    FOREIGN KEY (idMateria) REFERENCES Materias(id)
);

CREATE TABLE AlumnosInscritos(
	id INT PRIMARY KEY AUTO_INCREMENT,
    idAlumno INT NOT NULL,
    idCurso INT NOT NULL,
    FOREIGN KEY (idAlumno) REFERENCES Alumnos(id),
	FOREIGN KEY (idCurso) REFERENCES Cursos(id)   
);

CREATE TABLE Asignaciones(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    idCurso INT NOT NULL,
    FOREIGN KEY (idCurso) REFERENCES Cursos(id)
);

CREATE TABLE entregaAsignacion(
	id INT PRIMARY KEY AUTO_INCREMENT,
	calificacion INT,
    fechaEntrega DATETIME NOT NULL DEFAULT NOW(),
	idAlumno INT NOT NULL,
	idAsignacion INT NOT NULL,
	aprobada BOOLEAN NOT NULL DEFAULT FALSE,
	FOREIGN KEY (idAlumno) REFERENCES alumnos(id),
	FOREIGN KEY (idAsignacion) REFERENCES asignaciones(id) 
);

CREATE TABLE Calificaciones(
	id INT PRIMARY KEY AUTO_INCREMENT,
    idAlumno INT NOT NULL,
    idCurso INT NOT NULL,
    calificacion INT NOT NULL
);

CREATE TABLE Conversaciones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_profesor INT NOT NULL,
    id_padre INT NOT NULL,
    FOREIGN KEY (id_profesor) REFERENCES Profesores (id),
    FOREIGN KEY (id_padre) REFERENCES Padres (id)
);

CREATE TABLE Mensaje (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_conversacion INT NOT NULL,
    id_sender INT NOT NULL,
    text TEXT,
    fechaEnviado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_conversacion) REFERENCES Conversaciones (id)
);