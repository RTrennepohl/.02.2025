package frc.robot.ElevadorSystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevador extends SubsystemBase {
    private final SparkMax BaseDireita;
    private final SparkMax BaseEsquerda;
    private final SparkClosedLoopController pidController;
    private final RelativeEncoder encoder;

    public Elevador() {
        BaseDireita = new SparkMax(Constants.MotorPorts.MOTOR_DIREITA, MotorType.kBrushed);
        BaseEsquerda = new SparkMax(Constants.MotorPorts.MOTOR_ESQUERDA, MotorType.kBrushed);

        configurarMotores();

        pidController = BaseDireita.getClosedLoopController();
        encoder = BaseDireita.getEncoder();
        encoder.setPosition(0);
    }

    private void configurarMotores() {
        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(IdleMode.kBrake);
        config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
        config.closedLoop.pid(Constants.ElevatorConstants.KP, Constants.ElevatorConstants.KI, Constants.ElevatorConstants.KD);
        BaseDireita.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void sincronizarMotores() {
        BaseEsquerda.set(BaseDireita.get());
    }

    public void setElevadorAltura(double alturaCm) {
        double posicaoEncoder = (alturaCm - Constants.ElevatorConstants.ALTURA_INICIAL_CM) * Constants.ElevatorConstants.ENCODER_POR_CM;
        pidController.setReference(posicaoEncoder, ControlType.kPosition);
        sincronizarMotores();
    }

    public double getAlturaAtual() {
        return (encoder.getPosition() / Constants.ElevatorConstants.ENCODER_POR_CM) + Constants.ElevatorConstants.ALTURA_INICIAL_CM;
    }

    public void zeroElevador() {
        encoder.setPosition(0);
    }

    public void pararMotores() {
        BaseDireita.set(0);
        BaseEsquerda.set(0);
    }
}
