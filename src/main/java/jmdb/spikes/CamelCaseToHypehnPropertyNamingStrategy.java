package jmdb.spikes;

import org.codehaus.jackson.map.PropertyNamingStrategy;

public class CamelCaseToHypehnPropertyNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {

    PropertyNamingStrategyBase camelCaseToLowerCaseStrategy = (PropertyNamingStrategyBase) PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;

    @Override public String translate(String input) {
        return (camelCaseToLowerCaseStrategy.translate(input).replace("_", "-"));
    }
}