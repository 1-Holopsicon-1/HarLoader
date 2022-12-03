package harloader.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.*;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@TypeDefs({
        @TypeDef(name= "jsonb", typeClass = JsonBinaryType.class, defaultForType = Har.class)
})
@Entity
@Getter
@Setter
public class HarArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private HarLog data;

    private String browserData;
    private String archiveVer;

    @OneToMany(mappedBy = "harArchive", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Request> request;
}