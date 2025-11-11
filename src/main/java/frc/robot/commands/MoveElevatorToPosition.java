package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ScoringMecanisms.ElevatorSubsystem;
public class MoveElevatorToPosition extends Command{
    private ElevatorSubsystem m_elevator;

    private double m_newSetpoint;
    private double m_tolerance;
    public MoveElevatorToPosition(ElevatorSubsystem elevator, double newSetpoint, double tolerance) {
        m_elevator = elevator;
        m_newSetpoint = newSetpoint;
        m_tolerance = tolerance;
        addRequirements(m_elevator);
    }


}
