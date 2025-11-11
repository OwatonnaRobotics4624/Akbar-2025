package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ScoringMecanisms.*;
public class IntakePositionPivot extends Command{

    private final PivotSubsystem m_pivotSubsystem;

    public IntakePositionPivot(PivotSubsystem pivotSubsystem) {
        m_pivotSubsystem = pivotSubsystem;
        addRequirements(m_pivotSubsystem);
    }

    @Override
    public void initialize() {
        m_pivotSubsystem.pickupPosition();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        double pivotPosition = m_pivotSubsystem.getEncoderPosition();
        return (pivotPosition <= -58 && pivotPosition >= -66);
    }

    @Override
    public void end(boolean interrupted) {
        m_pivotSubsystem.stop();
    }
}
