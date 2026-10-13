package service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    //extract username from token.
    String extractUsername(String token);
    //validate the token
    boolean isTokenValid(String token , UserDetails userDetails);
    //generate new token we need to create claims along with user details.
    String generateToken(Map<String, Object>claims , UserDetails userDetails);
}
