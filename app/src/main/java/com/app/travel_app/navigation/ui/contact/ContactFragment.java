package com.app.travel_app.navigation.ui.contact;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.travel_app.R;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ContactFragment extends Fragment {

    EditText txtEmail, txtMessage;
    Button btnSend;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        //txtEmail = view.findViewById(R.id.insert_email);
        txtMessage = view.findViewById(R.id.text_message_email);
        btnSend = view.findViewById(R.id.send_email_button);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = "lismlism1234@gmail.com";
                final String password = "why_you_doing_here";
                String messageToSend = txtMessage.getText().toString();
                Properties properties = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");

                Session session = Session.getInstance(properties,
                        new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username,password);
                    }
                        });
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("davidegri72@gmail.com"));
                    message.setSubject("Sending email without opening gmail apps");
                    message.setText(messageToSend);
                    Transport.send(message);
                    Toast.makeText(getContext(), "email send succesfully", Toast.LENGTH_LONG).show();

                }catch (MessagingException e){
                    throw new RuntimeException(e);
                }
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        return view;
    }
}
