package org.poseidon.trading.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "BidList")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId", nullable = false)
    private Integer id;

    @Column(name = "account", nullable = false, length = 30)
    @NotBlank(message= "This field is mandatory")
    private String account;

    @Column(name = "type", nullable = false, length = 30)
    @NotBlank(message= "This field is mandatory")
    private String type;

    @Column(name = "bidQuantity")
    @NotNull(message="This field is mandatory")
    private Double bidQuantity;

    @Column(name = "askQuantity")
    private Double askQuantity;

    @Column(name = "bid")
    private Double bid;

    @Column(name = "ask")
    private Double ask;

    @Column(name = "benchmark", length = 125)
    private String benchmark;

    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    @Column(name = "commentary", length = 125)
    private String commentary;

    @Column(name = "security", length = 125)
    private String security;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "trader", length = 125)
    private String trader;

    @Column(name = "book", length = 125)
    private String book;

    @Column(name = "creationName", length = 125)
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "revisionName", length = 125)
    private String revisionName;

    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    @Column(name = "dealName", length = 125)
    private String dealName;

    @Column(name = "dealType", length = 125)
    private String dealType;

    @Column(name = "sourceListId", length = 125)
    private String sourceListId;

    @Column(name = "side", length = 125)
    private String side;
}
