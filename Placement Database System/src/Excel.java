import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;


public class Excel extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Excel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jFileChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser2ActionPerformed
        
        jFileChooser2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFileChooser2ActionPerformed(evt);
        }
        });
        File file = jFileChooser2.getSelectedFile();
            int batchSize=20;
            
            if(file!=null)
            {
                String filePath = file.getAbsolutePath();
            try{
            Connection con=Connect.ConnectDB();
            String sql="insert into student values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement=con.prepareStatement(sql);

            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));

            String lineText=null;
            int count=0;

            lineReader.readLine();
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");

                String s1=data[0];
                String s2=data[1];
                String s3=data[2];
                String s4=data[3];
                String s5=data[4];
                String s6=data[5];
                String s7=data[6];
                String s8=data[7];
                String s9=data[8];
                String s10=data[9];
                String s11=data[10];
                String s12=data[11];
                String s13=data[12];

                statement.setString(1,s1);
                statement.setString(2,s2);
                statement.setString(3,s3);
                statement.setString(4,s4);
                statement.setString(5,s5);
                statement.setString(6,s6);
                statement.setString(7,s7);
                statement.setString(8,s8);
                statement.setString(9,s9);
                statement.setString(10,s10);
                statement.setInt(11,parseInt(s11));
                statement.setString(12,s12);
                statement.setString(13,s13);
                statement.addBatch();
                //if(count%batchSize==0){
                    statement.executeBatch();
                //}
            }
            lineReader.close();
            statement.executeBatch();
            con.close();
            JOptionPane.showMessageDialog( this, "Data Imported Successfully");

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
            }
            else
            {
            JOptionPane.showMessageDialog( this, "Please Select a Valid CSV File");
            }
    }//GEN-LAST:event_jFileChooser2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Connect.ConnectDB();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Excel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    // End of variables declaration//GEN-END:variables
}
