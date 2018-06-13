package com.github.jactor.rises.io.rest;

import com.github.jactor.rises.client.dto.GuestBookDto;
import com.github.jactor.rises.client.dto.GuestBookEntryDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

public class GuestBookRestService extends AbstractRestService {

    private final String baseUrl;

    public GuestBookRestService(RestTemplate restTemplate, String baseUrl) {
        super(restTemplate);
        this.baseUrl = baseUrl;
    }

    public GuestBookDto saveOrUpdate(GuestBookDto guestBookDto) {
        ResponseEntity<GuestBookDto> responseEntity = exchangePost(
                baseUrl + "/persist", new HttpEntity<>(guestBookDto), GuestBookDto.class
        );

        return bodyOf(responseEntity);
    }

    public GuestBookEntryDto saveOrUpdate(GuestBookEntryDto guestBookEntryDto) {
        ResponseEntity<GuestBookEntryDto> responseEntity = exchangePost(
                baseUrl + "/entry/persist", new HttpEntity<>(guestBookEntryDto), GuestBookEntryDto.class
        );

        return bodyOf(responseEntity);
    }

    public GuestBookDto fetch(Serializable id) {
        ResponseEntity<GuestBookDto> responseEntity = getForEntity(baseUrl + '/' + id, GuestBookDto.class);

        return bodyOf(responseEntity);
    }

    public GuestBookEntryDto fetchEntry(Serializable entryId) {
        ResponseEntity<GuestBookEntryDto> responseEntity = getForEntity(baseUrl + "/entry/" + entryId, GuestBookEntryDto.class);

        return bodyOf(responseEntity);
    }
}
