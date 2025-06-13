package com.netflixClone.NetflixClone.service;

import com.netflixClone.NetflixClone.config.TmdbConfig;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class TmdbService {


    private final RestTemplate rt;
    private final TmdbConfig cfg;

    public String fetchNowPlaying() {
        String url = cfg.getApiUrl() + "/movie/now_playing?api_key=" + cfg.getApiKey() + "&language=es-MX";
        return rt.getForObject(url, String.class);
    }

    public String fetchPopular() {
        String url = cfg.getApiUrl() + "/movie/popular?api_key=" + cfg.getApiKey() + "&language=es-MX";
        return rt.getForObject(url, String.class);
    }

    public String fetchTopRated() {
        String url = cfg.getApiUrl() + "/movie/top_rated?api_key=" + cfg.getApiKey() + "&language=es-MX";
        return rt.getForObject(url, String.class);
    }
}
