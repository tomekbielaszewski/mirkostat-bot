package org.grizz.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "wykop.key")
public class WykopKeys {
    private String publicc;
    private String secret;
    private String user;
}
