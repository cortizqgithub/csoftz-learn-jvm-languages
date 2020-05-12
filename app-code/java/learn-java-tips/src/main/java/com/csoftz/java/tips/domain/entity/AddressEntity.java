package com.csoftz.java.tips.domain.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressEntity {
    String addressType;
    String addressLine1;
    String city;
    String stateOrProvince;
    String countryCode;
    String postalCode;
}
