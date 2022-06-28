package io_nio_basic.url_class;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DaumSearchGetMethod {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.exit(1);
        }

        try {
            String keyword = URLEncoder.encode(args[0]);
            String query = "?nil_suggest=btn&w=tot&DA=SBC&q=%08손현호";
            String u = "https://search.daum.net/search";
            System.out.println(u + query);
            URL url = new URL(u);
            URLConnection connection = url.openConnection();
            HttpURLConnection hurlc = (HttpURLConnection) connection;
            hurlc.setRequestMethod("GET");
            hurlc.setDoOutput(true);
            hurlc.setDoInput(false);
            hurlc.setDefaultUseCaches(false);

            PrintWriter out = new PrintWriter(hurlc.getOutputStream());
            out.println(query);
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(hurlc.getInputStream()));
            PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
            String inputLine = null;

            while ((inputLine = in.readLine()) != null) {
                pw.println(inputLine);
            }
            in.close();
            pw.close();
            System.out.println("검색 결과가 " + args[1] + "파일에 있습니다.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
