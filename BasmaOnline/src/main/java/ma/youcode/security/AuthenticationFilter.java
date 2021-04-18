package ma.youcode.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.youcode.requests.UserLoginRequests;
import ma.youcode.services.UserService;
import ma.youcode.shared.SpringApplicationContext;
import ma.youcode.shared.UserDto;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	// For Authentication
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		try {
			UserLoginRequests creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequests.class);
			return authenticationManager.authenticate(
					// Verify if Email and Password in DB
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	// When the user excited in DB
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String userName = ((User) auth.getPrincipal()).getUsername();
		// Generate the Token
		String token = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPRIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.TOKEN_SECRET).compact();
		// Contexte est un mécanisme qui ma ne permettre instancié ou récupère un objet
		// dans application
		UserService userService = (UserService) SpringApplicationContext.getBean("userServicesImp");
		UserDto userDto = userService.getUser(userName);
		// Send Token to Header
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		// Send UserId to Header
		res.addHeader("user_id", userDto.getUserId());

		
		//        res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ userDto.getUserId() + "\"}");

		// res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+
		// userDto.getUserId() + "\"}");

	}

}
