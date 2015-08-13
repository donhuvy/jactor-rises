package nu.hjemme.persistence.db;

import nu.hjemme.client.datatype.Country;
import nu.hjemme.client.domain.Address;
import nu.hjemme.persistence.client.AddressEntity;
import nu.hjemme.persistence.meta.AddressMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

import static java.util.Objects.hash;

/** @author Tor Egil Jacobsen */
public class AddressEntityImpl extends PersistentEntity<Long> implements AddressEntity {

    @Id
    @Column(name = AddressMetadata.ADDRESS_ID)
    // brukes av hibernate
    @SuppressWarnings("unused")
    void setAddressId(Long addressId) {
        setId(addressId);
    }

    @Column(name = AddressMetadata.COUNTRY)
    private Country country;

    @Column(name = AddressMetadata.ZIP_CODE)
    private Integer zipCode;

    @Column(name = AddressMetadata.ADDRESS_LINE_1)
    private String addressLine1;

    @Column(name = AddressMetadata.ADDRESS_LINE_2)
    private String addressLine2;

    @Column(name = AddressMetadata.ADDRESS_LINE_3)
    private String addressLine3;

    @Column(name = AddressMetadata.CITY)
    private String city;

    public AddressEntityImpl() {
    }

    /** @param address is used to create an entity */
    public AddressEntityImpl(Address address) {
        addressLine1 = address.getAddressLine1();
        addressLine2 = address.getAddressLine2();
        addressLine3 = address.getAddressLine3();
        city = address.getCity();
        country = address.getCountry();
        zipCode = address.getZipCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressEntityImpl that = (AddressEntityImpl) o;

        return Objects.equals(getAddressLine1(), that.getAddressLine1()) &&
                Objects.equals(getAddressLine2(), that.getAddressLine2()) &&
                Objects.equals(getAddressLine3(), that.getAddressLine3()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getCountry(), that.getCountry()) &&
                Objects.equals(getZipCode(), that.getZipCode());
    }

    @Override
    public int hashCode() {
        return hash(getAddressLine1(), getAddressLine2(), getAddressLine3(), getCity(), getCountry(), getZipCode());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .appendSuper(super.toString())
                .append(getAddressLine1())
                .append(getAddressLine2())
                .append(getAddressLine3())
                .append(getCity())
                .append(getCountry())
                .append(getZipCode())
                .toString();
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public Integer getZipCode() {
        return zipCode;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    @Override
    public String getAddressLine3() {
        return addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public void setCity(String city) {
        this.city = city;
    }
}