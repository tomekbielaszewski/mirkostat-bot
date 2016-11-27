package org.grizz.model;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class Embed {
    private boolean plus18;
    private String type;
    private String url;
}
