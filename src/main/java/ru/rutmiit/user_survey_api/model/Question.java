package ru.rutmiit.user_survey_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;
import ru.rutmiit.user_survey_api.util.PostgreSQLEnumType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "text", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    @Column(name = "type", columnDefinition = "question_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    public Question(String text, QuestionType type, List<Option> options) {
        this.text = text;
        this.type = type;
        this.options = options;
    }

    public Question(Long id) {
        this.id = id;
    }
}