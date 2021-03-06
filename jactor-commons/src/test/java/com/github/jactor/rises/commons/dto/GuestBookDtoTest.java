package com.github.jactor.rises.commons.dto;

import com.github.jactor.rises.commons.dto.GuestBookDto;
import com.github.jactor.rises.commons.dto.GuestBookEntryDto;
import com.github.jactor.rises.commons.dto.PersistentDto;
import com.github.jactor.rises.commons.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("A GuestBookDto")
class GuestBookDtoTest {

    @DisplayName("should have a copy constructor")
    @Test
    void shouldHaveCopyConstructor() {
        GuestBookDto guestBookDto = new GuestBookDto();
        guestBookDto.setEntries(new HashSet<>(singletonList(new GuestBookEntryDto())));
        guestBookDto.setTitle("title");
        guestBookDto.setUser(new UserDto());

        GuestBookDto copied = new GuestBookDto(guestBookDto);

        assertAll(
                () -> assertThat(copied.getEntries()).as("entries").isEqualTo(guestBookDto.getEntries()),
                () -> assertThat(copied.getTitle()).as("title").isEqualTo(guestBookDto.getTitle()),
                () -> assertThat(copied.getUser()).as("title").isEqualTo(guestBookDto.getUser())
        );
    }

    @DisplayName("should give values to PersistentDto")
    @Test
    void shouldGiveValuesToPersistentDto() {
        GuestBookDto persistentDto = new GuestBookDto();
        persistentDto.setCreatedBy("jactor");
        persistentDto.setCreationTime(LocalDateTime.now());
        persistentDto.setId(1L);
        persistentDto.setUpdatedBy("tip");
        persistentDto.setUpdatedTime(LocalDateTime.now());

        PersistentDto copied = new GuestBookDto(persistentDto);

        assertAll(
                () -> assertThat(copied.getCreatedBy()).as("created by").isEqualTo(persistentDto.getCreatedBy()),
                () -> assertThat(copied.getCreationTime()).as("creation time").isEqualTo(persistentDto.getCreationTime()),
                () -> assertThat(copied.getId()).as("id").isEqualTo(persistentDto.getId()),
                () -> assertThat(copied.getUpdatedBy()).as("updated by").isEqualTo(persistentDto.getUpdatedBy()),
                () -> assertThat(copied.getUpdatedTime()).as("updated time").isEqualTo(persistentDto.getUpdatedTime())
        );
    }
}
