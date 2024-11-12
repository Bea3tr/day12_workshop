package ssf.day12_workshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.security.SecureRandom;

@Controller
@RequestMapping("/generate")
public class NumberController {

    @GetMapping
    public String getNumber(
        @RequestParam MultiValueMap<String, String> form,
        Model model) {

        String name = form.getFirst("name");
        String count = form.getFirst("count");

        if(name == "" || count == "") {
            model.addAttribute("numbers", defaultGenNum());
        } else {
            int numCount = Integer.parseInt(count);

            model.addAttribute("name", name.toUpperCase());
            model.addAttribute("count", numCount);
            model.addAttribute("numbers", generateNumber(numCount));
        }

        return "generate";
    }

    private List<String> generateNumber(int count) {
        Random rand = new SecureRandom();
        List<String> genNum = new LinkedList<>();
        // Number of generated numbers
        for(int i = 0; i < count; i++) {
            // Numbers 0 - 30
            int idx = rand.nextInt(31);
            String fileName = "number" + idx +".jpg";
            String filePath = "/numbers/" + fileName;
            genNum.add(filePath);
        }
        return genNum;
    }

    private List<String> defaultGenNum() {
        int[] fixed = {5, 7, 3, 4};
        List<String> genNum = new LinkedList<>();
        for(int idx : fixed) {
            String fileName = "number" + idx +".jpg";
            String filePath = "/numbers/" + fileName;
            genNum.add(filePath);
        }
        return genNum;
    }
    
}
