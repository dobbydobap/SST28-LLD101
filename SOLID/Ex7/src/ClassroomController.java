public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        InputConnectable pjInput = reg.getFirstWithCapability(InputConnectable.class);
        if (pjInput instanceof PowerControllable) ((PowerControllable) pjInput).powerOn();
        pjInput.connectInput("HDMI-1");

        BrightnessControllable lights = reg.getFirstWithCapability(BrightnessControllable.class);
        lights.setBrightness(60);

        TemperatureControllable ac = reg.getFirstWithCapability(TemperatureControllable.class);
        ac.setTemperatureC(24);

        ScannerDevice scan = reg.getFirstWithCapability(ScannerDevice.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        // FIXED: We ask the registry for EVERYTHING that has a power switch, and turn them all off!
        for (PowerControllable device : reg.getAllWithCapability(PowerControllable.class)) {
            device.powerOff();
        }
    }
}