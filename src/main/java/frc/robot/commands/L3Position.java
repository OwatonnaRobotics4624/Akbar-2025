package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ScoringMecanisms.*;
public class L3Position extends Command{
    private final PivotSubsystem m_pivotSubsystem;
    private final ElevatorSubsystem m_elevatorSubsystem;

    public L3Position(PivotSubsystem pivotSubsystem, ElevatorSubsystem elevatorSubsystem) {
        m_pivotSubsystem = pivotSubsystem;
        m_elevatorSubsystem = elevatorSubsystem;
        addRequirements(m_pivotSubsystem, m_elevatorSubsystem);
    }

    @Override
    public void initialize() {
        m_pivotSubsystem.L3Reef();
        m_elevatorSubsystem.L3Reef();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        double pivotPosition = m_pivotSubsystem.getEncoderPosition();
        double elevatorPosition = m_elevatorSubsystem.getEncoderPosition();
        return (pivotPosition <= -166 && pivotPosition >= -176) &&
               (elevatorPosition <= 40 && elevatorPosition >= 30);
    }

    @Override
    public void end(boolean interrupted) {
        m_pivotSubsystem.stop();
        m_elevatorSubsystem.stop();
    }
}
