package com.mdp.micro.client.header;

import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

public interface MicroHeader {

    Consumer<HttpHeaders> initHeader();
}
