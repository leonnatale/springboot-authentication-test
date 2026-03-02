package io.github.leonnatale.ultest.filter;

import io.github.leonnatale.ultest.model.person.Person;
import io.github.leonnatale.ultest.repositories.PersonRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final PersonRepository personRepository;
    public AuthenticationFilter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader == null || authenticationHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }
        Optional<Person> found = personRepository.findById(Long.parseLong(authenticationHeader));
        if (found.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        Person person = found.get();

        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + person.getRole().name()));
        var authToken = new UsernamePasswordAuthenticationToken(
                person,
                null,
                authorities
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
