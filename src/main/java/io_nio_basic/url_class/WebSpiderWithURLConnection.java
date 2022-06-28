package io_nio_basic.url_class;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebSpiderWithURLConnection {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.exit(1);
        }

        URL url = null;
        try {
            url = new URL(args[0]);
        } catch (MalformedURLException e) {
            System.out.println("잘못된 URL형식 입니다.");
            System.out.println(e);
            System.exit(1);
        }

        FileOutputStream fos = null;
        try {
            URLConnection urlcon = url.openConnection();
            String contentType = urlcon.getContentType();
            long d1 = urlcon.getDate();
            Date d = new Date(d1);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String sdate = format.format(d);

            System.out.println("contentType : " + contentType);
            System.out.println("읽은 시간 : " + sdate);

            InputStream in = url.openStream();
            fos = new FileOutputStream(args[1]);

            byte[] buffer = new byte[512];
            int readCount = 0;

            System.out.println("읽기 시작합니다.");
            while ((readCount = in.read(buffer)) != -1) {
                fos.write(buffer, 0, readCount);
            }
            System.out.println(args[1] + "파일에 모두 저장했습니다.");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (fos!=null) fos.close();
            } catch (Exception e){}
        }
    }

}

