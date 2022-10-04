package ru.rutmiit.user_survey_api.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;
import ru.rutmiit.user_survey_api.util.PostgreSQLEnumType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "question")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Question {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "text", nullable = false)
    private String text;

    @OneToOne(mappedBy = "question")
    private Answer answer;

    @OneToMany(mappedBy = "question", cascade = ALL)
    private List<Option> options = new ArrayList<>();

    @Column(name = "type", columnDefinition = "question_type")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    public Question(String text, QuestionType type, List<Option> options) {
        this.text = text;
        this.options = options;
        this.type = type;
    }

    public Question(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Question question = (Question) o;
        return id != null && Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}