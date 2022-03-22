package br.com.projeto.authjwt.models;

import br.com.projeto.authjwt.models.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ROLES")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", initialValue = 1, allocationSize = 1)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 30)
    private RoleType roleName;

    @ManyToMany
    @JoinTable(name = "TB_ROLES_PERMISSIONS", joinColumns = @JoinColumn(name = "roleId"),
        inverseJoinColumns = @JoinColumn(name = "permissionId"))
    private List<Permission> permissions = new ArrayList<>();

    public boolean removerPermissao(Permission permissao) {
        return getPermissions().remove(permissao);
    }

    public boolean adicionarPermissao(Permission permissao) {
        return getPermissions().add(permissao);
    }

}
