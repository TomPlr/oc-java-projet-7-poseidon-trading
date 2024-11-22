package org.poseidon.trading.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "RuleName")
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String name;

    @Column(name = "description", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String description;

    @Column(name = "json", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String json;

    @Column(name = "template", length = 512)
    @NotBlank(message= "This field is mandatory")
    private String template;

    @Column(name = "sqlStr", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String sqlStr;

    @Column(name = "sqlPart", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String sqlPart;
}
