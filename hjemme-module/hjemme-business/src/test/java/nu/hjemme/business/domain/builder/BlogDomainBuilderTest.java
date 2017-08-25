package nu.hjemme.business.domain.builder;

import nu.hjemme.persistence.client.UserEntity;
import nu.hjemme.persistence.facade.PersistentDataService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nu.hjemme.business.domain.BlogDomain.aBlog;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("A BlogDomainBuilder")
class BlogDomainBuilderTest {

    @DisplayName("should not build a blog without a title")
    @Test void skalIkkeByggeUtenTittel() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aBlog().with(aUser()).build());
        assertThat(illegalArgumentException.getMessage()).isEqualTo(BlogDomainBuilder.THE_BLOG_MUST_HAVE_A_TITLE);
    }

    @DisplayName("should not build a blog with an empty title")
    @Test void skalIkkeByggeMedTomTittel() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aBlog().with(aUser()).withTitleAs("").build());
        assertThat(illegalArgumentException.getMessage()).isEqualTo(BlogDomainBuilder.THE_BLOG_MUST_HAVE_A_TITLE);
    }

    @DisplayName("should not build a blog without a user")
    @Test void skalIkkeByggeUtenBruker() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> aBlog().withTitleAs("the title").build());
        assertThat(illegalArgumentException.getMessage()).isEqualTo(BlogDomainBuilder.THE_BLOG_MUST_BELONG_TO_A_USER);
    }

    @DisplayName("should build a blog with a user and a title")
    @Test void skalByggeMedTittelOgBruker() {
        assertThat(aBlog().withTitleAs("title").with(aUser()).build()).isNotNull();
    }

    private UserEntity aUser() {
        return PersistentDataService.getInstance().provideInstanceFor(UserEntity.class);
    }
}