package org.poseidon.trading.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "CurvePoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "curveId")
    private Integer curveId;

    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    @Column(name = "term")
    @Min(value = 0)
    @NotNull(message="This field is mandatory")
    private Double term;

    @Column(name = "value")
    @Min(value = 0)
    @NotNull(message="This field is mandatory")
    private Double value;

    @Column(name = "creationDate")
    private Timestamp creationDate;
}
