package cn.tedu.boot11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BmiController {
    @RequestMapping("/bmi")
    @ResponseBody
    public String bmi(double weight,double height) {
        double bmi = weight / (height * height);
        if (bmi < 18.5) {
            return "偏瘦";
        }
        if (bmi < 24) {
            return "正常";
        }
        return "偏胖";

    }

}
