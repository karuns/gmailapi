package gclient;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class GmailAccount {
	
    /** Application name. */
    private String APPLICATION_NAME = "Gmail API Java Quickstart";
    private String userId;
	private File dataStoreDir; /** Directory to store user credentials for this application. */
	private FileDataStoreFactory DATA_STORE_FACTORY; /** Global instance of the {@link FileDataStoreFactory}. */
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance(); /** Global instance of the JSON factory. */
	private HttpTransport HTTP_TRANSPORT; /** Global instance of the HTTP transport. */
    private final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS,GmailScopes.GMAIL_COMPOSE, GmailScopes.GMAIL_SEND,GmailScopes.MAIL_GOOGLE_COM);
    Gmail service;
    
    public GmailAccount (String userId,String appName) {
    	this.userId = userId;    	
    	this.dataStoreDir = new java.io.File(System.getProperty("user.home"), ".credentials/gmail-java_"+userId);    	
        try {
			this.HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			this.DATA_STORE_FACTORY = new FileDataStoreFactory(this.dataStoreDir);
	        this.service = getGmailService();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        APPLICATION_NAME = "Gmail API Java Quickstart new";
    	
    }
    public GmailAccount (String userId) {
    	this.userId = userId;    	
    	this.dataStoreDir = new java.io.File(System.getProperty("user.home"), ".credentials/gmail-java_"+userId);    	
        try {
			this.HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			this.DATA_STORE_FACTORY = new FileDataStoreFactory(this.dataStoreDir);
	        this.service = getGmailService();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

   
    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public  Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in = GmailAccount.class.getResourceAsStream("/client_secret_"+userId+".json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        System.out.println("Credentials saved to " + dataStoreDir.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    public  Gmail getGmailService() throws IOException {
        Credential credential = authorize();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    public void sendEmail(MimeMessage m) throws IOException, MessagingException {
    	SendEmail.sendMessage(service, userId, m);
    }
    
    public  void sendEmail(String to, String subject, String bodyText) {
    	try {
			SendEmail.sendMessage(service, userId, SendEmail.createEmail(to, userId, subject, bodyText));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public  void getEmail(String emailId) {
    	
			try {
				System.out.println(GetMessage.getMessage(service, userId, emailId).toPrettyString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

}