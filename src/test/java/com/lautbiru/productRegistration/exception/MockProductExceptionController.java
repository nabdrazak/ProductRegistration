package com.lautbiru.productRegistration.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/productexception")
public class MockProductExceptionController {
    
    @Autowired
    ProductExceptionController productExceptionController;

    @RequestMapping(value = "/productNotFound")
    public void productNotFound() {
        throw new ProductNotFoundException();
    }

    @RequestMapping(value = "/duplicateId")
    public void duplicateId() {
        throw new DuplicationIdException();
    }
}
