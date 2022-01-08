package com.tweetusermangement.resuorces;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetusermangement.Iservice.UserRegistrationServiceI;
import com.tweetusermangement.dto.AuthenticateRequest;
import com.tweetusermangement.dto.AuthenticateResponse;
import com.tweetusermangement.dto.RegisterationRequest;
import com.tweetusermangement.entity.RegistrationEntity;
import com.tweetusermangement.serviceimpl.CustomUserDetailService;
import com.tweetusermangement.util.JwtTokenUtil;

@RequestMapping("/api/v1.0/tweets")
@RestController
public class UserRegistrationController {
	Logger log = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	private UserRegistrationServiceI registrationService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService userService;
	
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterationRequest request) {
		log.info("{}", request);
		if (registrationService.existingUser(request.getEmail())) {
          return new ResponseEntity<>("Email Id Is already Registered",HttpStatus.BAD_REQUEST);
		}
		request.setPassword(encoder.encode(request.getPassword()));
		RegistrationEntity res = registrationService.register(request);
		log.info("{}", res);
		return new ResponseEntity<>("User Added Successfully", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticationRequest)
			throws Exception {
		log.info("{}", authenticationRequest);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			log.info("{}", e);
			throw new Exception("Invalid username or password", e);
		}
		UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtTokenUtil.generateToken(userDetails);
		log.info("{}", jwt);
		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}

}
