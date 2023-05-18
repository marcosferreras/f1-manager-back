package com.MADG2.security.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class JwtDTO {

    private String token;

    private String bearer = "Bearer";

    private String userName;

    private Collection<? extends GrantedAuthority> authorities;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public JwtDTO(String token, String userName, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userName = userName;
        this.authorities = authorities;
    }


}
