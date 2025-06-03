/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import javax.swing.JFrame;
import java.util.Stack;
import javax.swing.JOptionPane;
import main.Login;

/**
 * Manages a stack of JFrames for navigation purposes (e.g., "back" button).
 * This class uses the Singleton pattern.
 * @author Kang Wei
 */
public class NavigationManager {

    private static NavigationManager instance;
    private Stack<JFrame> frameStack;
    private JFrame currentManagedFrame; // Renamed for clarity, this is the frame currently managed and shown by NavigationManager

    private NavigationManager() {
        frameStack = new Stack<>();
    }

    public static synchronized NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void openFrame(JFrame newFrameToShow, JFrame frameCurrentlyVisible) {
        if (frameCurrentlyVisible != null) {
            frameCurrentlyVisible.setVisible(false);
            frameStack.push(frameCurrentlyVisible);
            System.out.println("Pushed to stack: " + getFrameTitle(frameCurrentlyVisible) + ". Stack size: " + frameStack.size());
        }

        if (newFrameToShow != null) {
            this.currentManagedFrame = newFrameToShow;

            newFrameToShow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            newFrameToShow.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    NavigationManager.getInstance().goBack();
                }
            });

            newFrameToShow.setVisible(true);
            System.out.println("Opened and set as current: " + getFrameTitle(newFrameToShow));
        }
    }

    public JFrame goBack() {
        if (!frameStack.isEmpty()) {
            JFrame frameToDispose = this.currentManagedFrame;
            
            JFrame previousFrame = frameStack.pop(); 
            previousFrame.setVisible(true);
            this.currentManagedFrame = previousFrame;

            if (frameToDispose != null && frameToDispose != previousFrame) {
                frameToDispose.dispose();
                System.out.println("Disposed: " + getFrameTitle(frameToDispose));
            }
            System.out.println("Popped and showing: " + getFrameTitle(previousFrame) + ". Stack size: " + frameStack.size());
            return previousFrame;
        } else {
            System.out.println("Navigation stack is empty. No frame to go back to.");
            if (this.currentManagedFrame != null) {
                // for handling no previous screen
                int confirm = JOptionPane.showConfirmDialog(
                    currentManagedFrame,
                    "Do You Want To Log Out?",
                    "",
                    JOptionPane.YES_NO_OPTION);
        
                if (confirm == JOptionPane.YES_OPTION) {
                    clearHistoryAndDisposeAll(new Login());
                }
            }
        }
        return null;
    }

    public void clearHistoryAndDisposeAll(JFrame loginFrameToShow) {
        while (!frameStack.isEmpty()) {
            JFrame frame = frameStack.pop();
            if (frame.isDisplayable()) { // Check if it hasn't been disposed already
                frame.dispose();
                System.out.println("Disposed from stack: " + getFrameTitle(frame));
            }
        }
        frameStack.clear();

        // Dispose the current active frame if it's not null and displayable
        if (this.currentManagedFrame != null) {
            this.currentManagedFrame.setVisible(false);
            this.currentManagedFrame.dispose();
            System.out.println("Disposed current managed frame: " + getFrameTitle(this.currentManagedFrame));
        }
        this.currentManagedFrame = null;

        System.out.println("Navigation history cleared.");

        if (loginFrameToShow != null) {
            loginFrameToShow.setVisible(true);
        }
    }
    
    // Helper to get title safely
    private String getFrameTitle(JFrame frame) {
        return frame.getTitle() != null && !frame.getTitle().isEmpty() ? frame.getTitle() : "[Untitled Frame]";
    }
}