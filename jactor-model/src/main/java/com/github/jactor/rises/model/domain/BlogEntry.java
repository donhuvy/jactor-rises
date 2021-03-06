package com.github.jactor.rises.model.domain;

import com.github.jactor.rises.commons.datatype.Name;

import java.time.LocalDateTime;

public interface BlogEntry extends Persistent {

    Blog getBlog();

    /** @return creation time of an entry */
    LocalDateTime getCreatedTime();

    /** @return the actual entry */
    String getEntry();

    /** @return the creator which is the originator of the entry */
    Name getCreatorName();
}
