package com.shopping.mylist.handler.Exceptions;

import com.shopping.mylist.handler.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("O usuário nāo foi encontrado.");
    }
}
