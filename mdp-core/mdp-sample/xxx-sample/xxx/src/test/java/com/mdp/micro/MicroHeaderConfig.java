package com.mdp.micro;

import com.mdp.micro.client.header.MicroHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.function.Consumer;

@Service
public class MicroHeaderConfig implements MicroHeader {

    String REGISTRATION_ID="def-client";

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public Consumer<HttpHeaders> initHeader() {
        ClientRegistration client=clientRegistrationRepository.findByRegistrationId(REGISTRATION_ID);
        if(client!=null){
            return h -> h.add("Authorization","Basic "+ Base64.getEncoder().encodeToString((client.getClientId()+":"+ client.getClientSecret()).getBytes(Charset.forName("UTF-8"))));
        }
        return null;
    }
}
