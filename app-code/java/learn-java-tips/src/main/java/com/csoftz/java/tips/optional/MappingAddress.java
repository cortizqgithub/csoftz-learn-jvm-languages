package com.csoftz.java.tips.optional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.csoftz.java.tips.domain.Address;
import com.csoftz.java.tips.domain.entity.AddressEntity;

public class MappingAddress {
    public static void main(String[] args) {
        List<Address> addresses =
            Arrays.asList(
                Address.builder()
                    .addressType("AdType1")
                    .addressLine1("AdLine11")
                    .city("InfoCity1")
                    .build(),
                Address.builder()
                    .addressType("AdType2")
                    .addressLine1("AdLine2")
                    .city("InfoCity2")
                    .build()
            );
        //addresses = null;
        List<AddressEntity> addressEntities =
            Optional
                .ofNullable(addresses)
                .map(Collection::stream)
                .map(addressStream -> addressStream
                    .map(address -> AddressEntity
                        .builder()
                        .addressType(address.getAddressType())
                        .addressLine1(address.getAddressLine1())
                        .city(address.getCity())
                        .stateOrProvince(address.getStateOrProvince())
                        .countryCode(address.getCountryCode())
                        .postalCode(address.getPostalCode())
                        .build())
                    .collect(Collectors.toList()))
                .orElse(null);

        Optional
            .ofNullable(addressEntities)
            .ifPresent(System.out::println);
    }
}
