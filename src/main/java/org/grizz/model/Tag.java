package org.grizz.model;

import lombok.Data;
import lombok.experimental.Builder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
@Builder
public class Tag implements Identifable {
    private String name;
	private Long id;
}
