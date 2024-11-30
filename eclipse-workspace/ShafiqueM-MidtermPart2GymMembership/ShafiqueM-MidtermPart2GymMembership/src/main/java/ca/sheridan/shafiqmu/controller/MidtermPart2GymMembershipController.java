package ca.sheridan.shafiqmu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridan.shafiqmu.model.Member;

@Controller
public class MidtermPart2GymMembershipController {
	
	// Create a List collection object to model the list of Gym Members
	List<Member> members = new ArrayList<>();
	
	// method to process a get request for /index
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	// method to bind a member object to an html form
	@GetMapping("/GymMemberInput")
	public String memberDataInput(Model model) {
		model.addAttribute("member", new Member());
		return "GymMemberInput";
	}
	
	// method to process the member data input as an object
	@PostMapping("/memberDataInput")
	public String addMember(Model model, @ModelAttribute Member member) {
	   
		String addMember = "";
		members.add(member);
		addMember = "New memeber added to the list.";
		model.addAttribute("message", addMember);
	   return "AddMemberOutcome";
	}
	
	// method to process the request to display the  list of  gym members.
	@GetMapping ("/GymMemberList")
	  public String viewListOfClubMembers(Model model) {
		
		 model.addAttribute("members", members);
		 return "GymMemberList";
	}  
	
}

