package com.example.challenge.question;

import com.example.challenge.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getList () {
        return this.articleRepository.findAll();
    }

    public Article getArticle (Integer id) {
        Optional<Article> articleOptional = this.articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            return articleOptional.get();
        } else {
            throw new DataNotFoundException("article not found");
        }
    }

    public void create (String subject, String content) {

        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }

    public void modify (Article article, String subject, String content) {
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }

    public void delete (Article article) {
        this.articleRepository.delete(article);
    }
}
