package org.grizz.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.model.Statistics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Slf4j
@Service
public class StatisticsFormatter {
    @Value("${template.dir}")
    private String directoryForTemplates;

    public String format(Statistics stats) {
        Map<String, Object> model = stats.getStats();
        model.put("Entry", Entry.class);
        model.put("EntryComment", EntryComment.class);
        return formatWith(model);
    }

    private String formatWith(Map<String, Object> model) {
        Template template = getFreemarkerTemplate("response.ftl");
        StringWriter writer = new StringWriter();
        try {
            template.process(model, writer);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }

    private Template getFreemarkerTemplate(String templateName) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        try {
            cfg.setDirectoryForTemplateLoading(getFilepath(directoryForTemplates).toFile());
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            return cfg.getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getFilepath(String filePath) {
        URL url = getClass().getClassLoader().getResource(filePath);
        if (url == null) {
            throw new RuntimeException("Problem loading file [" + filePath + "]");
        }
        log.debug("Accessing file: " + filePath);
        Path path;
        try {
            path = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
}
