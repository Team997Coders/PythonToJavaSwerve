// interfaces package
package com.example.interfaces;

public interface ISwerveModule {
    // Define your ISwerveModule interface here
}

public interface ISwerveDrive {
    // Define your ISwerveDrive interface here
}

// swervemodule package
package com.example.swervemodule;

public class SwerveModule implements ISwerveModule {
    // Define your SwerveModule class here
}

// swervedrive package
package com.example.swervedrive;

import com.example.interfaces.ISwerveDrive;
import com.example.interfaces.ISwerveModule;

public class SwerveDrive implements ISwerveDrive {
    // Define your SwerveDrive class here
}
