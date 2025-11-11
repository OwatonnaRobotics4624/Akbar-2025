package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ScoringMecanisms.*;
public class BasePosition extends Command{
    private final PivotSubsystem m_pivotSubsystem;
    private final ElevatorSubsystem m_elevatorSubsystem;

    public BasePosition(PivotSubsystem pivotSubsystem, ElevatorSubsystem elevatorSubsystem) {
        m_pivotSubsystem = pivotSubsystem;
        m_elevatorSubsystem = elevatorSubsystem;
        addRequirements(m_pivotSubsystem, m_elevatorSubsystem);
    }

    @Override
    public void initialize() {
        m_pivotSubsystem.bottomPosition();
        m_elevatorSubsystem.bottomPosition();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        double pivotPosition = m_pivotSubsystem.getEncoderPosition();
        double elevatorPosition = m_elevatorSubsystem.getEncoderPosition();
        return (pivotPosition <= 5 && pivotPosition >= -5) &&
               (elevatorPosition <= 5 && elevatorPosition >= -5);
    }

    @Override
    public void end(boolean interrupted) {
        m_pivotSubsystem.stop();
        m_elevatorSubsystem.stop();
    }
}
