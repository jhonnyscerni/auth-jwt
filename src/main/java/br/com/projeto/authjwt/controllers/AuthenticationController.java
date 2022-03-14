package br.com.projeto.authjwt.controllers;

import br.com.projeto.authjwt.dto.JwtDto;
import br.com.projeto.authjwt.dto.LoginDto;
import br.com.projeto.authjwt.dto.UserDto;
import br.com.projeto.authjwt.security.jwt.JwtProvider;
import br.com.projeto.authjwt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtDto(jwt));
    }

    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<UserDto> resetPassword(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.resetPassword(email));
    }
}
