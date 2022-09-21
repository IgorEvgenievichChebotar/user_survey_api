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
@Table(name = "answer")
public class Answer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private AnswerId id;

    @MapsId("usrId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "usr_id", nullable = false)
    private Usr usr;

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "answer")
    @Type(type = "org.hibernate.type.TextType")
    private String answer;

    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}