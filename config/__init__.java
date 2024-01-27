// Import statements
import com.example.config.range.Range;
import com.example.config.range.OptionalRange;
import com.example.config.pid.PIDConfig;

import com.example.config.physical.PhysicalConfig;

import com.example.config.swerve.swervemoduleConfig;
import com.example.config.swerve.MotorConfig;
import com.example.config.swerve.EncoderConfig;
import com.example.config.swerve.swervemoduledoubleProperty;
import com.example.config.swerve.ModulePosition;
import com.example.config.swerve.swervemoduleIntProperty;
import com.example.config.swerve.OptionalswervemoduledoubleProperty;
import com.example.config.swerve.OptionalswervemoduleIntProperty;

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
    private swervemoduleConfig swervemoduleConfig;
    private MotorConfig motorConfig;
    private EncoderConfig encoderConfig;
    private swervemoduledoubleProperty swervemoduledoubleProperty;
    private ModulePosition modulePosition;
    private swervemoduleIntProperty swervemoduleIntProperty;
    private OptionalswervemoduledoubleProperty optionalswervemoduledoubleProperty;
    private OptionalswervemoduleIntProperty optionalswervemoduleIntProperty;

    // Import classes from driver_controls package
    private DriverControlsConfig driverControlsConfig;

    // Constructor
    public ConfigPackage(Range range, OptionalRange optionalRange, PIDConfig pidConfig,
                         PhysicalConfig physicalConfig,
                         swervemoduleConfig swervemoduleConfig, MotorConfig motorConfig,
                         EncoderConfig encoderConfig, swervemoduledoubleProperty swervemoduledoubleProperty,
                         ModulePosition modulePosition, swervemoduleIntProperty swervemoduleIntProperty,
                         OptionalswervemoduledoubleProperty optionalswervemoduledoubleProperty,
                         OptionalswervemoduleIntProperty optionalswervemoduleIntProperty,
                         DriverControlsConfig driverControlsConfig) {
        // Initialize fields with the provided values
        this.range = range;
        this.optionalRange = optionalRange;
        this.pidConfig = pidConfig;
        this.physicalConfig = physicalConfig;
        this.swervemoduleConfig = swervemoduleConfig;
        this.motorConfig = motorConfig;
        this.encoderConfig = encoderConfig;
        this.swervemoduledoubleProperty = swervemoduledoubleProperty;
        this.modulePosition = modulePosition;
        this.swervemoduleIntProperty = swervemoduleIntProperty;
        this.optionalswervemoduledoubleProperty = optionalswervemoduledoubleProperty;
        this.optionalswervemoduleIntProperty = optionalswervemoduleIntProperty;
        this.driverControlsConfig = driverControlsConfig;
    }

    // Add getters and setters as needed
}
