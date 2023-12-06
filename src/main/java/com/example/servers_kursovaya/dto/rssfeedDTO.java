package com.example.servers_kursovaya.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class rssfeedDTO implements Serializable {


    private String link;
    private String title;


    private String description;


    private Timestamp publicationDate;


    private Timestamp updatedDate;

}
