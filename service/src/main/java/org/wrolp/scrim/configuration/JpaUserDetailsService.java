package org.wrolp.scrim.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.wrolp.scrim.data.entity.User;
import org.wrolp.scrim.data.repository.UserRepository;

@Component
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("");
        }
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), AuthorityUtils.createAuthorityList("admin"));
    }

}
