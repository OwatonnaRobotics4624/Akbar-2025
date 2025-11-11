package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ScoringMecanisms.PivotSubsystem;

public class PathPlannerPivotL4 extends ParallelCommandGroup{

    public PathPlannerPivotL4(PivotSubsystem pivotSubsystem){
        addCommands(new L4PivotCMD(pivotSubsystem));
    }
}
