package visao;

import controle.MovimentacaoController;
import controle.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.classes.*;
import persistencia.*;
import relatorios.RelatorioPDF;

public class TelaRelatorios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaRelatorios.class.getName());

    public TelaRelatorios() {
        initComponents();
        setLocationRelativeTo(null);
        adicionarPlacas();
        adicionarTiposDeDespesas();
    }

    @SuppressWarnings("unchecked")
    
    public final void adicionarPlacas(){
        VeiculoController controler = new VeiculoController();
        ArrayList<TipoDeVeiculos> lista;
        try {
            lista = controler.listar();
            for(TipoDeVeiculos obj: lista){
            jComboBoxPlaca.addItem(obj.getPlaca());
        }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }     
    }
    
    public final void adicionarTiposDeDespesas(){
        TipoDespesaController controler = new TipoDespesaController();
        ArrayList<TipoDeDespesa> lista;
        try {
            lista = controler.listar();
            for(TipoDeDespesa obj: lista){
            jComboBoxDespesa.addItem(obj.getDescricao());
        }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }     
    }
        
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonVoltar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextFieldMes = new javax.swing.JTextField();
        jTextFieldAno = new javax.swing.JTextField();
        jComboBoxPlaca = new javax.swing.JComboBox<>();
        jFormattedTextFieldDataInicial = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataFinal = new javax.swing.JFormattedTextField();
        jComboBoxDespesa = new javax.swing.JComboBox<>();
        jButtonRelatorioInativos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(this::jButtonVoltarActionPerformed);

        jButton1.setText("Gerar relatorio");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jComboBoxPlaca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));

        try {
            jFormattedTextFieldDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            jFormattedTextFieldDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBoxDespesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));

        jButtonRelatorioInativos.setText("Veiculos Inativos");
        jButtonRelatorioInativos.addActionListener(this::jButtonRelatorioInativosActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldMes, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(jTextFieldAno))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jComboBoxPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRelatorioInativos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonVoltar)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jFormattedTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(345, 345, 345))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRelatorioInativos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(jButtonVoltar)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            MovimentacaoController movimentacaoController = new MovimentacaoController();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            LocalDate dataInicial = LocalDate.parse(jFormattedTextFieldDataInicial.getText(), formatter);
            LocalDate dataFinal = LocalDate.parse(jFormattedTextFieldDataFinal.getText(), formatter);
            
            String placa = String.valueOf(jComboBoxPlaca.getSelectedItem());  
            String descricao = String.valueOf(jComboBoxDespesa.getSelectedItem()); 

            ArrayList<Movimentacao> lista = movimentacaoController.listarMovimentacoes();
            VeiculoController veiController = new VeiculoController();
            TipoDeDespesasDAO despesaDAO = new TipoDeDespesasDAO();
            
            // Chama o RelatorioPDF para gerar o PDF
            RelatorioPDF pdf = new RelatorioPDF();

            pdf.gerarRelatorio(lista, dataInicial, dataFinal,veiController.buscarPorPlaca(placa),despesaDAO.buscarPorDescricao(descricao));

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonRelatorioInativosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRelatorioInativosActionPerformed
        // TODO add your handling code here:
        
        VeiculoController controller = new VeiculoController();
        
        RelatorioPDF pdf = new RelatorioPDF();
        try {
            pdf.gerarRelatorioVeiculosInativos(controller.buscarPorStatus(StatusVeiculo.INATIVO));
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonRelatorioInativosActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaRelatorios().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonRelatorioInativos;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxDespesa;
    private javax.swing.JComboBox<String> jComboBoxPlaca;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataFinal;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataInicial;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldMes;
    // End of variables declaration//GEN-END:variables
}
;