package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuarios;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuarios usuario){
        try{
            var algoritmo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API vollmed")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(expiresToken())
                    .sign(algoritmo);

        } catch (JWTCreationException e){
            throw new RuntimeException("");
        }
    }

    public Instant expiresToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT){
        try{
            var algoritmo = Algorithm.HMAC256(secret);


            return JWT.require(algoritmo)
                    .withIssuer("API vollmed")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTCreationException e){
            throw new RuntimeException("Invalid or inspired Token");
        }
    }

}
