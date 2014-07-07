package org.grizz.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Grizz on 2014-07-05.
 */
public class Embed {
    private boolean plus18;
    private String type;
    private String url;

    public boolean isPlus18() {
        return plus18;
    }

    public void setPlus18(boolean plus18) {
        this.plus18 = plus18;
    }

    public EmbedType getType() {
        if(StringUtils.isEmpty(type)) {
            return null;
        }
        return EmbedType.valueOf(type.toUpperCase());
    }

    public void setType(EmbedType type) {
        if(type == null) {
            this.type = null;
        }
        this.type = type.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
