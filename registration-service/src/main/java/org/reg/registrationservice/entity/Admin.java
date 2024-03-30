package org.reg.registrationservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false,unique = true)
    private Long phoneNo;
    

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;


}
