// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix6.swerve.SwerveRequest.RobotCentric;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calibrations.DriverCalibrations;
import frc.robot.Calibrations.FieldCalibrations;
import frc.robot.subsystems.CommandSwerveDrivetrain;


/**
 * AlignToTag command.
 */
public class TranslationAlignToTag extends Command {

    private CommandSwerveDrivetrain m_drivetrain;
    private int m_branch;
    private double m_xspeed;
    private double m_yspeed;
    private double m_targetTx;
    private double m_currentTx;
    private double m_errorTx;
    private int m_tagId;
    private int m_lockedTagId;
    private boolean m_validTagId;
    private int m_activePipeline;
    private RobotCentric m_swerveRequest = new RobotCentric().withRotationalDeadband(DriverCalibrations.kmaxSpeed * 0.1);
    private final ProfiledPIDController m_profiledPid = new ProfiledPIDController(
        DriverCalibrations.kAprilTagTranslationXAlignmentKP,
        0.0, 
        DriverCalibrations.kAprilTagTranslationXAlignmentKD,
        new TrapezoidProfile.Constraints(1.0, 0.05));

    /**
     * AlignToTag Constructor.
     *
     * @param drivetrain The drivetrain
     */
    public TranslationAlignToTag(int branch, CommandSwerveDrivetrain drivetrain) {
        m_branch = branch;  // This also matches the pipeline number
        m_drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("pipeline").setDouble(m_branch);
        m_targetTx = 0.0;  // Once a valid target is found, use the hashmap to set this target
        m_lockedTagId = 0;  // Init with an invalid AprilTag ID
    }

    @Override
    public void execute() {
        m_validTagId = FieldCalibrations.m_validTagIds.contains((int) NetworkTableInstance.getDefault()
            .getTable("limelight-one").getEntry("tid").getInteger(0));  // Validate the current AprilTag ID
        m_activePipeline = (int) NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("pipeline")
            .getDouble(0);  // Get the current pipeline number

        // Default to doing nothing
        m_xspeed = 0.0;
        m_yspeed = 0.0;

        // Check if the active pipeline matches the branch and the tag is valid
        if (m_validTagId && m_activePipeline == m_branch) {
            // Set targetTx based on the branch
            m_targetTx = (m_branch == 0) ? 20.0 : -20.0;

            // Calculate the error and speeds
            m_currentTx = NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("tx")
                .getDouble(DriverCalibrations.kLimelightDefaultKTx);
            m_errorTx = m_currentTx - m_targetTx;
            m_xspeed = m_profiledPid.calculate(m_errorTx);
            m_yspeed = DriverCalibrations.kAprilTagTranslationYRate * .5;
        }

        // Apply the robot-centric translation speeds
        m_drivetrain.setControl(m_swerveRequest.withVelocityX(-m_yspeed).withVelocityY(m_xspeed * .3));
    }
    
    @Override
    public void end(boolean interrupted) {
        // m_drivetrain.setControl(m_swerveRequest.withVelocityX(0.0).withVelocityY(0.0));
        
    }

    @Override
    public boolean isFinished() {
        // return m_onTarget;
        return false;
    }
}