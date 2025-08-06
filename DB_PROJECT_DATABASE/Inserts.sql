use coco_tours_db;
go

insert into administrator(name) values(
	'Dereck Monge'
)

insert into [user](email, username, [password], client_id, admin_id, role) 
values ('dmong@gmail.com', 'dmong', '12345678', null, 1, 'ADMIN')

select * from [user]