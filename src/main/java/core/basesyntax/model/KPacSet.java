package core.basesyntax.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "k_pac_sets")
@Getter
@Setter
@ToString
public class KPacSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250)
    private String title;
    @ManyToMany
    @JoinTable(
            name = "k_pac_sets_k_pacs",
            joinColumns = @JoinColumn(name = "k_pac_set_id"),
            inverseJoinColumns = @JoinColumn(name = "k_pac_id")
    )
    private Set<KPac> kpacs;
}
