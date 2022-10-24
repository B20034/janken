package oit.is.z1452.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

import oit.is.z1452.kaizi.janken.model.Entry;

@Controller
@RequestMapping("/janken")
public class JankenController {

  @Autowired
  private Entry entry;

  @GetMapping("step1")
  public String janken(ModelMap model, Principal prin) {

    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    ArrayList<String> userName = new ArrayList<>();
    userName = this.entry.getUsers();
    for (String u : userName) {
      u = "Hi " + u;
    }
    model.addAttribute("name", userName);
    return "janken.html";
  }

  @GetMapping("/step3/{param1}")
  public String sample22(@PathVariable String param1, ModelMap model) {

    ArrayList<String> userName = new ArrayList<>();
    userName = this.entry.getUsers();

    String myHand = "あなたの手 " + param1;
    String opponentHand = "相手の手 Gu";
    String result = "";
    if (param1.equals("Gu")) {
      result = "Draw";
    } else if (param1.equals("Choki")) {
      result = "You Lose";
    } else if (param1.equals("Pa")) {
      result = "You Win!";
    }
    model.addAttribute("name", userName);
    model.addAttribute("myHand", myHand);
    model.addAttribute("opponentHand", opponentHand);
    model.addAttribute("result", result);
    return "janken.html";

  }

  // @Autowired
  // private Entry entry;

  // @GetMapping("step1")
  // public String janken1() {
  // return "janken.html";
  // }

  // @GetMapping("step3")
  // public String sample33() {
  // return "sample33.html";
  // }

}
