package com.example.messaging.api.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ContactTest {


    private final String fname = "Test";
    private final String lname = "Test";
    private final String phone = "1234567890";
    private final String email = "Test@gmail.com";

    @Mock
    Contact contact;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(contact.getFname()).thenReturn(fname);
        Mockito.when(contact.getLname()).thenReturn(lname);
        Mockito.when(contact.getEmail()).thenReturn(email);
        Mockito.when(contact.getPhone()).thenReturn(phone);
    }

    @Test
    public void testFName() {
        Mockito.when(contact.getFname()).thenReturn(fname);
        Assert.assertEquals("Test", contact.getFname());
    }

    @Test
    public void testLName() {
        Mockito.when(contact.getLname()).thenReturn(lname);
        Assert.assertEquals("Test", contact.getLname());
    }

    @Test
    public void testEmail() {
        Mockito.when(contact.getEmail()).thenReturn(email);
        Assert.assertEquals("Test@gmail.com", contact.getEmail());
    }

    @Test
    public void testPhone() {
        Mockito.when(contact.getPhone()).thenReturn(phone);
        Assert.assertEquals("1234567890", contact.getPhone());
    }

    @After
    public void tearDown() throws Exception {
        contact = null;
    }
}