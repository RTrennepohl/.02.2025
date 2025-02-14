package frc.robot.TankDrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;      
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.* ;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;


public class Movimento extends SubsystemBase {
    SparkMaxConfig MDConfig, MEConfig,M2Config,M3Config;
    private final SparkMax MD = new SparkMax(MotorPorts.MOTOR_DIREITA, MotorType.kBrushed);
    private final SparkMax ME = new SparkMax(MotorPorts.MOTOR_ESQUERDA, MotorType.kBrushed);
    private final SparkMax M2 = new SparkMax(MotorPorts.MOTOR_AUX_DIREITA, MotorType.kBrushed);
    private final SparkMax M3 = new SparkMax(MotorPorts.MOTOR_AUX_ESQUERDA, MotorType.kBrushed);

public Movimento() {
    MDConfig = new SparkMaxConfig();
    MEConfig = new SparkMaxConfig();
    M2Config = new SparkMaxConfig();
    M3Config = new SparkMaxConfig();

    MDConfig.idleMode(IdleMode.kBrake);
    MEConfig.idleMode(IdleMode.kBrake);
    M2Config.idleMode(IdleMode.kBrake);
    M3Config.idleMode(IdleMode.kBrake);

    M2Config.follow(ME);
    M3Config.follow(MD);

    MD.configure(MDConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    ME.configure(MEConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    M2.configure(M2Config,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    M3.configure(M3Config,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);

    }

public void setMD(double speed) {
    MD.set(speed);
    M2.set(speed);
}

public void setME(double speed) {
    ME.set(speed);
    M3.set(speed);
}

public void mover(double speed) {
    setMD(speed);
    setME(speed);
}
public void pararMotores() {    
    MD.set(0);
    ME.set(0);
}
}