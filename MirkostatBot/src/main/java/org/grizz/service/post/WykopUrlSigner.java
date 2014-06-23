package org.grizz.service.post;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Grizz on 2014-06-22.
 */
public class WykopUrlSigner {
    private final String SECRET_KEY;

    public WykopUrlSigner(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public String sign(String url, Map<String, String> postContent) {
        StringBuffer postParams = new StringBuffer();
        List<String> postKeysList = new ArrayList<>();
        postKeysList.addAll(postContent.keySet());
        Collections.sort(postKeysList);

        buildPostParamsString(postContent, postKeysList, postParams);
        if(postParams.length() > 0) {
            removeLastComma(postParams);
        }

        return DigestUtils.md5Hex(SECRET_KEY + url + postParams.toString());
    }

    private void removeLastComma(StringBuffer postParams) {
        postParams.setLength(postParams.length() - 1);
    }

    private void buildPostParamsString(Map<String, String> postContent, List<String> postKeysList, StringBuffer postParams) {
        for (String key : postKeysList) {
            postParams.append(postContent.get(key));
            postParams.append(",");
        }
    }
}
