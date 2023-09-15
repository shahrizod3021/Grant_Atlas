package it.grantatlas.Controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import it.grantatlas.Entity.News;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.NewsDto;
import it.grantatlas.Repository.NewsRepository;
import it.grantatlas.Service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/news")
public class NewsController {

    private final NewsService newsService;

    private final NewsRepository newsRepository;

    @GetMapping
    public HttpEntity<?> getNews() {
        List<News> all = newsRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public HttpEntity<?> addNews(@RequestBody NewsDto newsDto) {
        Integer integer = newsService.addNews(newsDto);
        return ResponseEntity.ok(integer);
    }

    @PutMapping("/upload/{id}")
    public HttpEntity<?> uploadImg(@PathVariable Integer id, @RequestParam(name = "photoId") UUID photoId) {
        ApiResponse apiResponse = newsService.uploadImg(id, photoId);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editData(@PathVariable Integer id, @RequestBody NewsDto newsDto) {
        ApiResponse apiResponse = newsService.editData(id, newsDto);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteNews(@PathVariable Integer id) {
        ApiResponse apiResponse = newsService.deleteNews(id);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
