package manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidmanagement.v1.AndroidManagement;

public class AndroidManagementClient {

    private AndroidManagementClient() {
        //empty constructer
    }

    protected static AndroidManagement getAndroidManagementClient() throws GeneralSecurityException, IOException {
        try(FileInputStream input = new FileInputStream(Constants.SERVICE_ACCOUNT)) {
            GoogleCredential credential = GoogleCredential.fromStream(input).createScoped(Collections.singleton(Constants.OAUTH_SCOPE));

            return new AndroidManagement.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
            .setApplicationName("Android Sample App").build();
        }
    }
}
