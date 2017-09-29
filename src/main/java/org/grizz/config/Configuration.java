package org.grizz.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Configuration {
    @Parameter(names = {"--fetch-last-x-hours", "-fetch"}, description = "Fetch entries not older than given amount of hours", validateWith = PositiveInteger.class, required = true, order = 0)
    private int hoursOfHistory;
}
