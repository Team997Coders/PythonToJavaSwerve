// Import statements
import com.example.config.range.Range;
import com.example.config.range.OptionalRange;
import com.example.config.pid.PIDConfig;

import com.example.config.physical.PhysicalConfig;

import com.example.config.swerve.SwerveModuleConfig;
import com.example.config.swerve.MotorConfig;
import com.example.config.swerve.EncoderConfig;
import com.example.config.swerve.SwerveModuledoubleProperty;
import com.example.config.swerve.ModulePosition;
import com.example.config.swerve.SwerveModuleIntProperty;
import com.example.config.swerve.OptionalSwerveModuledoubleProperty;
import com.example.config.swerve.OptionalSwerveModuleIntProperty;

import com.example.config.drivercontrols.DriverControlsConfig;

// Package declaration
package com.example.config;

// Class containing read-only record objects used for configuration
public class ConfigPackage {
    // Import classes from other packages
    private Range range;
    private OptionalRange optionalRange;
    private PIDConfig pidConfig;

    // Import classes from physical_config package
    private PhysicalConfig physicalConfig;

    // Import classes from swerve_module_config package
    private SwerveModuleConfig swerveModuleConfig;
    private MotorConfig motorConfig;
    private EncoderConfig encoderConfig;
    private SwerveModuledoubleProperty swerveModuledoubleProperty;
    private ModulePosition modulePosition;
    private SwerveModuleIntProperty swerveModuleIntProperty;
    private OptionalSwerveModuledoubleProperty optionalSwerveModuledoubleProperty;
    private OptionalSwerveModuleIntProperty optionalSwerveModuleIntProperty;

    // Import classes from driver_controls package
    private DriverControlsConfig driverControlsConfig;

    // Constructor
    public ConfigPackage(Range range, OptionalRange optionalRange, PIDConfig pidConfig,
                         PhysicalConfig physicalConfig,
                         SwerveModuleConfig swerveModuleConfig, MotorConfig motorConfig,
                         EncoderConfig encoderConfig, SwerveModuledoubleProperty swerveModuledoubleProperty,
                         ModulePosition modulePosition, SwerveModuleIntProperty swerveModuleIntProperty,
                         OptionalSwerveModuledoubleProperty optionalSwerveModuledoubleProperty,
                         OptionalSwerveModuleIntProperty optionalSwerveModuleIntProperty,
                         DriverControlsConfig driverControlsConfig) {
        // Initialize fields with the provided values
        this.range = range;
        this.optionalRange = optionalRange;
        this.pidConfig = pidConfig;
        this.physicalConfig = physicalConfig;
        this.swerveModuleConfig = swerveModuleConfig;
        this.motorConfig = motorConfig;
        this.encoderConfig = encoderConfig;
        this.swerveModuledoubleProperty = swerveModuledoubleProperty;
        this.modulePosition = modulePosition;
        this.swerveModuleIntProperty = swerveModuleIntProperty;
        this.optionalSwerveModuledoubleProperty = optionalSwerveModuledoubleProperty;
        this.optionalSwerveModuleIntProperty = optionalSwerveModuleIntProperty;
        this.driverControlsConfig = driverControlsConfig;
    }

    // Add getters and setters as needed
}
