package org.grizz.service;

import org.apache.http.Header;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicHeader;
import org.grizz.service.post.WykopUrlSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
public class HTTPRequestService {
    @Autowired
    private WykopUrlSigner signer;
	
	public String sendGet(String url) {
		String responseContent = "";
        Request getRequest = Request.Get(url);

        getRequest.addHeader(new BasicHeader("apisign", signer.sign(url, Collections.EMPTY_MAP)));

        try {
            responseContent = getRequest.execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }

		return responseContent;
	}
	
	public String sendPost(String url, Map<String, String> postContent, Map<String, String> headers) {
        String responseContent = "";
        Form form = Form.form();
        List<Header> headersList = new ArrayList<>();

        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
            headersList.add(new BasicHeader(headerEntry.getKey(), headerEntry.getValue()));
        }

        for (Map.Entry<String, String> entry : postContent.entrySet()) {
            form.add(entry.getKey(), entry.getValue());
        }

        Request postRequest = Request.Post(url);

        postRequest.addHeader(new BasicHeader("apisign", signer.sign(url, postContent)));

        for (Header header : headersList) {
            postRequest.addHeader(header);
        }

        try {
            responseContent = postRequest
                    .bodyForm(form.build())
                    .execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseContent;
	}
}
