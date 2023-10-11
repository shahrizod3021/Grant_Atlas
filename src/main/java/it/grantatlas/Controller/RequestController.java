package it.grantatlas.Controller;

import it.grantatlas.Entity.Request;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Repository.RequestRepository;
import it.grantatlas.Service.MailSendService;
import it.grantatlas.Service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestRepository repository;

    private final MailSendService mailSendService;

    private final TelegramService telegramService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Request> all = repository.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public HttpEntity<?> requesting(@RequestParam(name = "name") String name, @RequestParam(name = "phoneNumber") String phoneNumber, @RequestParam(name = "comment") String comment) {
        Request request = Request.builder().name(name).phoneNumber(phoneNumber).plusComment(comment).build();
        repository.save(request);
        String text = "🤵Kim tomondan: " + request.getName() + "\n📞Bog'lanish uchun: " + request.getPhoneNumber() + "\nFoydalanuvchi izohi: " + request.getPlusComment();
        mailSendService.sendToEmail("Sizda yangi so'rovnoma", text);
        telegramService.sendMessage(text, "-4051650649");
        return ResponseEntity.ok(new ApiResponse("Sizning so'rovingiz qabul qilindi", true, 200));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRequest(@PathVariable Long id) {
        Request request = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Siz tanlagan So'rovnoma bazada topilmadi"));
        repository.delete(request);
        return ResponseEntity.ok(new ApiResponse("Olib tashlandi", true, 200));
    }
}
