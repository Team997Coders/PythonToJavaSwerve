public class YourClass {

    public static void attachDebugger() {
        try {
            // Set the debug options
            String debugOptions = "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5678";
            System.setProperty("java.awt.headless", "false");
            System.setProperty("java.net.preferIPv4Stack", "true");

            // Start a new process with the debug options
            ProcessBuilder processBuilder = new ProcessBuilder("java", debugOptions, "-cp", "your-classpath", "YourMainClass");
            Process process = processBuilder.start();

            // Wait for the process to exit
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (Exception e) {
            System.out.println("Failed to attach debugger. Exception:\n" + e);
        }
    }

    public static void main(String[] args) {
        attachDebugger();

        // Your main application logic here
    }
}
