package fr.umlv.urm.manager;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class RegisterManager {
    private int registerIndex;

    /**
     * RegisterManager constructor
     * 
     * @param maximumRegisterIndex
     */
    public RegisterManager(int maximumRegisterIndex) {
        registerIndex = maximumRegisterIndex;
    }

    /**
     * Get a fixed register index for command (typically for substitutable)
     * 
     * @return fixed register index
     */
    public int getFixedRegister() {
        registerIndex++;
        return registerIndex;
    }

    /**
     * Get a temporary register index for command (typically for compilable)
     * 
     * @return temporary register index
     */
    public int getTemporaryRegister() {
        return registerIndex + 1;
    }
}
