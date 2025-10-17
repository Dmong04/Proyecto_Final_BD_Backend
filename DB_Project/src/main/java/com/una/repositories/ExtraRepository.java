package com.una.repositories;

import com.una.models.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Integer> {
    public Optional<Extra> findExtraByName(String name);
    public Optional<Extra> findByName(String name);

    @Procedure(name = "pa_extra_search_all")
    List<Extra> pa_extra_search_all();

//    @Procedure(name = "pa_extra_search_by_id")
//    Extra pa_extra_search_by_id(@Param("extra_id") Integer extra_id);

    @Procedure(name = "pa_extra_insert")
    void pa_extra_insert(@Param("name") String name, @Param("description") String description,
                            @Param("unit_price") Integer unit_price);

    @Procedure(name = "pa_extra_update")
    void pa_extra_update(@Param("extra_id") Integer extra_id, @Param("new_name") String new_name, @Param("new_description") String new_description,
                         @Param("new_price") Integer new_price);

    @Procedure(name = "pa_extra_delete")
    void pa_extra_delete(@Param("extra_id") Integer extra_id);
}
