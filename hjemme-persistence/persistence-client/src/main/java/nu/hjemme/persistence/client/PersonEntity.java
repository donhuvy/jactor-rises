package nu.hjemme.persistence.client;

import nu.hjemme.client.domain.Person;

public interface PersonEntity extends Person {
    @Override UserEntity getUser();

    void setAddressEntity(AddressEntity addressEntity);

    void setDescription(String description);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setUserEntity(UserEntity userEntity);
}