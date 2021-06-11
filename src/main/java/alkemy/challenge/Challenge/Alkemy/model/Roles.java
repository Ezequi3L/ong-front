package alkemy.challenge.Challenge.Alkemy.model;

import javax.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_id")
    private Long id;
    private String name;//"ROLE_ADMIN", "ROLE_USER"
    private String description;

    private static final long serialVersionUID = 1L;
}
