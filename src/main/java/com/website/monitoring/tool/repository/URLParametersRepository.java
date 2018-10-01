package com.website.monitoring.tool.repository;

import com.website.monitoring.tool.entity.URLParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("URLParametersRepository")
public interface URLParametersRepository extends JpaRepository<URLParameters, Long> {

    @Query(value = "SELECT url FROM url_parameters", nativeQuery = true)
    List<String> findAllURLs();

    @Query(value = "SELECT * FROM url_parameters params WHERE params.url = :url", nativeQuery = true)
    URLParameters findParamsByURL(@Param("url") String url);
}
