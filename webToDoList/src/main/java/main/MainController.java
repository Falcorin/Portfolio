package main;

import main.model.Affair;
import main.model.AffairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    private AffairRepository affairRepository;

    @Value("${someParameter}")
    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Affair> affairs = affairRepository.findAll();
        ArrayList<Affair> arrayList = new ArrayList<>();
        affairs.forEach(arrayList::add);
        model.addAttribute("affairs", arrayList);
        model.addAttribute("affairsCount", arrayList.size());
        model.addAttribute("someParameter", someParameter);
        return "index";
    }
}
