package org.wrolp.scrim.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.wrolp.scrim.data.entity.User;

@RepositoryRestResource(exported = true)
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

}
