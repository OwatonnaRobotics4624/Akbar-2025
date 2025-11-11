package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ScoringMecanisms.*;;
public class BasePositionCMD extends ParallelCommandGroup{
    public BasePositionCMD(ElevatorSubsystem elevatorSubsystem, PivotSubsystem pivotSubsystem){
        addCommands(
            new BasePivot(pivotSubsystem),
            new BaseElevator(elevatorSubsystem)
        );
    }
    
}
