package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

import com.google.api.services.androidmanagement.v1.model.Enterprise;
import com.google.api.services.androidmanagement.v1.model.SignupUrl;

public class EnterpriseManagement {

    protected SignupUrl createSignupUrl() throws IOException, GeneralSecurityException {
        return AndroidManagementClient.getAndroidManagementClient().signupUrls().create().setProjectId(Constants.PROJECT_ID).setCallbackUrl("https://localhost:9999")
        .execute();
    }

    protected String createEnterprise() throws IOException, GeneralSecurityException {
        SignupUrl url = createSignupUrl();
        System.out.println(url.getUrl());

        String enterpriseToken = new BufferedReader(new InputStreamReader(System.in)).readLine();

        return AndroidManagementClient.getAndroidManagementClient().enterprises().create(new Enterprise()).setProjectId(Constants.PROJECT_ID)
        .setSignupUrlName(url.getName()).setEnterpriseToken(enterpriseToken).execute().getName();
    }
}
