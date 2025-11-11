package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ScoringMecanisms.PivotSubsystem;
public class L4PivotCMD extends Command{
    private final PivotSubsystem m_pivotsubsystem;

    public L4PivotCMD(PivotSubsystem pivotsubsystem) {
        m_pivotsubsystem = pivotsubsystem;
        addRequirements(m_pivotsubsystem);

    }

    @Override
    public void initialize() {
        m_pivotsubsystem.highScore();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        double position = m_pivotsubsystem.getEncoderPosition();
        return position <= -161 && position >= -170;
    }

    @Override
    public void end(boolean interrupted) {
        m_pivotsubsystem.stop();
    }
}
