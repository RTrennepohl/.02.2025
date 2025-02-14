package frc.robot;

public final class Constants {
    
    public static class MotorPorts {
        public static final int MOTOR_DIREITA = 4;
        public static final int MOTOR_ESQUERDA = 5;
        public static final int MOTOR_AUX_DIREITA = 3;
        public static final int MOTOR_AUX_ESQUERDA = 2;
        public static final int ELEVADOR_DIREITA = 6;
        public static final int ELEVADOR_ESQUERDA = 7;
    }

    public static class LimelightConstants {
        public static final double LIMELIGHT_CORRECTION_FACTOR = 0.02;
        public static final double LIMELIGHT_ERROR_THRESHOLD = 1.0;
        public static final String LIME_CAMERA_NAME = "limelight"; // Nome padrão da câmera Limelight
        public static final int[] APRILTAG_IDS = {11}; // IDs das tags que deseja rastrear
        public static final double LIME_DOWNSCALING = 0.5; // Fator de downscaling da câmera (ajuste conforme necessário)
    
        // Velocidades para os comandos automáticos
        public static final double BASE_SPEED = -0.5;
        public static final double TURN_ADJUSTMENT = 0.1;
    }

    public static class SensorConstants {

        public static final int SensorID = 1;
    
    }
    
    public static class ElevatorConstants {
        public static final double KP = 0.1;
        public static final double KI = 0.0;
        public static final double KD = 0.0;
        public static final double ENCODER_POR_CM = 10.0;
        public static final double ALTURA_INICIAL_CM = 18.0;
        public static final double ALTURA_L1 = 46.0;
        public static final double ALTURA_L2 = 58.0;
        public static final double ALTURA_L3 = 70.0;
        public static final double ALTURA_L4 = 90.0;
        public static final double TOLERANCIA_CM = 1.0;
    }
}
