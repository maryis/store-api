package com.Jumbo.store.storeapi.controller;

import com.Jumbo.store.storeapi.config.JwtTokenUtil;
import com.Jumbo.store.storeapi.dto.JwtRequest;
import com.Jumbo.store.storeapi.dto.JwtResponse;
import com.Jumbo.store.storeapi.service.impl.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    private JwtUserDetailsService jwtUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginController(JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        logger.info("login request for user {}",authenticationRequest.getUsername());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final JwtResponse jwtResponse = new JwtResponse(jwtTokenUtil.generateToken(userDetails));
        return jwtResponse;
    }

    private UserDetails authenticate(String username, String password) {

        System.out.println("HI:"+username+" "+password);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        if (!userDetails.getPassword().equals(password)) {
            logger.error("invalid login request for user {}",username);
            throw new BadCredentialsException("INVALID_CREDENTIALS");
        }
        return userDetails;
    }
}
