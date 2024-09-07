package com.shopping.mylist.handler.Exceptions;

import com.shopping.mylist.handler.BusinessException;

public class MinMaxInputException extends BusinessException {
    public MinMaxInputException(String inputName) {
        super("O campo %s precisa conter entre 5 e 100 caracteres".formatted());
    }
}
