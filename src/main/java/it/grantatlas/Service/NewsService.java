package it.grantatlas.Service;

import it.grantatlas.Entity.Attachment;
import it.grantatlas.Entity.AttachmentContent;
import it.grantatlas.Entity.News;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.NewsDto;
import it.grantatlas.Repository.AttachmentContentRepository;
import it.grantatlas.Repository.AttachmentRepository;
import it.grantatlas.Repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    private final AttachmentContentRepository attachmentContentRepository;

    private final AttachmentRepository attachmentRepository;


    public Integer addNews(NewsDto newsDto){
        News build = News.builder()
                .descriptionUz(newsDto.descriptionUz())
                .descriptionRu(newsDto.descriptionRu())
                .descriptionEng(newsDto.descriptionEng())
                .descriptionTurk(newsDto.descriptionTurk())
                .build();
        build.setNameUz(newsDto.nameUz());
        build.setNameRu(newsDto.nameRu());
        build.setNameEng(newsDto.nameEng());
        build.setNameTurk(newsDto.nameTurk());
        News save = newsRepository.save(build);
        return save.getId();
    }

    public ApiResponse uploadImg(Integer id, UUID img){
        News news = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("yangilik topilmadi"));
        news.setImg(img);
        newsRepository.save(news);
        return new ApiResponse("Yangilik saqlandi", true, 201);
    }

    public ApiResponse editData(Integer id, NewsDto newsDto){
        News news = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Yangilik ma'lumotlar bazasida topilmadi"));
        news.setNameUz(newsDto.nameUz().length() != 0 ? newsDto.nameUz() : news.getNameUz());
        news.setNameRu(newsDto.nameRu().length() != 0 ? newsDto.nameRu() : news.getNameRu());
        news.setNameEng(newsDto.nameEng().length() != 0 ? newsDto.nameEng() : news.getNameEng());
        news.setNameTurk(newsDto.nameTurk().length() != 0 ? newsDto.nameTurk() : news.getNameTurk());
        news.setDescriptionUz(newsDto.descriptionUz().length() != 0  ? newsDto.descriptionUz() : news.getDescriptionUz());
        news.setDescriptionEng(newsDto.descriptionEng().length() != 0  ? newsDto.descriptionEng() : news.getDescriptionEng());
        news.setDescriptionRu(newsDto.descriptionRu().length() != 0  ? newsDto.descriptionRu() : news.getDescriptionRu());
        news.setDescriptionTurk(newsDto.descriptionTurk().length() != 0  ? newsDto.descriptionTurk() : news.getDescriptionTurk());
        newsRepository.save(news);
        return new ApiResponse("Ma'lumotlar taxrirlandi", true, 200);
    }

    public ApiResponse deleteNews(Integer id){
        News news = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Siz tanlagan yangilikni topib bo'lmadi"));
        if (news.getImg() != null){
            Attachment attachment = attachmentRepository.findById(news.getImg()).orElseThrow(() -> new ResourceNotFoundException("rasmni olib tashlab bo'lmadi"));
            AttachmentContent byAttachmentId = attachmentContentRepository.findByAttachmentId(attachment.getId());
            attachmentContentRepository.delete(byAttachmentId);
            attachmentRepository.delete(attachment);
        }
        newsRepository.delete(news);
        return new ApiResponse("Yanglik olib tashlandi", true, 200);
    }

}
