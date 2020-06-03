package com.example.userauthservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.userauthservice.dto.request.AuthenticationRequest;
import com.example.userauthservice.dto.response.AuthenticationResponse;
import com.example.userauthservice.dto.response.UserPermittedMenuResponse;
import com.example.userauthservice.entity.MyUserDetails;
import com.example.userauthservice.entity.User;
import com.example.userauthservice.repository.UserRepository;
import com.example.userauthservice.service.HomeService;
import com.example.userauthservice.service.impl.MyUserDetailsService;
import com.example.userauthservice.util.JwtUtil;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home(){
        return ("<h2>Hello there!</h2>");
    }

    //@PreAuthorize("hasAuthority('"+AUDIT_REPORT+ "_" + VIEW +"')")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'EDITOR')")
    @GetMapping("/user")
    public String user(Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getAllowedSurah());
        System.out.println(userDetails.getAuthorities().toString());
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        System.out.println(user.getRoles());
        return ("<h2>Hello User!</h2>");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'EDITOR')")
    @GetMapping("/get-menu-list")
    public List<UserPermittedMenuResponse> userMenu(Authentication authentication){
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        return homeService.getPermittedMenu(user.getRoles().getId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return ("<h2>Hello Admin!</h2>");
    }

    @PreAuthorize("hasAuthority('SURAH_CREATE')")
    @GetMapping("/create")
    public String edit(){
        return "In edit....";
    }

    @PreAuthorize("hasAuthority('SURAH_VIEW')")
    @GetMapping("/view")
    public String view(){
        return "In View";
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("incorrct details", e);
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        
        System.out.println(userDetails.getAuthorities().toString());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}