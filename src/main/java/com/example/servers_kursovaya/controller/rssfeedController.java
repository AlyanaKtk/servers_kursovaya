package com.example.servers_kursovaya.controller;


import com.example.servers_kursovaya.dto.rssfeedDTO;
import com.example.servers_kursovaya.model.rssfeed;
import com.example.servers_kursovaya.service.rssfeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//контроллер
@RequestMapping
@Controller
public class rssfeedController {
    @Autowired
    private rssfeedService rssfeedService;
    //главная страница
    @GetMapping("/")
    public String welcome() {
        return "index.html";
    }
    //фид
    //параметры: модель, настройки страниц
    @GetMapping("/feed")
    public String getFeed(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                     @RequestParam(name = "sort", required = false, defaultValue = "updated_date") String sortBy,
                                     @RequestParam(name = "direction", required = false, defaultValue = "desc") String direction)
    {
        Page<rssfeedDTO> feed = rssfeedService.getLatestRSSData(page, size, sortBy, direction);
        model.addAttribute("feed",feed); //добавление фида к модели
        int totalPages = feed.getTotalPages(); //подсчёт страниц
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers); //добавление номера страниц
        }
        return "feed.html";
    }
    //добавление новых записей
    @PostMapping("/add")
    public rssfeed addFeed(@RequestBody rssfeed feed)
    {
        return rssfeedService.addFeed(feed);
    }
    //удаление записей
    @DeleteMapping("feed/delete/{id}")
    public void deleteFeed(@PathVariable Long id)
    {
        rssfeedService.deleteFeed(id);
    }




}
