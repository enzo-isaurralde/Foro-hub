package Foro_hub.demo.infra.security;

import Foro_hub.demo.domain.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getNombre()) // quién es el usuario
                .setIssuedAt(new Date())            // fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // expira en 1 día
                .signWith(SignatureAlgorithm.HS256, secretKey) // firma con clave secreta
                .compact();
    }

    public String obtenerSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
