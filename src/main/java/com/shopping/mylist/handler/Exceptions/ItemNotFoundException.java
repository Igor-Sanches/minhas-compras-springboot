package com.shopping.mylist.handler.Exceptions;

import com.shopping.mylist.handler.BusinessException;

public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException() {
        super("O item nāo foi encontrado.");
    }
}
