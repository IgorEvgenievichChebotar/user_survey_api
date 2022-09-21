package ru.rutmiit.user_survey_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usr")
public class Usr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @Type(type = "org.hibernate.type.TextType")
    private String name;

    @Column(name = "email")
    @Type(type = "org.hibernate.type.TextType")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usr_survey",
            joinColumns = @JoinColumn(name = "usr_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id"))
    private List<Survey> surveys = new ArrayList<>();

    public Usr(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}