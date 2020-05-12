package com.csoftz.java.tips.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Address {
    String addressType;
    String addressLine1;
    String city;
    String stateOrProvince;
    String countryCode;
    String postalCode;
}
