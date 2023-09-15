package it.grantatlas.Service;

import it.grantatlas.Entity.User;
import it.grantatlas.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSendService {

    private final JavaMailSender javaMailSender;

    private final AuthRepository authRepository;

    public void sendToEmail(String subject, String text){
        User user = authRepository.findUserByRole("ADMIN").orElseThrow(() -> new ResourceNotFoundException("admin ma'lumotlari topilmagani sababli emailga habar yuborib bo'lmadi"));
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("shaxrizodmirzaaliyevdev@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
