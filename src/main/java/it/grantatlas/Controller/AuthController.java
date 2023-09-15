package it.grantatlas.Controller;

import it.grantatlas.Entity.User;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.AuthRequest;
import it.grantatlas.Payload.AuthResponse;
import it.grantatlas.Repository.AuthRepository;
import it.grantatlas.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/v1/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthRepository authRepository;

    @GetMapping("/{id}")
    public HttpEntity<?> getAll(@PathVariable UUID id) {
        User user = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user topilmadi"));
        return ResponseEntity.status(user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(user);
    }

    @PostMapping("/login")
    private HttpEntity<?> login(@RequestBody AuthRequest authRequest) {
        AuthResponse login = authService.login(authRequest);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, login.token()).body(login);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editData(@PathVariable UUID id, @RequestParam(name = "name") String name, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        ApiResponse apiResponse = authService.editData(id, name, email, password);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
