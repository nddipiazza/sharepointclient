package com.microsoft.sharepoint.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.httpclient.auth.AuthPolicy;

public class AxisNtlmAuthUtil {

  public static void authenticateStub(org.apache.axis2.client.Stub stub, String username, String password, String domain, String host) throws AxisFault {
    HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
    auth.setUsername(username);
    auth.setPassword(password);
    auth.setDomain(domain);
    auth.setPreemptiveAuthentication(true);
    auth.setHost(host);
    
    AuthPolicy.registerAuthScheme(AuthPolicy.NTLM, com.microsoft.sharepoint.auth.JCIFSNTLMScheme.class);
    
    List<String> authPrefs = new ArrayList<>(1);
    authPrefs.add(AuthPolicy.NTLM);
    auth.setAuthSchemes(authPrefs);

    stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
  }
}
