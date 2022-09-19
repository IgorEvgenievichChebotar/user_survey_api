package ru.rutmiit.user_survey_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AnswerId implements Serializable {
    @Serial
    private static final long serialVersionUID = 3083930312147793708L;
    @NotNull
    @Column(name = "usr_id", nullable = false)
    private Long usrId;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnswerId entity = (AnswerId) o;
        return Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.usrId, entity.usrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, usrId);
    }

}