package com.github.jactorrises.persistence.orm.entity.blog;

import com.github.jactorrises.client.persistence.dto.BlogDto;
import com.github.jactorrises.persistence.orm.entity.DateTextEmbeddable;
import com.github.jactorrises.persistence.orm.entity.PersistentEntity;
import com.github.jactorrises.persistence.orm.entity.user.UserEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.hash;
import static java.util.stream.Collectors.toSet;

@Entity
@Table(name = "T_BLOG")
public class BlogEntity extends PersistentEntity {

    @Embedded @AttributeOverride(name = "dateAsText", column = @Column(name = "CREATED")) private DateTextEmbeddable created;
    @Column(name = "TITLE") private String title;
    @JoinColumn(name = "USER_ID") @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY) private UserEntity userEntity;
    @OneToMany(mappedBy = "blogEntity") private Set<BlogEntryEntity> entries = new HashSet<>();

    BlogEntity() {
        created = new DateTextEmbeddable(LocalDate.now());
    }

    private BlogEntity(BlogEntity blogEntity) {
        super(blogEntity);
        created = blogEntity.created;
        title = blogEntity.getTitle();
        userEntity = blogEntity.copyUser();
    }

    public BlogEntity(BlogDto blogDto) {
        super(blogDto);
        created = new DateTextEmbeddable(blogDto.getCreated());
        title = blogDto.getTitle();
        userEntity = new UserEntity(blogDto.getUser());

    }

    public BlogEntity copy() {
        return new BlogEntity(this);
    }

    private UserEntity copyUser() {
        return userEntity != null ? userEntity.copy() : null;
    }

    public BlogDto asDto() {
        BlogDto blogDto = addPersistentData(new BlogDto());
        blogDto.setCreated(created.fetchLocalDate());
        blogDto.setEntries(entries.stream().map(bee -> bee.asDto(blogDto)).collect(toSet()));
        blogDto.setTitle(title);
        blogDto.setUser(userEntity.asDto());

        return blogDto;
    }

    @Override public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() &&
                Objects.equals(title, ((BlogEntity) o).title) &&
                Objects.equals(userEntity, ((BlogEntity) o).userEntity);
    }

    @Override public int hashCode() {
        return hash(getTitle(), getUser());
    }

    @Override public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append(created.toString())
                .append(getTitle())
                .append(getUser())
                .toString();
    }

    public String getTitle() {
        return title;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public LocalDate getCreated() {
        return created.fetchLocalDate();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public static BlogEntityBuilder aBlog() {
        return new BlogEntityBuilder();
    }
}
