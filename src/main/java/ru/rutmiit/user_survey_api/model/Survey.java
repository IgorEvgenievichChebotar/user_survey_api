package ru.rutmiit.user_survey_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usr_survey",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "usr_id"))
    private List<Usr> usrs = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    public Survey(Long id, String title, LocalDate endDate, String description, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.description = description;
        this.questions = questions;
    }

    public Survey(List<Question> questions) {
        this.questions = questions;
    }
}