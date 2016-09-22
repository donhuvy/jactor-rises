package nu.hjemme.web.menu;

import com.github.jactorrises.matcher.MatchBuilder;
import com.github.jactorrises.matcher.TypeSafeBuildMatcher;
import nu.hjemme.client.datatype.Name;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.jactorrises.matcher.LabelMatcher.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MenuItemCacheTest {
    private MenuItemCache testMenuItemCache;
    private MenuTarget etStedPaHovedmenyen;
    private MenuTarget etAnnetStedPaHovedmenyen;

    @Before public void initForTesting() {
        testMenuItemCache = new MenuItemCache();
    }

    @Before public void initEtStedPaHovedmenyen() {
        etStedPaHovedmenyen = new MenuTarget(new MenuItemTarget("somewhere"), new Name("main.menu"));
    }

    @Before public void initEtAnnetStedPaHovedmenyen() {
        etAnnetStedPaHovedmenyen = new MenuTarget(new MenuItemTarget("somewhere else"), new Name("main.menu"));
    }

    @Test public void skalIkkeHaMenuTargetCachetPaInstansSomIkkeInneholderCache() {
        assertThat(testMenuItemCache.isCached(etStedPaHovedmenyen), is(equalTo(false), "chosen item cached"));
    }

    @Test public void skalIkkeHaMenuTargetCacheNarEtAnnetMenuTargetErCachet() {
        testMenuItemCache.cache(etStedPaHovedmenyen, new ArrayList<>());
        assertThat(testMenuItemCache.isCached(etAnnetStedPaHovedmenyen), is(equalTo(false), "not chosen menu cached"));
    }

    @Test public void skalHaMenuTargetCachetNarNarMenuTargetSomBesOmErCachet() {
        testMenuItemCache.cache(etStedPaHovedmenyen, new ArrayList<>());
        assertThat(testMenuItemCache.isCached(etStedPaHovedmenyen), is(equalTo(true), "chosen from menu"));
    }

    @Test public void skalCacheListeAvMenuItemsBasertPaMenuTarget() {
        assertThat(testMenuItemCache, new TypeSafeBuildMatcher<MenuItemCache>("Caching basert pa menu item target") {
            @Override public MatchBuilder matches(MenuItemCache menuItemCache, MatchBuilder matchBuilder) {
                @SuppressWarnings("unchecked") List<MenuItem> eiListeAvMenuItems = mock(List.class);
                @SuppressWarnings("unchecked") List<MenuItem> eiAnnenListeAvMenuItems = mock(List.class);

                testMenuItemCache.cache(etStedPaHovedmenyen, eiListeAvMenuItems);
                testMenuItemCache.cache(etAnnetStedPaHovedmenyen, eiAnnenListeAvMenuItems);

                return matchBuilder
                        .matches(menuItemCache.fetchBy(etStedPaHovedmenyen), is(equalTo(eiListeAvMenuItems), "cache av " + etStedPaHovedmenyen))
                        .matches(menuItemCache.fetchBy(etAnnetStedPaHovedmenyen), is(equalTo(eiAnnenListeAvMenuItems), "cache av " + etAnnetStedPaHovedmenyen));

            }
        });
    }

}
