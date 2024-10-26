CREATE TABLE contacto (
  idcontacto int NOT NULL IDENTITY PRIMARY KEY,
  nombre varchar(45) DEFAULT NULL,
  apellido varchar(45) DEFAULT NULL,
  apodo varchar(45) DEFAULT NULL,
  celular int DEFAULT NULL,
  celular2 int DEFAULT NULL,
  trabajo int DEFAULT NULL,
  casa int DEFAULT NULL,
  correo varchar(45) DEFAULT NULL,
  correo2 varchar(45) DEFAULT NULL,
  fechaInsert date NOT NULL
)

create procedure ProInsertarContacto 
(
@Nombre VARCHAR(45),
@Apellido VARCHAR(45),
@Apodo VARCHAR(45),
@Celular INT,
@Celular2 INT,
@Trabajo INT,
@Casa INT,
@Correo VARCHAR(45),
@Correo2 VARCHAR(45)
)
as
INSERT INTO contacto(nombre, apellido, apodo, celular, celular2, trabajo, casa, correo, correo2, fechaInsert) 
values(@Nombre,@Apellido,@Apodo,@Celular,@Celular2,@Trabajo,@Casa,@Correo,@Correo2,GETDATE())

create procedure ProEliminarContacto(@Id_Contacto INT)
as
DELETE FROM contacto WHERE idcontacto=@Id_Contacto;

create procedure ProModificarContacto(
@Nombre VARCHAR(45),
@Apellido VARCHAR(45),
@Apodo VARCHAR(45),
@Celular INT,
@Celular2 INT,
@Trabajo INT,
@Casa INT,
@Correo VARCHAR(45),
@Correo2 VARCHAR(45),
@Id_Contacto INT)
as
UPDATE contacto SET nombre=@Nombre,apellido=@Apellido,apodo=@Apodo,celular=@Celular,celular2=@Celular2,
trabajo=@Trabajo,casa=@Casa,correo=@Correo,correo2=@Correo2,fechaInsert=GETDATE() WHERE idcontacto=@Id_Contacto;

create procedure ProLeerContactos(@id int)
as
select RIGHT (idcontacto, 5) AS idcontacto,
  nombre,
  apellido,
  apodo,
  celular,
  celular2,
  trabajo,
  casa,
  correo,
  correo2,
  fechaInsert
FROM
  Contacto
WHERE
  idContacto = @id


create procedure ProLeeContacto (@desde int, @cuantos int,@Busqueda VARCHAR(200))
as
SELECT RIGHT(idContacto, 5) AS idContacto,
  nombre,
  apellido,
  apodo,
  celular,
  celular2,
  trabajo,
  casa,
  correo,
  correo2,
  fechaInsert
FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY nombre) as row FROM contacto) a WHERE row > @desde and row <= @cuantos and

  nombre LIKE CONCAT('%', @Busqueda, '%') OR apellido LIKE CONCAT('%', @Busqueda, '%') OR apodo LIKE CONCAT('%', @Busqueda, '%') 
  OR celular LIKE CONCAT('%', @Busqueda, '%') OR celular2 LIKE CONCAT('%', @Busqueda, '%') OR trabajo LIKE CONCAT('%', @Busqueda, '%')
   OR casa LIKE CONCAT('%', @Busqueda, '%') OR correo LIKE CONCAT('%', @Busqueda, '%') OR correo2 LIKE CONCAT('%', @Busqueda, '%') 
   OR fechaInsert LIKE CONCAT('%', @Busqueda, '%')


create procedure ProCuantosContactos (@Busqueda VARCHAR(200))
as
SELECT
  COUNT(*) AS cuantos
FROM
  Contacto
WHERE
   nombre LIKE CONCAT('%', @Busqueda, '%') OR apellido LIKE CONCAT('%', @Busqueda, '%') OR apodo LIKE CONCAT('%', @Busqueda, '%') 
  OR celular LIKE CONCAT('%', @Busqueda, '%') OR celular2 LIKE CONCAT('%', @Busqueda, '%') OR trabajo LIKE CONCAT('%', @Busqueda, '%')
   OR casa LIKE CONCAT('%', @Busqueda, '%') OR correo LIKE CONCAT('%', @Busqueda, '%') OR correo2 LIKE CONCAT('%', @Busqueda, '%') 
   OR fechaInsert LIKE CONCAT('%', @Busqueda, '%')


exec ProInsertarContacto 'marcelo','burgos','marchelo','990715586','0','0','0','marchelo.1989@live.cl','marchelo.bm@gmail.com'
exec ProLeeContacto '0','10',''
select * from contacto