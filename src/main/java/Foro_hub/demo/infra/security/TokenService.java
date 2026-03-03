package Foro_hub.demo.infra.security;

import Foro_hub.demo.domain.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername()) // quién es el usuario
                .setIssuedAt(new Date())            // fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // expira en 1 día
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // firma con clave secreta
                .compact();
    }

    public String obtenerSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
