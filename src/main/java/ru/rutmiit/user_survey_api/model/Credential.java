package ru.rutmiit.user_survey_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "credential")
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String username;

    @Column(name = "password", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String password;

    @Column(name = "role", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String role;

}