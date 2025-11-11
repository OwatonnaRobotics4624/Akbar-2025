package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ScoringMecanisms.ElevatorSubsystem;
import frc.robot.subsystems.ScoringMecanisms.PivotSubsystem;

public class L3PositionCMD extends ParallelCommandGroup{
    public L3PositionCMD(ElevatorSubsystem elevatorSubsystem, PivotSubsystem pivotSubsystem){
        addCommands(new L3Position(pivotSubsystem, elevatorSubsystem));
    }
    
}
