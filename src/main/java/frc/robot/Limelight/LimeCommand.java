package frc.robot.Limelight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers.LimelightResults;
import frc.robot.LimelightHelpers.LimelightTarget_Fiducial;
import frc.robot.TankDrive.Movimento; // Classe que controla os motores

public class LimeCommand extends Command {
    private final Lime lime;
    private final Movimento movimento;

    public LimeCommand(Lime lime, Movimento movimento, Joystick joystick) {
        this.lime = lime;
        this.movimento = movimento;

        addRequirements(lime, movimento); // Agora requer também o subsistema de movimento
    }

    @Override
    public void execute() {
        LimelightResults results = lime.getLatestResults();

        if (results != null && results.targets_Fiducials.length > 0) {
            LimelightTarget_Fiducial tag = results.targets_Fiducials[0];

            // Se detectar a AprilTag com ID 11, move para frente
            if (tag.fiducialID == 11) {
                System.out.println("Tag ID 11 detectada! Movendo para frente.");
                movimento.mover(0.5); // Ajuste conforme necessário
                return;
            }
        }

        // Se nenhuma tag for detectada ou não for a ID 11, para o robô
        System.out.println("Nenhuma tag válida detectada. Parando o robô.");
        movimento.mover(0.0);
    }

    @Override
    public boolean isFinished() {
        return false; // O comando continua rodando até ser interrompido
    }
}
