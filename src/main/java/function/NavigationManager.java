/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import javax.swing.JFrame;
import java.util.Stack;

/**
 *
 * @author Kang Wei
 */
public class NavigationManager {

    private static NavigationManager instance;
    private Stack<JFrame> frameStack;
    private JFrame currentFrame;

    private NavigationManager() {
        frameStack = new Stack<>();
    }

    public static synchronized NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
        }

    public void openFrame(JFrame newFrameToShow, JFrame currentFrameToHide) {
        if (currentFrameToHide != null) {
            currentFrameToHide.setVisible(false);
            frameStack.push(currentFrameToHide);
            System.out.println("Pushed to stack: " + currentFrameToHide.getTitle() + ". Stack size: " + frameStack.size());
        }
        if (newFrameToShow != null) {
            currentFrame = newFrameToShow;
            newFrameToShow.setVisible(true);
            System.out.println("Opened: " + newFrameToShow.getTitle());
        }
    }

    public JFrame goBack() {
        if (!frameStack.isEmpty()) {
            JFrame previousFrame = frameStack.pop();
            previousFrame.setVisible(true);
            currentFrame.dispose();
            System.out.println("Popped and showing: " + previousFrame.getTitle() + ". Stack size: " + frameStack.size());
            return previousFrame;
        } else {
            System.out.println("Navigation stack is empty. No frame to go back to.");
        }
        return null;
    }

    public void clearHistory() { // for log out
        while (!frameStack.isEmpty()) {
            JFrame frame = frameStack.pop();
            frame.dispose();
        }
            System.out.println("Navigation history cleared.");
        }
    }