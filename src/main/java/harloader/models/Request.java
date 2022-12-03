package harloader.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vladmihalcea.hibernate.type.json.JsonType;
import de.sstoehr.harreader.model.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;


import javax.persistence.*;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String body;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, String> headers;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, String> params;

    private HttpMethod method;
    //private Double perc = 0.0d;
    @JsonBackReference
    @ManyToOne
    private User user;

    @JsonBackReference
    @ManyToOne
    private HarArchive harArchive;

}

