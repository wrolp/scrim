package org.wrolp.scrim.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.wrolp.scrim.data.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
