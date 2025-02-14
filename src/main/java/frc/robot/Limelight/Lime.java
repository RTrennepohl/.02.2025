package frc.robot.Limelight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers.LimelightResults;
import frc.robot.LimelightHelpers.LimelightTarget_Fiducial;
import frc.robot.LimelightHelpers.RawFiducial;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Lime extends SubsystemBase {
    
    public Lime() {
        // Força a configuração correta da câmera Limelight
        LimelightHelpers.setPipelineIndex("limelight", 0);
    }

    public LimelightResults getLatestResults() {
        return LimelightHelpers.getLatestResults("limelight");
    }

    public void printAprilTagData() {
        // Obtendo os resultados mais recentes da câmera Limelight
        LimelightResults results = getLatestResults();

        if (results == null) {
            System.out.println("Erro: Nenhum resultado disponível da Limelight.");
            return;
        }

        // Obtendo dados brutos das tags
        RawFiducial[] fiducials = LimelightHelpers.getRawFiducials("limelight");

        for (RawFiducial fiducial : fiducials) {
            System.out.println("Tag ID: " + fiducial.id);
            System.out.println("Deslocamento X: " + fiducial.txnc);
            System.out.println("Deslocamento Y: " + fiducial.tync);
            System.out.println("Área do alvo: " + fiducial.ta);
            System.out.println("Distância até a câmera: " + fiducial.distToCamera);
            System.out.println("Distância até o robô: " + fiducial.distToRobot);
            System.out.println("Ambiguidade: " + fiducial.ambiguity);
        }

        // Verifica se há tags fiduciais detectadas
        if (results.targets_Fiducials.length > 0) {
            LimelightTarget_Fiducial tag = results.targets_Fiducials[0];

            System.out.println("Tag ID detectada: " + tag.fiducialID);
            System.out.println("Família da Tag: " + tag.fiducialFamily);

            // Se o ID detectado for 11, ativa movimento do robô
            if (tag.fiducialID == 11) {
                moverRoboParaFrente();
            } else {
                pararRobo();
            }
        } else {
            System.out.println("Nenhuma AprilTag detectada.");
            pararRobo();
        }
    }

    // Método para mover o robô para frente
    private void moverRoboParaFrente() {
        System.out.println("Movendo robô para frente!");
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("drive").setDouble(0.5); // Ajuste a velocidade
    }

    // Método para parar o robô
    private void pararRobo() {
        System.out.println("Parando o robô!");
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("drive").setDouble(0.0);
    }
}
