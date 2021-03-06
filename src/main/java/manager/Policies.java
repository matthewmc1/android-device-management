package manager;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.services.androidmanagement.v1.model.ApplicationPolicy;
import com.google.api.services.androidmanagement.v1.model.PermissionGrant;
import com.google.api.services.androidmanagement.v1.model.PersistentPreferredActivity;
import com.google.api.services.androidmanagement.v1.model.Policy;

public class Policies {

        protected Policy createPolicy() {
                List<String> categories = new ArrayList<>();
                categories.add("android.intent.category.HOME");
                categories.add("android.intent.category.DEFAULT");

                return new Policy().setApplications(Collections.singletonList(new ApplicationPolicy()
                                .setPackageName(AppConstants.CHROME_APP)
                                .setPermissionGrants(Collections.singletonList(new PermissionGrant()
                                                .setPermission("android.permission.ACCESS_FINE_LOCATION").setPolicy("DENY")))
                                .setInstallType("FORCE_INSTALLED").setDefaultPermissionPolicy("GRANT")
                                .setLockTaskAllowed(true)))
                                .setPersistentPreferredActivities(
                                                Collections.singletonList(new PersistentPreferredActivity()
                                                                .setReceiverActivity(AppConstants.CHROME_APP)
                                                                .setActions(Collections.singletonList(
                                                                                "android.intent.action.MAIN"))
                                                                .setCategories(categories)))
                                .setKeyguardDisabled(true).setStatusBarDisabled(true)
                                .setLocationMode("LOCATION_ENFORCED");
        }

        protected void setPolicy(String enterpriseName, String policyId, Policy policy)
                        throws IOException, GeneralSecurityException {
                String name = String.format("%s/policies/%s", enterpriseName, policyId);

                AndroidManagementClient.getAndroidManagementClient().enterprises().policies().patch(name, policy)
                                .execute();
        }
}
