package org.grizz.printer;

import org.grizz.output.Output;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class SignPrinter implements StatPrinter {
    @Override
    public void print(Output output) {
        output.append("by @Grizwold");
    }
}
