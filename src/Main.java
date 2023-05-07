enum PowerUp {
  MUSHROOM,
  FIRE_FLOWER,
  CAPE_FEATHER,
}

interface State {
  void takeDamage();

  void pickUpPowerUp(PowerUp powerUp);

  void pressAttack();
}

class SmallMario implements State {
  Mario mario;

  public SmallMario(Mario mario) {
    this.mario = mario;
  }

  public void takeDamage() {
    mario.setLives(mario.getLives() - 1);
    if (mario.getLives() <= 0) {
      mario.setState(new DeadMario(mario));
    }
  }

  public void pickUpPowerUp(PowerUp powerUp) {
    switch (powerUp) {
      case MUSHROOM:
        mario.setState(new BigMario(mario));
        break;
      case FIRE_FLOWER:
        mario.setState(new FireMario(mario));
        break;
      case CAPE_FEATHER:
        mario.setState(new CapeMario(mario));
        break;
    }
  }

  public void pressAttack() {
    System.out.println("Mario is small, he can't attack");
  }
}

class BigMario implements State {
  Mario mario;

  public BigMario(Mario mario) {
    this.mario = mario;
  }

  public void takeDamage() {
    mario.setState(new SmallMario(mario));
  }

  public void pickUpPowerUp(PowerUp powerUp) {
    switch (powerUp) {
      case MUSHROOM:
        mario.setLives(mario.getLives() + 1);
        break;
      case FIRE_FLOWER:
        mario.setState(new FireMario(mario));
        break;
      case CAPE_FEATHER:
        mario.setState(new CapeMario(mario));
        break;
    }
  }

  public void pressAttack() {
    System.out.println("Mario is big, he can attack");
  }
}

class FireMario implements State {
  Mario mario;

  public FireMario(Mario mario) {
    this.mario = mario;
  }

  public void takeDamage() {
    mario.setState(new BigMario(mario));
  }

  public void pickUpPowerUp(PowerUp powerUp) {
    switch (powerUp) {
      case MUSHROOM:
        mario.setLives(mario.getLives() + 1);
        break;
      case FIRE_FLOWER:
        mario.setState(new FireMario(mario));
        break;
      case CAPE_FEATHER:
        mario.setState(new CapeMario(mario));
        break;
    }
  }

  public void pressAttack() {
    System.out.println("Mario throws fireballs");
  }
}

class CapeMario implements State {
  Mario mario;

  public CapeMario(Mario mario) {
    this.mario = mario;
  }

  public void takeDamage() {
    mario.setState(new BigMario(mario));
  }

  public void pickUpPowerUp(PowerUp powerUp) {
    switch (powerUp) {
      case MUSHROOM:
        mario.setLives(mario.getLives() + 1);
        break;
      case FIRE_FLOWER:
        mario.setState(new FireMario(mario));
        break;
      case CAPE_FEATHER:
        mario.setState(new CapeMario(mario));
        break;
    }
  }

  public void pressAttack() {
    System.out.println("Mario spins with his cape");
  }
}

class DeadMario implements State {
  Mario mario;

  public DeadMario(Mario mario) {
    this.mario = mario;
  }

  public void takeDamage() {
    System.out.println("Mario is dead, he can't take damage");
  }

  public void pickUpPowerUp(PowerUp powerUp) {
    System.out.println("Mario is dead, he can't pick up power ups");
  }

  public void pressAttack() {
    System.out.println("Mario is dead, he can't attack");
  }
}

class Mario {
  private State state;
  private int lives;

  public Mario() {
    state = new SmallMario(this);
    lives = 5;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public int getLives() {
    return lives;
  }

  public void setLives(int lives) {
    this.lives = lives;
  }

  public void takeDamage() {
    System.out.println("-- Taking damage");
    state.takeDamage();
  }

  public void pickUpPowerUp(PowerUp powerUp) {
    System.out.println("-- Picking up a " + powerUp);
    state.pickUpPowerUp(powerUp);
  }

  public void pressAttack() {
    System.out.println("-- Pressing attack");
    state.pressAttack();
  }

  @Override
  public String toString() {
    return "Mario { state: " + state.getClass().getName() + ", lives: " + lives + " }";
  }
}

public class Main {
  public static void main(String[] args) {
    Mario mario = new Mario();
    System.out.println(mario);
    mario.pressAttack();
    mario.takeDamage();
    System.out.println(mario);
    mario.pickUpPowerUp(PowerUp.MUSHROOM);
    System.out.println(mario);
    mario.pressAttack();
    mario.pickUpPowerUp(PowerUp.FIRE_FLOWER);
    System.out.println(mario);
    mario.pressAttack();
    mario.pickUpPowerUp(PowerUp.CAPE_FEATHER);
    System.out.println(mario);
    mario.pressAttack();
    mario.takeDamage();
    System.out.println(mario);
    mario.takeDamage();
    System.out.println(mario);
    mario.takeDamage();
    mario.takeDamage();
    mario.takeDamage();
    System.out.println(mario);
    mario.takeDamage();
    System.out.println(mario);
    mario.pressAttack();
  }
}