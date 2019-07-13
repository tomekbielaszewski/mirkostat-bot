package org.grizz.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Configuration {
    private WykopEndpoint wykopEndpoint;

    @Parameter(names = {"--fetch-last-x-hours", "-hours"}, description = "Fetch entries not older than given amount of hours", validateWith = PositiveInteger.class, required = true, order = 0)
    private int hoursOfHistory;

    @Getter
    @ToString
    public static class WykopEndpoint {
        @Parameter(names = {"--wykop-resource", "-resource"}, description = "Resource name from pageable endpoint in wykop.pl API documentation", required = true)
        private String resource;

        @Parameter(names = {"--wykop-method", "-method"}, description = "Method name from pageable endpoint in wykop.pl API documentation", required = true)
        private String method;
    }
}
