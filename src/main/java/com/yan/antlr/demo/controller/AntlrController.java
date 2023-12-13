package com.yan.antlr.demo.controller;

import com.yan.antlr.demo.helper.BoolComputeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/antlr")
@RestController
public class AntlrController {

    @Autowired
    private BoolComputeHelper boolComputeHelper;

    @RequestMapping("/bool")
    public String boolCompute(@RequestParam (value = "input") String input) {
        return String.valueOf(boolComputeHelper.compute(input));
    }

}
