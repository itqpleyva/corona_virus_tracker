package corona_virus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import corona_virus.Services.CoronaVirusDataService;

@Controller
public class CasesController {

	@Autowired
	CoronaVirusDataService service;
	
	@GetMapping("/")
	public String Home(Model model) throws Exception {
		int total = service.fetchVirusdata().stream().mapToInt(e -> e.getLatestTotal()).sum();
		int total_yesterday = service.fetchVirusdata().stream().mapToInt(e -> e.getYesterdayTotal()).sum();
		int increase = total-total_yesterday;
		model.addAttribute("cases", service.fetchVirusdata());
		model.addAttribute("total", total);
		model.addAttribute("total_yesterday", total_yesterday);
		model.addAttribute("increase", increase);
		return "home";
	} 
}
