package frc.robot;

import frc.robot.TankDrive.*;
//import frc.robot.Limelight.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
    private final Movimento movimento = new Movimento();
   // private final Lime limelight = new Lime();

    private final Joystick joystick0 = new Joystick(0);
    //private final Joystick joystick1 = new Joystick(1);

    public RobotContainer() { 
        // Define os comandos padrões para os subsistemas
        movimento.setDefaultCommand(new MovimentoCommand(movimento, joystick0));
        
        // Corrigido: Passando os parâmetros corretamente para Lime
       // limelight.setDefaultCommand(new LimeCommand(limelight, movimento, joystick0));
    }

    public Command getAutonomousCommand() {
        return null; // Pode ser substituído por um comando autônomo específico
    }
}
