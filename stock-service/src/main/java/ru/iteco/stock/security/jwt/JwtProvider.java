package ru.iteco.stock.security.jwt;

public interface JwtProvider {

    String generateJwt(String username);
    boolean validateJwt(String token);
    String getUserNameFromJwt(String token);

}
