package org.reg.resolveissueservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Admin(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @NotNull
    @Column(nullable = false)
    private String name;


    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Issue> issueList;



}
