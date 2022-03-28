package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
