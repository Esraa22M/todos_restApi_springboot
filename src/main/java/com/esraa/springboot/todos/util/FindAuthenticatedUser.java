package com.esraa.springboot.todos.util;

import com.esraa.springboot.todos.entities.User;

public interface FindAuthenticatedUser {
    User getAuthenticatedUser ();
}
