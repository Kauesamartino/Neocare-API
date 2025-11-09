package com.neocare.api.infrastructure.services;

import com.neocare.api.domain.repository.CredenciaisRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CredenciaisRepository credenciaisRepository;

    public CustomUserDetailsService(CredenciaisRepository credenciaisRepository) {
        this.credenciaisRepository = credenciaisRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credenciaisRepository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
