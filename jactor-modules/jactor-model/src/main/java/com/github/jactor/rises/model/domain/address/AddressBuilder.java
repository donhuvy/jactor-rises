package com.github.jactor.rises.model.domain.address;

import com.github.jactor.rises.client.dto.AddressDto;
import com.github.jactor.rises.commons.builder.AbstractBuilder;
import com.github.jactor.rises.commons.builder.MissingFields;

import java.util.Optional;

public final class AddressBuilder extends AbstractBuilder<AddressDomain> {
    private final AddressDto addressDto = new AddressDto();

    AddressBuilder() {
        super(AddressBuilder::validInstance);
    }

    public AddressBuilder withCity(String city) {
        addressDto.setCity(city);
        return this;
    }

    public AddressBuilder withCountry(String country) {
        addressDto.setCountry(country);
        return this;
    }

    public AddressBuilder withAddressLine1(String addressLine1) {
        addressDto.setAddressLine1(addressLine1);
        return this;
    }

    public AddressBuilder withAddressLine2(String addressLine2) {
        addressDto.setAddressLine2(addressLine2);
        return this;
    }

    AddressBuilder withAddressLine3(@SuppressWarnings("SameParameterValue") String addressLine3) {
        addressDto.setAddressLine3(addressLine3);
        return this;
    }

    public AddressBuilder withZipCode(Integer zipCode) {
        addressDto.setZipCode(zipCode);
        return this;
    }

    @Override protected AddressDomain buildBean() {
        return new AddressDomain(addressDto);
    }

    private static Optional<MissingFields> validInstance(AddressDomain addressDomain, MissingFields missingFields) {
        missingFields.addInvalidFieldWhenBlank("addressLine1", addressDomain.getAddressLine1());
        missingFields.addInvalidFieldWhenNoValue("country", addressDomain.getCountry());
        missingFields.addInvalidFieldWhenNoValue("zipCode", addressDomain.getZipCode());

        return missingFields.presentWhenFieldsAreMissing();
    }

    public static AddressDomain build(AddressDto addressDto) {
        return new AddressDomain(addressDto);
    }
}
