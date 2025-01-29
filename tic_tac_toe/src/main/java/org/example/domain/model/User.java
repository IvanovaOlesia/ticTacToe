package org.example.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

public class User {
    private UUID id;
    private String login;
    private String password;
}
