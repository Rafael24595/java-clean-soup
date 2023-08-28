package web;

import core.java.Main;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SoupController {

    @PostMapping("/build")
    public String build(@RequestBody String xml) throws Exception {
        String[] panels = Main.main(new String[]{xml});
        return "[" + String.join("," , panels) + "]";
    }

}
