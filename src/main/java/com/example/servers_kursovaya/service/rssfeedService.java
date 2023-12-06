package com.example.servers_kursovaya.service;

import com.example.servers_kursovaya.dto.rssfeedDTO;
import com.example.servers_kursovaya.model.rssfeed;
import com.example.servers_kursovaya.repository.rssfeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class rssfeedService {
    private final rssfeedRepository rssfeedRepos;
    //конструктор
    @Autowired
    public rssfeedService(rssfeedRepository rssfeedRepos)
    {
        this.rssfeedRepos = rssfeedRepos;
    }
    //добавление фида
    public rssfeed addFeed(rssfeed feed)
    {
        return rssfeedRepos.save(feed);
    }
    //добавление нескольких фидов
    public List<rssfeed> addRSSFeeds(List<rssfeed> rssFeeds) {
        return (List<rssfeed>) rssfeedRepos.saveAll(rssFeeds);
    }

    //вызов последних фидов в виде страниц
    public Page<rssfeed> getLatestRSSFeeds(Integer page, Integer size, String sortBy, String direction) {
        String sort = getSortByValue(sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        if (direction.contentEquals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        }
        return rssfeedRepos.findAll(pageable);
    }
    //возврат данных из фидов
    public Page<rssfeedDTO> getLatestRSSData(Integer page, Integer size, String sortBy, String direction) {
        return toPageObjectDto(getLatestRSSFeeds(page, size, sortBy, direction));
    }
    //обновление фидов
    public rssfeed updateRSSFeed(rssfeed updateRSSFeed) {
        return rssfeedRepos.save(updateRSSFeed);
    }
    //конвертация в дто
    public Page<rssfeedDTO> toPageObjectDto(Page<rssfeed> rssFeeds) {
        return rssFeeds.map(this::convertToObjectDto);
    }

    //сохранить фид в дто
    private rssfeedDTO convertToObjectDto(rssfeed rssFeed) {
        rssfeedDTO dto = new rssfeedDTO();
        dto.setLink(rssFeed.getLink());
        dto.setTitle(rssFeed.getTitle());
        dto.setDescription(rssFeed.getDescription());
        dto.setPublicationDate(rssFeed.getPublicationDate());
        dto.setUpdatedDate(rssFeed.getUpdatedDate());
        return dto;
    }
    //сортировка
    public String getSortByValue(String sortBy) {
        switch (sortBy) {
            case "id":  return "id";
            case "link":  return "link";
            case "title":  return "title";
            case "description":  return "description";
            case "publication_date":  return "publicationDate";
            default: return "updatedDate";
        }
    }

}
