package br.com.projeto.authjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PERMISSIONS")
@SequenceGenerator(name = "seq_permission", sequenceName = "seq_permission", initialValue = 1, allocationSize = 1)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permission implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permission")
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String description;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.name;
    }

}

