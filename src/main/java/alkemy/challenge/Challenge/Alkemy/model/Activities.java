package alkemy.challenge.Challenge.Alkemy.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET deleted WHERE name =?")
@FilterDef(name = "deletedActivitiesFilter", parameters = @ParamDef(name = "isDeleted", type
        = "boolean"))
@Filter(name = "deletedActivitiesFilter", condition = "deleted = :isDeleted")
public class Activities {

    @Id
    @SequenceGenerator(name = "publication_sequence", sequenceName = "publication_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publication_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "image")
    private String image;

    @Column
    private boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "activities_date")
    private Date activitiesDate;
}


