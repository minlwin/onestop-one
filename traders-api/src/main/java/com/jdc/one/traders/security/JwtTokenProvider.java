package com.jdc.one.traders.security;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt.issuer}")
	private String issuer;
	@Value("${app.jwt.limit}")
	private int limit;
	
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public Optional<String> generate(Authentication auth) {
		
		var expireAt = Calendar.getInstance();
		expireAt.add(Calendar.MINUTE, limit);
				
		if(null != auth && !(auth instanceof AnonymousAuthenticationToken)) {
			var token = Jwts.builder()
					.setIssuer(issuer)
					.setIssuedAt(new Date())
					.setExpiration(expireAt.getTime())
					.setSubject(auth.getName())
					.claim("role", auth.getAuthorities()
							.stream()
							.map(a -> a.getAuthority())
							.collect(Collectors.joining(",")))
					.signWith(key)
					.compact();
			return Optional.of(token);
		}
		
		return Optional.empty();
	}

	public void authenticate(String jwtToken) {

		if(StringUtils.hasLength(jwtToken)) {
			var parser = Jwts.parserBuilder().requireIssuer(issuer).setSigningKey(key).build();
			var jws = parser.parseClaimsJws(jwtToken);
			var username = jws.getBody().getSubject();
			var authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jws.getBody().get("role").toString());
			
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, authorities));
		}
	}

}
