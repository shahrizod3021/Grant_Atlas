package it.grantatlas.Service;

import it.grantatlas.Entity.User;
import it.grantatlas.Jwt.JWTUtil;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.AuthRequest;
import it.grantatlas.Payload.AuthResponse;
import it.grantatlas.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        User principal = (User) authentication.getPrincipal();
        authRepository.save(principal);
        String token = jwtUtil.issueToken(principal.getUsername(), principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return new AuthResponse(token, principal);
    }

    public ApiResponse editData(UUID id, String name, String email, String password){
        User user = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("sizning shaxsiy ma'lumotlaringiz bazada topilmadi"));
        user.setName(name.length() != 0  ? name : user.getName());
        user.setEmail(email.length() != 0 ? email : user.getEmail());
        user.setPassword(password.length() != 0 ? passwordEncoder.encode(password) : passwordEncoder.encode(user.getPassword()));
        user.setVisible(password.length() != 0 ? password : user.getVisible());
        authRepository.save(user);
        return new ApiResponse("Ma'lumotlar taxrirlandi", true, 200);
    }


}
