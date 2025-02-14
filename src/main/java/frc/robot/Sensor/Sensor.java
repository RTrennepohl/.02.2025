package frc.robot.Sensor;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.config.* ;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import frc.robot.*;

public class Sensor extends SubsystemBase {
    private final SparkMax takerMotor = new SparkMax(9, MotorType.kBrushless); // Porta CAN do motor NEO
    private final DigitalInput sensor = new DigitalInput(3); // Sensor de proximidade
    private final Joystick joystick = new Joystick(0); // Joystick para controle

     // Botão do joystick que controla o motor

    public Sensor() {
        // Criar objeto de configuração para o SparkMax
        SparkMaxConfig motorConfig = new SparkMaxConfig();
        
        // Aplicar configurações
        motorConfig.idleMode(IdleMode.kBrake);// Define modo Brake para parar rapidamente
        
        takerMotor.configure(motorConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);

    }

    public void controlarMotor() {
        boolean buttonPressed = joystick.getRawButton(Constants.SensorConstants.SensorID);
        boolean sensorActivated = sensor.get();

        if (buttonPressed && !sensorActivated) {
            takerMotor.set(0.5); // Liga o motor
        } else {
            takerMotor.set(0); // Para o motor se o botão for solto ou o sensor ativar
        }
    }

    @Override
    public void periodic() {
        controlarMotor(); // Atualiza o controle do motor a cada ciclo do robô
    }
}

