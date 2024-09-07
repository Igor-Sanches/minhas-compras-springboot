package com.shopping.mylist.handler.Exceptions;

import com.shopping.mylist.handler.BusinessException;

public class InputRequiredException extends BusinessException {

    public InputRequiredException(String inputNmae) {
        super("O campo %s é obrigatório".formatted(inputNmae));
    }

}
