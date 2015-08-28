package nu.hjemme.client.domain.menu;

import java.util.List;

/** A chosen menu item. */
public interface ChosenMenuItem extends MenuItem {

    @Override List<ChosenMenuItem> getChildren();

    boolean isChosen();

    boolean isChildChosen();

    MenuItem getMenuItem();
}
