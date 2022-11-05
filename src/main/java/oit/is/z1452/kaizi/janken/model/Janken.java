package oit.is.z1452.kaizi.janken.model;

import java.util.Random;

public class Janken {
  String mystatus;
  String oppstatus;

  public void CPUOpponentHand() {
    Random ran = new Random();
    int result = ran.nextInt() % 3;
    if (result == 0) {
      this.oppstatus = "Gu";
    } else if (result == 1) {
      this.oppstatus = "Choki";
    } else {
      this.oppstatus = "Pa";
    }
  }

  public String JankenResult() {
    if (this.mystatus.equals("Gu")) {
      return GuResult();
    } else if (this.mystatus.equals("Choki")) {
      return ChokiResult();
    } else {
      return PaResult();
    }
  }

  public String GuResult() {
    if (this.oppstatus.equals("Pa")) {
      return "You lose";
    } else if (this.oppstatus.equals("Choli")) {
      return "You Win!";
    } else {
      return "Draw";
    }
  }

  public String ChokiResult() {
    if (this.oppstatus.equals("Gu")) {
      return "You lose";
    } else if (this.oppstatus.equals("Pa")) {
      return "You Win!";
    } else {
      return "Draw";
    }
  }

  public String PaResult() {
    if (this.oppstatus.equals("Choki")) {
      return "You lose";
    } else if (this.oppstatus.equals("Gu")) {
      return "You Win!";
    } else {
      return "Draw";
    }
  }

  public void setOppStatus(String Hand) {
    this.oppstatus = Hand;
  }

  public String getOppstatus() {
    return this.oppstatus;
  }

  public void setMyStatus(String Hand) {
    this.mystatus = Hand;
  }

  public String getMystatus() {
    return this.mystatus;
  }
}
