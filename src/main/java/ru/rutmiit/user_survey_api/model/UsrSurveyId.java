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
public class UsrSurveyId implements Serializable {
    @Serial
    private static final long serialVersionUID = -6535807601180461100L;
    @NotNull
    @Column(name = "usr_id", nullable = false)
    private Long usrId;

    @NotNull
    @Column(name = "survey_id", nullable = false)
    private Long surveyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsrSurveyId entity = (UsrSurveyId) o;
        return Objects.equals(this.surveyId, entity.surveyId) &&
                Objects.equals(this.usrId, entity.usrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveyId, usrId);
    }

}