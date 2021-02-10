package manager;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.androidmanagement.v1.model.EnrollmentToken;

public class EnrollmentTokenClient {

    protected String createEnrollementToken(String enterpriseName, String policyId)
            throws IOException, GeneralSecurityException {
        EnrollmentToken token = new EnrollmentToken().setPolicyName(policyId).setDuration("86400s");

        return AndroidManagementClient.getAndroidManagementClient().enterprises().enrollmentTokens()
                .create(enterpriseName, token).execute().getValue();

    }

}
