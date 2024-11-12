package ssf.day12_workshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import static ssf.day12_workshop.controllers.Constants.*;

@Controller
@RequestMapping("/generate")
public class NumberController {

    // GET /generate?name=fred&count=
    @GetMapping
    public String getNumber(
        @RequestParam MultiValueMap<String, String> form,
        Model model) {

        String name = form.getFirst(ATTR_NAME);
        int count = 0;
        List<String> values;
        List<Integer> intList;

        // GET /generate?list=num,num,num,num -> input from browser address bar
        if(form.containsKey(ATTR_LIST)) {
            values = toList(form.getFirst(ATTR_LIST))
                    .stream()
                    .map(val -> "/numbers/number%d.jpg".formatted(val))
                    .toList();
            model.addAttribute(ATTR_VALUES, values);

            if(form.containsKey(ATTR_NAME)) {
                count = values.size();
                model.addAttribute(ATTR_NAME, name.toUpperCase());
                model.addAttribute(ATTR_COUNT, count);
            return "random_nums";
            }

            return "list_nums";
        }

        name = form.getFirst(ATTR_NAME);
        count = toInt(form.getFirst(ATTR_COUNT), 4);
        intList = generateRandom(count);

        // int {0, 1, 2} -> String{"/numbers/number0.jpg", ...}
        values = intList.stream()
                        .map(val -> "/numbers/number%d.jpg".formatted(val))
                        .toList();

        model.addAttribute(ATTR_NAME, name.toUpperCase());
        model.addAttribute(ATTR_COUNT, count);
        model.addAttribute(ATTR_VALUES, values);

        return "random_nums";
    }

    // private List<String> generateNumber(int count) {
    //     Random rand = new SecureRandom();
    //     List<String> genNum = new LinkedList<>();
    //     // Number of generated numbers
    //     for(int i = 0; i < count; i++) {
    //         // Numbers 0 - 30
    //         int idx = rand.nextInt(31);
    //         String fileName = "number" + idx +".jpg";
    //         String filePath = "/numbers/" + fileName;
    //         genNum.add(filePath);
    //     }
    //     return genNum;
    // }

    // private List<String> defaultGenNum() {
    //     int[] fixed = {5, 7, 3, 4};
    //     List<String> genNum = new LinkedList<>();
    //     for(int idx : fixed) {
    //         String fileName = "number" + idx +".jpg";
    //         String filePath = "/numbers/" + fileName;
    //         genNum.add(filePath);
    //     }
    //     return genNum;
    // }

    
    
}
