package com.bank.app.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// IMPORTANT: as of java 16 we have "record", they act as carrier of immutable data
// if you have java 15 or below, you should use final fields, all args constructor and only getter methods
@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDto(String message, Map<String, String> contactDetails,
                                  List<String> onCallSupport) {
}
