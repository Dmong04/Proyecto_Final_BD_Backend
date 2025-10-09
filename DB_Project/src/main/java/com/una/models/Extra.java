
package com.una.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "extra")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "pa_extra_search_all",
                procedureName = "pa_extra_search_all",
                resultClasses = Extra.class
        ),
        @NamedStoredProcedureQuery(
                name = "pa_extra_insert",
                procedureName = "pa_extra_insert",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "description", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "unit_price", type = BigDecimal.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "pa_extra_update",
                procedureName = "pa_extra_update",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "extra_id", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "new_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "new_description", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "new_price", type = BigDecimal.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "pa_extra_delete",
                procedureName = "pa_extra_delete",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "extra_id", type = Integer.class)
                }
        )
})
public class Extra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;
}
