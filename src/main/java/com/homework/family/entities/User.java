package com.homework.family.entities;

import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String userName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Singular
    private Set<Role> roles;

    @Column(nullable = false)
    private String familyId;

    @OneToOne(mappedBy = "owner")
    private MenuStructure menuStructure;

}
