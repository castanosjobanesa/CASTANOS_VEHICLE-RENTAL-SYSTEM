package com.scc.model;

public record user(int userId, String username, String passwordHash, String role, String status) {}