package com.example.servers_kursovaya.repository;


import com.example.servers_kursovaya.model.rssfeed;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface rssfeedRepository extends JpaRepository<rssfeed, Long> {
    @Transactional(readOnly = true)
    @Cacheable("rss_feeds")
    Page<rssfeed> findAll(Pageable pageable) throws DataAccessException;
}
