package com.mdp.micro.client.header;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
 public class DefaultMicroHeader implements MicroHeader {
    @Override
    public Consumer<HttpHeaders> initHeader() {
        return null;
    }
}
