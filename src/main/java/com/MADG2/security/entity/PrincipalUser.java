package com.MADG2.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
public class PrincipalUser implements UserDetails{

    @NotNull
    private String name;

    @NotNull 
    @Column(unique = false)
    private String userName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static PrincipalUser build(User user){
        /*List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(rol -> new SimpleGrantedAuthority(rol.getRolName().name())).collect(Collectors.toList());
         */

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRolName().name()));
        return new PrincipalUser(user.getName(), user.getUserName(), user.getEmail(), user.getPassword(), authorities);
    }


}
