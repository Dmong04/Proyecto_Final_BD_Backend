use coco_tours_db
go

create procedure pa_client_users (
	@name VARCHAR(50) = null,
	@phone VARCHAR(20) = null,
	@email VARCHAR(70) = null,
	@username VARCHAR(30) = null,
	@password VARCHAR(150) = null
) as
begin
	insert into client([name]) values (@name)
	DECLARE @client_phone_id INT;
	DECLARE @client_id INT;
	SELECT @client_id = @@IDENTITY;
	SELECT @client_phone_id = @client_id;
	insert into client_phones(client_id, [phone]) values (@client_phone_id, @phone)
	insert into [user] (email, username, [password], client_id, admin_id)
	values (@email, @username, @password, @client_id, null)
end
go

execute pa_client_users 'client','80808080', 'client2@gmail.com', 'client2', '12345678'

select * from [user]

select * from [client]

select * from [client_phones]