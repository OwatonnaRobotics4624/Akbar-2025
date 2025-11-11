package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ScoringMecanisms.ElevatorSubsystem;
public class IntakePositionElevator extends Command{
    private final ElevatorSubsystem m_elevatorSubsystem;

    public IntakePositionElevator(ElevatorSubsystem elevatorSubsystem){
        m_elevatorSubsystem = elevatorSubsystem;
        addRequirements(m_elevatorSubsystem);
    }
    @Override
    public void initialize() {
        m_elevatorSubsystem.Pickup();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        double elevatorPosition = m_elevatorSubsystem.getEncoderPosition();
        return (elevatorPosition <= -45 && elevatorPosition >= -55);
    }

    @Override
    public void end(boolean interrupted) {
        m_elevatorSubsystem.stop();
    }
}
