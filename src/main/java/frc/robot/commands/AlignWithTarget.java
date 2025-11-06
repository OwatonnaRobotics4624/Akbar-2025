package frc.robot.commands;

import com.ctre.phoenix6.swerve.SwerveRequest.RobotCentric;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calibrations.DriverCalibrations;
import frc.robot.Calibrations.FieldCalibrations;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class AlignWithTarget extends Command{

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

    public AlignWithTarget(int branch, CommandSwerveDrivetrain drivetrain) {
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
        m_validTagId = FieldCalibrations.m_validTagIds.contains(m_tagId);

        m_xspeed = 0.0;
        m_yspeed = 0.0;
        if (m_validTagId){
            if (m_lockedTagId == 0) {
                m_lockedTagId = m_tagId;
                m_targetTx = FieldCalibrations.m_coralReefTargets.get(m_lockedTagId).get(m_branch);
            }

        }
        if (m_lockedTagId == m_tagId) {
            m_currentTx = NetworkTableInstance.getDefault().getTable("limelight-one").getEntry("tx")
                                                .getDouble(DriverCalibrations.kLimelightDefaultKTx);
            m_errorTx = m_targetTx - m_currentTx;

            /* as it gets closer slow down, no pid */
            


            m_drivetrain.setControl(m_swerveRequest
                .withVelocityX(m_xspeed)
                .withVelocityY(m_yspeed)
            );
        } else {
            // Reset if we lose the target
            m_lockedTagId = 0;
            m_profiledPid.reset(0.0); 
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
