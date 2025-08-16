USE coco_tours_db
GO

CREATE PROCEDURE pa_passenger_delete (
    @passenger_id INT
) AS
BEGIN
BEGIN TRY
    IF NOT EXISTS (SELECT 1 FROM passengers WHERE id = @passenger_id)
    BEGIN
        RAISERROR('El pasajero no existe', 16, 1)
        RETURN
    END

    DELETE FROM passengers WHERE id = @passenger_id;
END TRY
BEGIN CATCH
    RAISERROR('Error al eliminar pasajero', 16, 1)
END CATCH
END
GO