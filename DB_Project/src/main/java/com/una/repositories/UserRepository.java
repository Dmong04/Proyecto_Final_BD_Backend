package com.una.repositories;

import com.una.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);

    @Procedure(name = "pa_admin_insert")
    void pa_admin_insert(@Param("name") String name, @Param("email") String email,
                         @Param("username") String username, @Param("password") String password);

    @Procedure(name = "pa_admin_update")
    void pa_admin_update(@Param("id") Integer id, @Param("name") String name, @Param("email") String email,
                         @Param("username") String username, @Param("password") String password);

    @Procedure(name = "pa_admin_delete")
    void pa_admin_delete(@Param("id") Integer id);

    // Procedimientos almacenados de clientes

    @Procedure(name = "pa_client_insert")
    void pa_client_insert(@Param("name") String name, @Param("phone") String phone, @Param("email") String email,
                          @Param("username") String username, @Param("password") String password);

    @Procedure(name = "pa_client_update")
    void pa_client_update(@Param("id") Integer id, @Param("name") String name, @Param("phone") String phone,
                          @Param("email") String email, @Param("username") String username, @Param("password") String password);

    @Procedure(name = "pa_client_delete")
    void pa_client_delete(@Param("id") Integer id);
}
