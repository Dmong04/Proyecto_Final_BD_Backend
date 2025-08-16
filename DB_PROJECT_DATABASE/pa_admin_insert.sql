USE coco_tours_db
GO

CREATE PROCEDURE pa_admin_insert (
	@name VARCHAR(50),
	@email VARCHAR(70),
	@username VARCHAR(30),
	@password VARCHAR(150)
) AS
BEGIN
BEGIN TRY

 --Name validation
IF @name IS NULL or @name = ' '
BEGIN
 RAISERROR('El nombre no corresponde a un valor válido', 16, 1)
 RETURN
END

INSERT INTO administrator ([name]) values (@name)

--Email validation
IF @email NOT LIKE '%_@_%._%'
BEGIN
 RAISERROR('El formato email no es válido', 16, 1)
 RETURN
END
ELSE IF exists (SELECT 1 FROM [user] WHERE email = @email)
BEGIN
 RAISERROR('El correo ya esta asosiado a un usuario', 16, 1)
 RETURN
END

--Username validation
IF @username IS NULL or @username = ' '
BEGIN
 RAISERROR('El nombre de usuario no corresponde a un valor válido', 16, 1)
 RETURN;
END
ELSE IF exists (SELECT 1 FROM [user] WHERE username = @username)
BEGIN
 RAISERROR('El nombre de usuario ya se enc^uentra en uso', 16, 1)
 RETURN
END

--Password valitadion
IF @password IS NULL or @username = ''
BEGIN
RAISERROR('la contraseña no es valida', 16, 1)
 RETURN
END
ELSE IF (@password NOT LIKE '%[0-9]%' OR @password NOT LIKE '%[!@#$^&*()]%')
BEGIN
 RAISERROR('La contraseña debe incluir un numero y un caracters especial', 16, 1)
 RETURN
END

--
INSERT INTO [user] (email, username, [password], client_id, admin_id)
VALUES (@email, @username, @password, null, @@IDENTITY)

END  TRY
BEGIN CATCH
	RAISERROR('Hubo un error en el proceso, por favor, revise los datos ingresados', 16, 1)
	RETURN
END CATCH
END
GO

