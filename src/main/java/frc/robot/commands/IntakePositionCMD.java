package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ScoringMecanisms.ElevatorSubsystem;
import frc.robot.subsystems.ScoringMecanisms.PivotSubsystem;

public class IntakePositionCMD extends ParallelCommandGroup{
    public IntakePositionCMD(ElevatorSubsystem elevatorSubsystem, PivotSubsystem pivotSubsystem){
        addCommands(
            new IntakePositionPivot(pivotSubsystem),
            new IntakePositionElevator(elevatorSubsystem));
    }
    
}
