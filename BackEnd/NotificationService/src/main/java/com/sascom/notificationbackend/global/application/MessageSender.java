package com.sascom.notificationbackend.global.application;

public interface MessageSender {
    void sendMessage(String receiverAddress, String title, String body);
}
