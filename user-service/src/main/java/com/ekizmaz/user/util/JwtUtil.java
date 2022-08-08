package com.ekizmaz.user.util;

import com.ekizmaz.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

	private static final long EXPIRATION_TIME = 300_000;

	private static final String SECRET_KEY = "logo_yazilim-patika-top-secret-key-logo_yazilim-patika-top-secret-key";

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String generateToken(User foundUser) {

		Map<String, Object> claims = new HashMap<>();

		claims.put("email", foundUser.getEmail());
		claims.put("firmType", foundUser.getFirmType().toString());
		claims.put("id", foundUser.getId());

		long now = System.currentTimeMillis();

		//// @formatter:off
		return Jwts.builder()
				.setClaims(claims)
				.setIssuer("user-service")
				.setSubject(foundUser.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date(now + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
		// @formatter:on

	}

}
