package com.github.jactor.rises.model.domain.blog;

import com.github.jactor.rises.commons.dto.BlogDto;
import com.github.jactor.rises.commons.dto.UserDto;
import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.MissingFields;
import com.github.jactor.rises.model.domain.user.UserBuilder;

import java.util.Optional;

public final class BlogBuilder extends AbstractBuilder<BlogDomain> {
    private final BlogDto blogDto = new BlogDto();

    BlogBuilder() {
        super(BlogBuilder::validateInstance);
    }

    BlogBuilder withTitle(String title) {
        blogDto.setTitle(title);
        return this;
    }

    public BlogBuilder with(UserDto userDto) {
        blogDto.setUser(userDto);
        return this;
    }

    public BlogBuilder with(UserBuilder userBuilder) {
        return with(userBuilder.build().getDto());
    }

    @Override protected BlogDomain buildBean() {
        return new BlogDomain(blogDto);
    }

    private static Optional<MissingFields> validateInstance(BlogDomain blogDomain, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenBlank("title", blogDomain.getTitle());
        missingFields.addInvalidFieldWhenNoValue("user", blogDomain.getUser());

        return missingFields.presentWhenFieldsAreMissing();
    }

    public static BlogDomain build(BlogDto blogDto) {
        return new BlogDomain(blogDto);
    }
}
