package io_nio_basic.url_class;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }

        String decodeStr = URLDecoder.decode(args[0]);
        System.out.println(args[0] + " === > " + decodeStr);
    }

}
