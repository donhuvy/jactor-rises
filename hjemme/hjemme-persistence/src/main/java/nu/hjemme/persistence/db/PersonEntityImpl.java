package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Name;
import nu.hjemme.client.domain.Person;
import nu.hjemme.persistence.client.PersonEntity;
import nu.hjemme.persistence.meta.PersonMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class PersonEntityImpl extends PersistentEntity<Long> implements PersonEntity {

    @Id
    @Column(name = PersonMetadata.PERSON_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    void setPersonId(Long personId) {
        setId(personId);
    }

    @Column(name = PersonMetadata.FIRST_NAME)
    // Describe type
    private Name firstName;

    @Column(name = PersonMetadata.LAST_NAME)
    // Describe type
    private Name lastName;

    @OneToMany(mappedBy = PersonMetadata.ADDRESS)
    private AddressEntityImpl address;

    public PersonEntityImpl() {
    }

    /** @param person will be used to create an entity */
    public PersonEntityImpl(Person person) {
        address = person.getAddress() != null ? new AddressEntityImpl(person.getAddress()) : null;
        firstName = person.getFirstName();
        lastName = person.getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonEntityImpl personEntity = (PersonEntityImpl) o;

        return Objects.equals(getFirstName(), personEntity.getFirstName()) && Objects.equals(getLastName(), personEntity.getLastName()) && Objects.equals(getAddress(), personEntity.getAddress());
    }

    @Override
    public int hashCode() {
        return hash(getAddress(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getFirstName())
                .append(getLastName())
                .append(getAddress())
                .toString();
    }

    @Override
    public AddressEntityImpl getAddress() {
        return address;
    }

    @Override
    public Name getFirstName() {
        return firstName;
    }

    @Override
    public Name getLastName() {
        return lastName;
    }

    public void setFirstName(Name firstName) {
        this.firstName = firstName;
    }

    public void setLastName(Name lastName) {
        this.lastName = lastName;
    }

    public void setAddress(AddressEntityImpl address) {
        this.address = address;
    }
}