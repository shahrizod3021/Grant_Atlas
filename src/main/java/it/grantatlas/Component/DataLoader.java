package it.grantatlas.Component;

import it.grantatlas.Entity.AboutUs;
import it.grantatlas.Entity.User;
import it.grantatlas.Repository.AboutUsRepository;
import it.grantatlas.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    private final AboutUsRepository aboutUsRepository;

    @Override
    public void run(String... args) throws Exception {
        if (init.equals("create-drop") || init.equals("create")){

            aboutUsRepository.save(
                    new AboutUs("Grant Atlas", "Grant Atlas", "Grant Atlas", "GrantAtlas", 100, 15)
            );
            User build = User.builder()
                    .name("Grant Atlas")
                    .email("shaxrizodmirzaaliyev@gmail.com")
                    .visible("root123")
                    .password(passwordEncoder.encode("root123"))
                    .role("ADMIN")
                    .build();
            authRepository.save(build);
        }
    }
}
