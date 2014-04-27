package nu.hjemme.business.domain;

import nu.hjemme.business.persistence.mutable.MutableProfile;
import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Address;

/** @author Tor Egil Jacobsen */
public class Profile implements nu.hjemme.client.domain.Profile {
    private final MutableProfile mutableProfile;

    public Profile(MutableProfile mutableProfile) {
        this.mutableProfile = mutableProfile;
    }

    @Override
    public String getDescription() {
        return mutableProfile.getDescription();
    }

    @Override
    public nu.hjemme.client.domain.User getUser() {
        return new User(mutableProfile.getMutableUser());
    }

    @Override
    public Name getFirstName() {
        return mutableProfile.getFirstName();
    }

    @Override
    public Name getLastName() {
        return mutableProfile.getLastName();
    }

    @Override
    public Address getAddress() {
        return mutableProfile.getAddress();
    }

    public MutableProfile getMutableProfile() {
        return mutableProfile;
    }
}