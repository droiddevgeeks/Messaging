package com.example.messaging.api.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MessageTest {


    private final String to = "+9123456789";
    private final String from = "+123456789";
    private final String body = "Hi, You Otp is 678903";
    private final String status = "queued";

    @Mock
    Message message;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(message.getTo()).thenReturn(to);
        Mockito.when(message.getFrom()).thenReturn(from);
        Mockito.when(message.getStatus()).thenReturn(status);
        Mockito.when(message.getBody()).thenReturn(body);
    }


    @Test
    public void testTo() {
        Mockito.when(message.getTo()).thenReturn(to);
        Assert.assertEquals("+9123456789", message.getTo());
    }

    @Test
    public void testFrom() {
        Mockito.when(message.getFrom()).thenReturn(from);
        Assert.assertEquals("+123456789", message.getFrom());
    }

    @Test
    public void testBody() {
        Mockito.when(message.getBody()).thenReturn(body);
        Assert.assertEquals("Hi, You Otp is 678903", message.getBody());
    }

    @Test
    public void testStatus() {
        Mockito.when(message.getStatus()).thenReturn(status);
        Assert.assertEquals("queued", message.getStatus());
    }

    @After
    public void tearDown() throws Exception {
        message = null;
    }
}