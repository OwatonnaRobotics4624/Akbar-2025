package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ScoringMecanisms.*;
public class PathPlannerElevatorL4 extends ParallelCommandGroup{
    
    public PathPlannerElevatorL4(ElevatorSubsystem elevatorSubsystem){
        addCommands(new L4ElevatorReef(elevatorSubsystem));
    }
}
