package com.example.userservice.services;

import com.example.userservice.dto.auth.request.AuthenticationRequest;
import com.example.userservice.dto.auth.request.IntrospectRequest;
import com.example.userservice.dto.auth.request.LogoutRequest;
import com.example.userservice.dto.auth.response.AuthenticationResponse;
import com.example.userservice.dto.auth.response.IntrospectResponse;
import com.example.userservice.entity.InvalidedToken;
import com.example.userservice.entity.User;
import com.example.userservice.exception.AppException;
import com.example.userservice.exception.ErrorCode;
import com.example.userservice.permisson.Permission;
import com.example.userservice.permisson.PermissionRepository;
import com.example.userservice.repository.InvalidedTokenRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.role.Role;
import com.example.userservice.role.RoleRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
public class AuthenticationService {

    @Resource
    private UserRepository userRepository;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    RoleRepository roleRepository;
    @Resource
    PermissionRepository permissionRepository;

    @Resource
    private InvalidedTokenRepository invalidedTokenRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SECRET_KEY;

    public AuthenticationResponse authentication(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generalToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        // lấy token
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (AppException | JOSEException |ParseException  e) {
            isValid = false;
        }


        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }


    private String generalToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);


        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("Minh")
                .issueTime(new Date())

                .expirationTime(new Date(Instant.now().plus(
                        15, ChronoUnit.MINUTES
                ).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();


        Payload payload = new Payload(jwtClaimsSet.toJSONObject());


        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        // MACSigner() khóa để kí và khóa giải mã trùng nhau
        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can not create token");
            throw new RuntimeException(e);
        }

    }


    public String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoleId())) {
            List<Role> roles = roleRepository.findAllById(user.getRoleId());
            for (Role role : roles) {
                String roleName = role.getName();
                stringJoiner.add("ROLE_" + roleName);
                if (!CollectionUtils.isEmpty(role.getPermissionId())) {
                    List<Permission> permissions = permissionRepository.findAllById(role.getPermissionId());
                    for (Permission permission : permissions) {
                        stringJoiner.add("PERMISSION_" + permission.getName());
                    }
                }

            }

        }
        return stringJoiner.toString();
    }


    public void logout(LogoutRequest request) throws ParseException, JOSEException {

        var signToken = verifyToken(request.getToken());
        // lấy jwtToken Id
        String jit = signToken.getJWTClaimsSet().getJWTID();
        // lấy expirationDate
        Date expirationDate = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidedToken invalidedToken = InvalidedToken.builder()
                .id(jit)
                .expriryDate(expirationDate)
                .build();


        invalidedTokenRepository.save(invalidedToken);

    }


    // hàm sử lý token
    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {


        // giải mã thật toán đã has
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        // dùng để parse chuỗi token
        SignedJWT signedJWT = SignedJWT.parse(token);

        // kiểm tra token hết hạn chưa
        Date expTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);


        // nếu token không dc verify hoặc hết hạn
        if (!(verified && expTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }


        if (invalidedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }
}
