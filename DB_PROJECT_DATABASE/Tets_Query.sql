USE coco_tours_db;
GO

EXEC pa_admin_insert 
	@name = 'Soporte',
	@email = 'Soporte@gmail.com',
	@username = 'soporte01',
	@password = 'Soporte#1234';

	-- Ver administradores creados
SELECT * FROM administrator;

-- Ver usuarios asociados a administradores
SELECT * FROM [user] WHERE admin_id IS NOT NULL;
