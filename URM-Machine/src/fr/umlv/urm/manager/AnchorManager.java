package fr.umlv.urm.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class AnchorManager {
    private final Map<String, Integer> anchors;

    /**
     * AnchorManager constructor
     */
    public AnchorManager() {
        this.anchors = new HashMap<>();
    }

    /**
     * Get random anchor name
     * 
     * @return random anchor name
     */
    public String getRandomAnchor() {
        return UUID.randomUUID().toString();
    }

    /**
     * Get line number for anchor
     * 
     * @param anchor
     * @return line number for anchor
     */
    public int getLineNumber(String anchor) {
        if (!anchors.containsKey(anchor)) {
            throw new IllegalStateException("Can't get undefined anchor "  + anchor);
        }

        return anchors.get(anchor);
    }

    /**
     * Register the anchor at line number
     * 
     * @param anchor
     * @param lineNumber
     */
    public void register(String anchor, int lineNumber) {
        if (anchors.containsKey(anchor)) {
            throw new IllegalStateException("Label \"" + anchor + "\" already register");
        }

        anchors.put(anchor, lineNumber);
    }
}
