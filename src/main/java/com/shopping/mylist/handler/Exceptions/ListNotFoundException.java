package com.shopping.mylist.handler.Exceptions;

import com.shopping.mylist.handler.BusinessException;

public class ListNotFoundException extends BusinessException {
    public ListNotFoundException() {
        super("A lista nāo foi encontrado.");
    }
}
