package com.lingea.reindexer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ReindexerUtils {

    /**
     * Function for sending a POST with a document from document storage to reindex the document.
     * @param url - the indexing service URL.
     * @param meta - metadata mimicking the original indexer.
     * @param bytes - the document in binary form.
     * @throws Exception
     */
    public static void sendMultipartFormData(String url, Map<String, String> meta, byte[] bytes) throws Exception {
        String boundary = "===" + System.currentTimeMillis() + "===";
        String charset = "UTF-8";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        OutputStream output = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);

        for (String key : meta.keySet()) {
            writer.append("--" + boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"" + key + "\"").append("\r\n");
            writer.append("\r\n").append(meta.get(key)).append("\r\n");
        }

        // add byte array field
        writer.append("--" + boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"data\"; filename=\"data.bin\"").append("\r\n");
        writer.append("Content-Type: application/octet-stream").append("\r\n");
        writer.append("Content-Transfer-Encoding: binary").append("\r\n").append("\r\n");
        output.write(bytes);
        writer.append("\r\n").flush();

        writer.append("--" + boundary + "--").append("\r\n");
        writer.close();

        // int responseCode = connection.getResponseCode();
        // InputStream responseStream = connection.getInputStream();
    }

}