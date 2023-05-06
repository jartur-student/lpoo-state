#[derive(Debug)]
enum PowerUp {
    Mushroom,
    FireFlower,
    CapeFeather,
}

#[derive(Debug)]
enum State {
    SmallMario,
    SuperMario,
    FireMario,
    CapeMario,
}

#[derive(Debug)]
struct Mario {
    state: State,
    lives: u32,
}

impl Mario {
    fn new() -> Self {
        Mario {
            state: State::SmallMario,
            lives: 5,
        }
    }

    fn take_damage(&mut self) {
        println!("-- Taking damage");
        match (&self.state, self.lives) {
            (State::SmallMario, 1) => {
                println!("Game over!");
                println!("Restarting game...");
                self.lives = 5;
            }
            (State::SmallMario, _) => self.lives -= 1,
            (State::SuperMario, _) => self.state = State::SmallMario,
            _ => self.state = State::SuperMario,
        }
    }

    fn grab_power_up(&mut self, power_up: PowerUp) {
        println!("-- Grabbing a {:?}", power_up);
        match (&self.state, power_up) {
            (State::SmallMario, PowerUp::Mushroom) => self.state = State::SuperMario,
            (_, PowerUp::FireFlower) => self.state = State::FireMario,
            (_, PowerUp::CapeFeather) => self.state = State::CapeMario,
            (_, PowerUp::Mushroom) => self.lives += 1,
        }
    }

    fn press_attack(&self) {
        println!("-- Pressing Attack");
        match &self.state {
            State::FireMario => println!("Throwing fireballs!"),
            State::CapeMario => println!("Spinning with cape!"),
            _ => println!("Nothing happened!"),
        }
    }
}

fn main() {
    let mut mario = Mario::new();
    println!("{:?}", mario);
    mario.press_attack();
    mario.take_damage();
    println!("{:?}", mario);
    mario.grab_power_up(PowerUp::Mushroom);
    println!("{:?}", mario);
    mario.press_attack();
    mario.grab_power_up(PowerUp::FireFlower);
    println!("{:?}", mario);
    mario.press_attack();
    mario.grab_power_up(PowerUp::CapeFeather);
    println!("{:?}", mario);
    mario.press_attack();
    mario.take_damage();
    println!("{:?}", mario);
    mario.take_damage();
    println!("{:?}", mario);
    mario.take_damage();
    mario.take_damage();
    mario.take_damage();
    println!("{:?}", mario);
    mario.take_damage();
    println!("{:?}", mario);
}
