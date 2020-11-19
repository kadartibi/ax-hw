package com.homework.family.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuStructure {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User owner;

    @Column(columnDefinition = "varchar(255) default 'Cars'")
    private String backGround;

    @Column(columnDefinition = "varchar(255) default 'Dark'")
    private String theme;

    @ElementCollection
    private List<String> icons;

}
