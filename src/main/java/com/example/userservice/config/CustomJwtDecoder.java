//package com.example.userservice.config;
//
//import com.example.userservice.dto.auth.request.IntrospectRequest;
//import com.example.userservice.services.AuthenticationService;
//import com.nimbusds.jose.JOSEException;
//import jakarta.annotation.Resource;
//import lombok.experimental.NonFinal;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtException;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.text.ParseException;
//import java.util.Objects;
//
//@Component
//public class CustomJwtDecoder implements JwtDecoder {
//
//    @NonFinal
//    @Value("${jwt.signerKey}")
//    protected String SECRET_KEY;
//
//    @Resource
//    private AuthenticationService authenticationService;
//
//    private NimbusJwtDecoder jwtDecoder = null;
//
//    @Override
//    public Jwt decode(String token) throws JwtException {
//        try {
//            var response = authenticationService.introspect(IntrospectRequest.builder()
//                    .token(token)
//                    .build());
//            if (!response.isValid()) {
//                throw new JwtException("invalid token");
//            }
//
//        } catch (ParseException | JOSEException e) {
//            throw new JwtException(e.getMessage());
//        }
//        if (Objects.isNull(jwtDecoder)) {
//            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HS512");
//            jwtDecoder = NimbusJwtDecoder
//                    .withSecretKey(secretKeySpec)
//                    .macAlgorithm(MacAlgorithm.HS512)
//                    .build();
//        }
//        System.out.println(jwtDecoder);
//
//        return jwtDecoder.decode(token);
//    }
//}
