package com.chicho.tasks.model.user;

public record RegisterDTO(String email, String password, UserRole role) {
}
