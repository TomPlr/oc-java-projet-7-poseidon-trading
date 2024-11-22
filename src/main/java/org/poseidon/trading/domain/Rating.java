package org.poseidon.trading.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "moodysRating", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String moodysRating;

    @Column(name = "sandPRating", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String sandPRating;

    @Column(name = "fitchRating", length = 125)
    @NotBlank(message= "This field is mandatory")
    private String fitchRating;

    @Column(name = "orderNumber")
    @NotNull(message="This field is mandatory")
    private Integer orderNumber;
}
