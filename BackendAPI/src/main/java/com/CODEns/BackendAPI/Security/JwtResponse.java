package com.CODEns.BackendAPI.Security;

import java.io.Serializable;

import com.CODEns.BackendAPI.DTOs.UserDTO;
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final UserDTO user;
    private final String expiration;
    public JwtResponse(String jwttoken, String expiration, UserDTO user) {
        this.jwttoken = jwttoken;
        this.user = user;
        this.expiration = expiration;
    }
    public String getToken() {
        return this.jwttoken;
    }

    public UserDTO getUser() {
        return user;
    }

    public String getExpiration() {
        return expiration;
    }
}
