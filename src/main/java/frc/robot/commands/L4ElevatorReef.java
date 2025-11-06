package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calibrations.DriverCalibrations;
import frc.robot.Calibrations.FieldCalibrations;
import frc.robot.subsystems.ScoringMecanisms.ElevatorSubsystem;

public class L4Reef extends Command{
    private final ElevatorSubsystem m_elevatorSubsystem;

    public L4Reef(ElevatorSubsystem elevatorSubsystem) {
        m_elevatorSubsystem = elevatorSubsystem;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize() {
        m_elevatorSubsystem.highReef();
    }

    @Override
    public void execute() {
        // No additional execution logic needed
    }

    @Override
    public boolean isFinished() {
        // Command is finished when the elevator reaches the target position
        double position = m_elevatorSubsystem.getEncoderPosition();
        return position <= -152.5 && position >= -160;
    }

    @Override
    public void end(boolean interrupted) {
        m_elevatorSubsystem.stop();
    }
}
