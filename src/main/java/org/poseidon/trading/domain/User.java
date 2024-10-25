package org.poseidon.trading.domain;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "username", length = 125)
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(name = "password", length = 125)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "fullname", length = 125)
    private String fullname;

    @Column(name = "role", length = 125)
    @NotBlank(message = "Role is mandatory")
    private String role;
}
