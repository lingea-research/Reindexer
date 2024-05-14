package com.lingea.reindexer;

import cz.lingea.documentstorage.DocumentStorage;
import cz.lingea.documentstorage.exception.DatabaseException.RecordNotFound;

import java.io.IOException;
import java.util.Map;

/**
 * A class for reseting the index from the document storage.
 */
public class Reindexer {

    private DocumentStorage documentStorage;
    private String indexingServiceUrl;

    /** Basic constructor. */
    public Reindexer(String dataPath, String indexPath, String metaPath, String indexingServiceUrl) {
        try {
            this.documentStorage = DocumentStorage.getInstance(dataPath, indexPath, metaPath);
        } catch (Exception ex) {
            this.documentStorage = null;
            System.out.println("ERROR GETTING THE DOCUMENT STORAGE INSTANCE - " + ex.getMessage());
        }
    }

    /**
     * A function for resetting the indexing service index from the document storage. Uses the API.
     */
    public void reindexAll() {
        Map<String, Map<String, String>> metadata = DocumentStorage.getMeta();
        for (String docId : metadata.keySet()) {
            try {
                this.reindex(docId, metadata.get(docId));
            } catch (IOException ex) {
                System.out.println("IOEXCEPTION DURING REINDEXING - " + ex.getMessage());
            } catch (RecordNotFound ex) {
                System.out.println("RECORD NOT FOUND EXCEPTION DURING REINDEXING - " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("ERROR SENDING THE POST REQUEST DURING REINDEXING - " + ex.getMessage());
            }
        }
    }

    /** Function for reindexing one document by a docId and its metadata. */
    private void reindex(String docId, Map<String, String> meta) throws IOException, RecordNotFound, Exception {
        long id = Long.parseLong(docId);
        byte[] bytes = DocumentStorage.getDocument(id);
        ReindexerUtils.sendMultipartFormData(this.indexingServiceUrl, meta, bytes);
    }
}
