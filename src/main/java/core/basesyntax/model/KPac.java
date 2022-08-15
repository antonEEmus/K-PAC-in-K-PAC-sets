package core.basesyntax.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "k_pacs")
@Data
public class KPac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250)
    private String title;
    @Column(length = 2000)
    private String description;
    @Column(name = "creation_date")
    private Timestamp creationDate;
}
