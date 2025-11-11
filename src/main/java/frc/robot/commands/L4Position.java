package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ScoringMecanisms.*;
public class L4Position extends ParallelCommandGroup{

    public L4Position(PivotSubsystem pivotSubsystem, ElevatorSubsystem elevatorSubsystem) {
        addCommands(
            new L4PivotCMD(pivotSubsystem),
            new L4ElevatorReef(elevatorSubsystem)
        );
    }
}
