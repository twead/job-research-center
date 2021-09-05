package com.szakdolgozat.security.jwt;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.JWTClaimsSet;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import com.szakdolgozat.security.dto.JwtDto;
import com.szakdolgozat.security.entity.UserPrincipal;

@Component
public class JwtProvider {

	private final static Logger logger = (Logger) LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return Jwts.builder().setSubject(userPrincipal.getUsername()).claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
			return true;

		} catch (MalformedJwtException e) {
			logger.error("Token badly formed");
		} catch (UnsupportedJwtException e) {
			logger.error("Token not supported");
		} catch (ExpiredJwtException e) {
			// logger.error("Token expired");
		} catch (IllegalArgumentException e) {
			logger.error("Token is empty");
		} catch (SignatureException e) {
			logger.error("Fail at the signature");
		}

		return false;
	}

	public String refreshToken(JwtDto jwtDto) throws ParseException {
		JWT jwt = JWTParser.parse(jwtDto.getToken());
		JWTClaimsSet claims = jwt.getJWTClaimsSet();
		String email = claims.getSubject();
		List<String> roles = (List<String>) claims.getClaim("roles");

		return Jwts.builder().setSubject(email).claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

}
