import java.util.*;

public class DeviceRegistry {
    private final List<SmartClassroomDevice> devices = new ArrayList<>();

    public void add(SmartClassroomDevice d) { devices.add(d); }

    // FIXED: Dynamically fetches a device based on what it can DO, not what it IS.
    @SuppressWarnings("unchecked")
    public <T> T getFirstWithCapability(Class<T> capability) {
        for (SmartClassroomDevice d : devices) {
            if (capability.isInstance(d)) return (T) d;
        }
        throw new IllegalStateException("Missing capability: " + capability.getSimpleName());
    }

    // NEW: Fetches ALL devices with a specific capability (Perfect for shutting down the room!)
    @SuppressWarnings("unchecked")
    public <T> List<T> getAllWithCapability(Class<T> capability) {
        List<T> result = new ArrayList<>();
        for (SmartClassroomDevice d : devices) {
            if (capability.isInstance(d)) result.add((T) d);
        }
        return result;
    }
}