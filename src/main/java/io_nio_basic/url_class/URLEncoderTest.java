package io_nio_basic.url_class;

import java.net.URLEncoder;

public class URLEncoderTest {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }

        String encodeStr = URLEncoder.encode(args[0]);
        System.out.println(args[0] + " === > " + encodeStr);
    }
}
