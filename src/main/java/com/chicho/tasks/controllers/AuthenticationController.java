package com.chicho.tasks.controllers;

import com.chicho.tasks.model.user.AuthenticationDTO;
import com.chicho.tasks.model.user.LoginResponseDTO;
import com.chicho.tasks.model.user.RegisterDTO;
import com.chicho.tasks.model.user.User;
import com.chicho.tasks.repositories.UserRepository;
import com.chicho.tasks.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            TokenService tokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if (userRepository.findUserByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
