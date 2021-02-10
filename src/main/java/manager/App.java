package manager;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class App {

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        Policies policyManager = new Policies();
        policyManager.setPolicy(Constants.ENTERPRISE_NAME, Constants.POLICY_ID, policyManager.createPolicy());

        EnrollmentTokenClient tokenClient = new EnrollmentTokenClient();
        String token = tokenClient.createEnrollementToken(Constants.ENTERPRISE_NAME, Constants.POLICY_ID);
        System.out.println("User token: " + token);
    }
    
}
