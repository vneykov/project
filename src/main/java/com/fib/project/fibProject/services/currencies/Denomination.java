package com.fib.project.fibProject.services.currencies;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BGN.class, name = "BGN"),
        @JsonSubTypes.Type(value = EUR.class, name = "EUR")
})
public interface Denomination {

    int getDenomination();
    int getCount();
    void setCount(int count);

    void setDenomination(int denomination);
}
