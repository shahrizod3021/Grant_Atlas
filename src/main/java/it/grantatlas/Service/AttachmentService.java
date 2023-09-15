package it.grantatlas.Service;

import it.grantatlas.Entity.Attachment;
import it.grantatlas.Entity.AttachmentContent;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Repository.AttachmentContentRepository;
import it.grantatlas.Repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final AttachmentContentRepository attachmentContentRepository;


    public HttpEntity<?> upload(MultipartHttpServletRequest request) {
        try {
            Iterator<String> fileNames = request.getFileNames();
            MultipartFile file = request.getFile(fileNames.next());
            if (Objects.requireNonNull(Objects.requireNonNull(file).getContentType()).equals("image/jpeg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/jpg")){
                Attachment attachment = new Attachment(
                        file.getOriginalFilename(),
                        file.getContentType(),
                        file.getSize()
                );
                Attachment save = attachmentRepository.save(attachment);
                AttachmentContent attachmentContent = new AttachmentContent(
                        save,
                        file.getBytes()
                );
                attachmentContentRepository.save(attachmentContent);
                return ResponseEntity.ok(save.getId());
            }
            return ResponseEntity.badRequest().body(new ApiResponse("вы можете загружать изображения только в формате .jpeg или .png.", false, 400));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("К сожалению, вы не можете загрузить изображение размером более 10 МБ.");
        }
    }




    public HttpEntity<?> getFileJon(UUID id) {
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()) {
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(id);
            Attachment attachment = byId.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                    .body(attachmentContent.getBytes());
        }
        return null;
    }
}
