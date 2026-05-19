import gui.MainGUI;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // تشغيل واجهة Swing بشكل آمن
        SwingUtilities.invokeLater(() -> {

            try {
                // فتح الواجهة الرئيسية
                MainGUI mainGUI = new MainGUI();
                mainGUI.setVisible(true);
            } 
            catch (Exception e) {
                // عرض أي خطأ أثناء التشغيل
                System.out.println("Error: " + e.getMessage());
            }
        });
    }
}